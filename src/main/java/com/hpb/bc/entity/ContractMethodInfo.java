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

package com.hpb.bc.entity;

import java.util.Date;

public class ContractMethodInfo extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract_method_info.id
     *
     * @mbg.generated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract_method_info.contract_addr
     *
     * @mbg.generated
     */
    private String contractAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract_method_info.method_id
     *
     * @mbg.generated
     */
    private String methodId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract_method_info.method_name
     *
     * @mbg.generated
     */
    private String methodName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract_method_info.create_timestamp
     *
     * @mbg.generated
     */
    private Date createTimestamp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract_method_info.method_abi
     *
     * @mbg.generated
     */
    private String methodAbi;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract_method_info.id
     *
     * @return the value of contract_method_info.id
     * @mbg.generated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract_method_info.id
     *
     * @param id the value for contract_method_info.id
     * @mbg.generated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract_method_info.contract_addr
     *
     * @return the value of contract_method_info.contract_addr
     * @mbg.generated
     */
    public String getContractAddr() {
        return contractAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract_method_info.contract_addr
     *
     * @param contractAddr the value for contract_method_info.contract_addr
     * @mbg.generated
     */
    public void setContractAddr(String contractAddr) {
        this.contractAddr = contractAddr == null ? null : contractAddr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract_method_info.method_id
     *
     * @return the value of contract_method_info.method_id
     * @mbg.generated
     */
    public String getMethodId() {
        return methodId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract_method_info.method_id
     *
     * @param methodId the value for contract_method_info.method_id
     * @mbg.generated
     */
    public void setMethodId(String methodId) {
        this.methodId = methodId == null ? null : methodId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract_method_info.method_name
     *
     * @return the value of contract_method_info.method_name
     * @mbg.generated
     */
    public String getMethodName() {
        return methodName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract_method_info.method_name
     *
     * @param methodName the value for contract_method_info.method_name
     * @mbg.generated
     */
    public void setMethodName(String methodName) {
        this.methodName = methodName == null ? null : methodName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract_method_info.create_timestamp
     *
     * @return the value of contract_method_info.create_timestamp
     * @mbg.generated
     */
    public Date getCreateTimestamp() {
        return createTimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract_method_info.create_timestamp
     *
     * @param createTimestamp the value for contract_method_info.create_timestamp
     * @mbg.generated
     */
    public void setCreateTimestamp(Date createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract_method_info.method_abi
     *
     * @return the value of contract_method_info.method_abi
     * @mbg.generated
     */
    public String getMethodAbi() {
        return methodAbi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract_method_info.method_abi
     *
     * @param methodAbi the value for contract_method_info.method_abi
     * @mbg.generated
     */
    public void setMethodAbi(String methodAbi) {
        this.methodAbi = methodAbi == null ? null : methodAbi.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contract_method_info
     *
     * @mbg.generated
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ContractMethodInfo other = (ContractMethodInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getContractAddr() == null ? other.getContractAddr() == null : this.getContractAddr().equals(other.getContractAddr()))
                && (this.getMethodId() == null ? other.getMethodId() == null : this.getMethodId().equals(other.getMethodId()))
                && (this.getMethodName() == null ? other.getMethodName() == null : this.getMethodName().equals(other.getMethodName()))
                && (this.getCreateTimestamp() == null ? other.getCreateTimestamp() == null : this.getCreateTimestamp().equals(other.getCreateTimestamp()))
                && (this.getMethodAbi() == null ? other.getMethodAbi() == null : this.getMethodAbi().equals(other.getMethodAbi()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contract_method_info
     *
     * @mbg.generated
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getContractAddr() == null) ? 0 : getContractAddr().hashCode());
        result = prime * result + ((getMethodId() == null) ? 0 : getMethodId().hashCode());
        result = prime * result + ((getMethodName() == null) ? 0 : getMethodName().hashCode());
        result = prime * result + ((getCreateTimestamp() == null) ? 0 : getCreateTimestamp().hashCode());
        result = prime * result + ((getMethodAbi() == null) ? 0 : getMethodAbi().hashCode());
        return result;
    }
}