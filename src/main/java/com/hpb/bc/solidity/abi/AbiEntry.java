/*
 * Copyright 2020 HPB Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hpb.bc.solidity.abi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hpb.bc.exception.ApiException;
import com.hpb.bc.model.EventData;
import com.hpb.bc.model.HpbData;
import com.hpb.bc.solidity.converters.decoders.SolidityTypeDecoder;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hpb.bc.util.Crypto.sha3;


/**
 * Created by davidroon on 28.03.17.
 * This code is released under Apache 2 license
 */
public class AbiEntry {
    private final Boolean anonymous;
    private final Boolean constant;
    private final Boolean payable;
    private final String stateMutability;
    private final String name;
    private final String signature;
    private final List<AbiParam> inputs;
    private final List<AbiParam> outputs;
    private final String type;

    public AbiEntry() {
        this(null, null, null, null, null, null, null, null, null);
    }

    private AbiEntry(Boolean anonymous, Boolean constant, Boolean payable, String stateMutability, String name, String signature, List<AbiParam> inputs, List<AbiParam> outputs, String type) {
        this.anonymous = anonymous;
        this.constant = constant;
        this.payable = payable;
        this.stateMutability = stateMutability;
        this.name = name;
        this.signature = signature;
        this.inputs = inputs;
        this.outputs = outputs;
        this.type = type;
    }

    public static List<AbiEntry> parse(final String json) {
        try {
            return new ObjectMapper().readValue(json, new TypeReference<List<AbiEntry>>() {
            });
        } catch (IOException e) {
            throw new ApiException("error while deserialising ABI", e);
        }
    }

    public Boolean isAnonymous() {
        return anonymous;
    }

    public Boolean isConstant() {
        return constant;
    }

    public Boolean isPayable() {
        return payable;
    }

    public String getStateMutability() {
        return stateMutability;
    }

    public String getName() {
        return name;
    }

    public String getSignature() {
        return signature;
    }

    public List<AbiParam> getInputs() {
        return inputs;
    }

    public List<AbiParam> getOutputs() {
        return Optional.ofNullable(outputs).orElseGet(ArrayList::new);
    }

    public String getType() {
        return type;
    }

