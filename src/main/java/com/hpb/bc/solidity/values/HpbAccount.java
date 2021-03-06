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

package com.hpb.bc.solidity.values;

import com.hpb.bc.exception.ApiException;
import com.hpb.bc.model.HpbData;
import com.hpb.bc.solidity.SolidityType;
import com.hpb.bc.solidity.converters.encoders.NumberEncoder;
import com.hpb.bc.util.Crypto;
import com.hpb.bc.util.Sha3;
import org.spongycastle.asn1.sec.SECNamedCurves;
import org.spongycastle.asn1.x9.X9ECParameters;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;
import org.spongycastle.jcajce.provider.asymmetric.ec.BCECPrivateKey;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.jce.spec.ECParameterSpec;
import org.spongycastle.jce.spec.ECPrivateKeySpec;
import org.spongycastle.math.ec.ECCurve;
import org.spongycastle.math.ec.ECPoint;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.Arrays.copyOfRange;

/**
 * Created by davidroon on 05.11.16.
 * This code is released under Apache 2 license
 */
public class HpbAccount {
    static final ECDomainParameters EC_DOMAIN_PARAMETERS;
    static final X9ECParameters CURVE_PARAMS = SECNamedCurves.getByName("secp256k1");

    static final ECCurve.Fp CURVE;
    private static final KeyFactory keyFactory;
    private static final ECParameterSpec CURVE_SPEC;
    private static final NumberEncoder numberEncoder = new NumberEncoder();

    static {
        try {
            EC_DOMAIN_PARAMETERS = new ECDomainParameters(CURVE_PARAMS.getCurve(), CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());
            CURVE = (ECCurve.Fp) EC_DOMAIN_PARAMETERS.getCurve();
            CURVE_SPEC = new ECParameterSpec(CURVE_PARAMS.getCurve(), CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());
            keyFactory = KeyFactory.getInstance("EC", new BouncyCastleProvider());
        } catch (NoSuchAlgorithmException e) {
            throw new ApiException("error while initializing cryptographic tools", e);
        }
    }

    private final BigInteger bigIntPrivateKey;
    private final BCECPrivateKey privateKey;
    private final ECPoint publicKey;
    private final HpbAddress address;

    public HpbAccount(BigInteger privKey) throws InvalidKeySpecException {
        this.bigIntPrivateKey = privKey;
        this.privateKey = (BCECPrivateKey) keyFactory.generatePrivate(new ECPrivateKeySpec(privKey, CURVE_SPEC));
        this.publicKey = EC_DOMAIN_PARAMETERS.getG().multiply(privKey);
        this.address = computeAddress(this.publicKey);
    }

    public ECPoint getPublicKey() {
        return publicKey;
    }

    public HpbAddress getAddress() {
        return address;
    }

    public BigInteger getBigIntPrivateKey() {
        return bigIntPrivateKey;
    }

    public HpbData getDataPrivateKey() {
        return numberEncoder.encode(bigIntPrivateKey, SolidityType.UINT);
    }

    public boolean verify(HpbSignature signature, HpbData data) {
        return getRecId(signature, data.sha3()) > -1;
    }

    public HpbSignature sign(HpbData data) {
        Sha3 hash = data.sha3();
        HpbSignature sig = doSign(hash);

        return new HpbSignature(sig.getR(), sig.getS(), getRecId(sig, hash));
    }

    private byte getRecId(HpbSignature signature, Sha3 hash) {
        HpbData thisKey = HpbData.of(publicKey.getEncoded(/* compressed */ false));

        return (byte) IntStream.range(0, 4).filter(i -> signature.recoverPubBytesFromSignature((byte) i, hash.hash)
                .map(thisKey::equals).orElse(false)).findFirst().orElse(-1);
    }

    private HpbSignature doSign(Sha3 hash) {
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ECPrivateKeyParameters privKeyParams = new ECPrivateKeyParameters(privateKey.getD(), EC_DOMAIN_PARAMETERS);
        signer.init(true, privKeyParams);
        BigInteger[] components = signer.generateSignature(hash.hash);

        return new HpbSignature(components[0], components[1], (byte) 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HpbAccount that = (HpbAccount) o;

        return Objects.equals(privateKey, that.privateKey);
    }

    @Override
    public int hashCode() {
        return privateKey != null ? privateKey.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "address:" + getAddress().withLeading0x();
    }

    private HpbAddress computeAddress(ECPoint publicKey) {
        byte[] pubBytes = publicKey.getEncoded(false);
        byte[] hash = Crypto.sha3(copyOfRange(pubBytes, 1, pubBytes.length));
        return HpbAddress.of(copyOfRange(hash, 12, hash.length));
    }
}