    public Object decode(EventData eventData, List<List<SolidityTypeDecoder>> decoders, Type resultCls) {
        return findConstructor(decoders, (Class<?>) resultCls)
                .map(constructor -> {
                    try {
                        Class[] resultClasses = constructor.getParameterTypes();
                        Type[] resultTypes = constructor.getGenericParameterTypes();

                        Object[] decodeResult = new Object[decoders.size()];

                        int indexed = 0;
                        int unindexed = 0;

                        for (int i = 0; i < decoders.size(); i++) {
                            AbiParam param = inputs.get(i);
                            final Type resultType = resultTypes[i];
                            final Class inputCls = resultClasses[i];

                            SolidityTypeDecoder decoder = decoders.get(i).stream()
                                    .filter(encoder -> encoder.canDecode(inputCls))
                                    .findFirst().orElseThrow(() -> new ApiException("could not find decoder for " + resultType.getTypeName() + " serious bug detected!"));

                            if (param.isIndexed()) {
                                if (param.isDynamic()) {
                                    decodeResult[i] = decoder.decode(0, HpbData.empty(), resultType);
                                } else {
                                    decodeResult[i] = decoder.decode(0, eventData.getIndexedArguments().get(indexed), resultType);
                                }
                                indexed++;
                            } else {
                                decodeResult[i] = decoder.decode(unindexed++, eventData.getEventArguments(), resultType);
                            }
                        }

                        return constructor.newInstance(decodeResult);
                    } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                        throw new ApiException("error while creating a new instance of " + resultCls.getTypeName());
                    }
                }).orElseThrow(() -> new ApiException("could not find decoder for " + resultCls.getTypeName()));
    }

    public List<Object> decodeParameters(EventData eventData, List<List<SolidityTypeDecoder>> decoders, List<Class<?>> eventParams) {
        Object[] decodeResult = new Object[decoders.size()];

        int indexed = 0;
        int unindexed = 0;

        for (int i = 0; i < decoders.size(); i++) {
            AbiParam param = inputs.get(i);

            Class<?> inputCls = eventParams.get(i);

            SolidityTypeDecoder decoder = decoders.get(i).stream()
                    .filter(encoder -> encoder.canDecode(inputCls))
                    .findFirst().orElseThrow(() -> new ApiException("could not find decoder for " + inputCls.getTypeName() + " serious bug detected!"));

            if (param.isIndexed()) {
                if (param.isDynamic()) {
                    decodeResult[i] = decoder.decode(0, HpbData.empty(), inputCls);
                } else {
                    decodeResult[i] = decoder.decode(0, eventData.getIndexedArguments().get(indexed), inputCls);
                }
                indexed++;
            } else {
                decodeResult[i] = decoder.decode(unindexed++, eventData.getEventArguments(), inputCls);
            }
        }

        return Arrays.asList(decodeResult);
    }

    public Object decode(HpbData data, List<List<SolidityTypeDecoder>> decoders, Type resultType) {
        final Class<?> resultCls = resultType instanceof Class ? (Class) resultType : (Class) ((ParameterizedType) resultType).getRawType();

        if (resultCls.equals(Void.class) || resultCls.getTypeName().equals("void")) {
            return null;
        }

        if (decoders.size() == 1) {
            Optional<SolidityTypeDecoder> optDecoder = decoders.get(0).stream().filter(dec -> dec.canDecode(resultCls))
                    .findFirst();

            return optDecoder
                    .map(decoder -> decoder.decode(0, data, resultType))
                    .orElseGet(() -> decodeFromConstructor(data, decoders, resultCls));

        }

        return decodeFromConstructor(data, decoders, resultCls);
    }

    private Object decodeFromConstructor(HpbData data, List<List<SolidityTypeDecoder>> decoders, Class<?> resultCls) {
        return findConstructor(decoders, (Class<?>) resultCls)
                .map(constructor -> {
                    try {
                        return constructor.newInstance(decodeInternal(data, decoders, constructor.getParameterTypes(), constructor.getGenericParameterTypes()));
                    } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                        throw new ApiException("error while creating a new instance of " + resultCls.getTypeName());
                    }
                }).orElseThrow(() -> new ApiException("could not find decoder for (" + printOutputs() + ") to " + resultCls.getTypeName()));
    }

    private String printOutputs() {
        return outputs.stream().map(AbiParam::getType).reduce((a, b) -> a + ", " + b).orElse("");
    }

    public HpbData signature() {
        //Constructor has no signature has it is an anonyous function
        if ("constructor".equals(type)) {
            return HpbData.empty();
        }

        String params = getInputs().stream().map(AbiParam::getType).collect(Collectors.joining(","));
        return HpbData.of(Arrays.copyOfRange(sha3((getName() + "(" + params + ")").getBytes()), 0, 4));
    }

    public HpbData signatureLong() {
        String params = getInputs().stream().map(AbiParam::getType).collect(Collectors.joining(","));
        return HpbData.of(sha3((getName() + "(" + params + ")").getBytes()));
    }

    public <U> Optional<Constructor<U>> findConstructor(List<List<SolidityTypeDecoder>> decoders, Class<U> resultCls) {
        return Arrays.stream(resultCls.getConstructors()).filter(constructor -> {
            if (constructor.getParameterCount() != decoders.size()) {
                return false;
            }
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            for (int i = 0; i < decoders.size(); i++) {
                final Class<?> parameterType = parameterTypes[i];
                if (decoders.get(i).stream().noneMatch(dec -> dec.canDecode(parameterType))) {
                    return false;
                }
            }
            return true;
        }).findFirst().map(constructor -> (Constructor<U>) constructor);
    }

    private Object[] decodeInternal(HpbData data, List<List<SolidityTypeDecoder>> decoders, Class[] resultClasses, Type[] resultTypes) {
        Object[] decodeResult = new Object[decoders.size()];
        for (int i = 0; i < decoders.size(); i++) {
            final Type resultType = resultTypes[i];
            final Class<?> resultCls = resultClasses[i];
            SolidityTypeDecoder decoder = decoders.get(i).stream()
                    .filter(encoder -> encoder.canDecode(resultCls))
                    .findFirst().orElseThrow(() -> new ApiException("could not find decoder for " + resultType.getTypeName() + " serious bug detected!"));
            decodeResult[i] = decoder.decode(i, data, resultType);
        }

        return decodeResult;
    }

    @Override
    public String toString() {
        return "AbiEntry{" +
                "anonymous=" + anonymous +
                ", constant=" + constant +
                ", payable=" + payable +
                ", stateMutability='" + stateMutability + '\'' +
                ", name='" + name + '\'' +
                ", signature='" + signature + '\'' +
                ", inputs=" + inputs +
                ", outputs=" + outputs +
                ", type='" + type + '\'' +
                '}';
    }
}
