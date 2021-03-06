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

package com.hpb.bc.example;

import java.util.ArrayList;
import java.util.List;

public class BlockBigRecordExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public BlockBigRecordExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andBlockNumberIsNull() {
            addCriterion("block_number is null");
            return (Criteria) this;
        }

        public Criteria andBlockNumberIsNotNull() {
            addCriterion("block_number is not null");
            return (Criteria) this;
        }

        public Criteria andBlockNumberEqualTo(Long value) {
            addCriterion("block_number =", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberNotEqualTo(Long value) {
            addCriterion("block_number <>", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberGreaterThan(Long value) {
            addCriterion("block_number >", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("block_number >=", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberLessThan(Long value) {
            addCriterion("block_number <", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberLessThanOrEqualTo(Long value) {
            addCriterion("block_number <=", value, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberIn(List<Long> values) {
            addCriterion("block_number in", values, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberNotIn(List<Long> values) {
            addCriterion("block_number not in", values, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberBetween(Long value1, Long value2) {
            addCriterion("block_number between", value1, value2, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockNumberNotBetween(Long value1, Long value2) {
            addCriterion("block_number not between", value1, value2, "blockNumber");
            return (Criteria) this;
        }

        public Criteria andBlockHashIsNull() {
            addCriterion("block_hash is null");
            return (Criteria) this;
        }

        public Criteria andBlockHashIsNotNull() {
            addCriterion("block_hash is not null");
            return (Criteria) this;
        }

        public Criteria andBlockHashEqualTo(String value) {
            addCriterion("block_hash =", value, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashNotEqualTo(String value) {
            addCriterion("block_hash <>", value, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashGreaterThan(String value) {
            addCriterion("block_hash >", value, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashGreaterThanOrEqualTo(String value) {
            addCriterion("block_hash >=", value, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashLessThan(String value) {
            addCriterion("block_hash <", value, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashLessThanOrEqualTo(String value) {
            addCriterion("block_hash <=", value, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashLike(String value) {
            addCriterion("block_hash like", value, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashNotLike(String value) {
            addCriterion("block_hash not like", value, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashIn(List<String> values) {
            addCriterion("block_hash in", values, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashNotIn(List<String> values) {
            addCriterion("block_hash not in", values, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashBetween(String value1, String value2) {
            addCriterion("block_hash between", value1, value2, "blockHash");
            return (Criteria) this;
        }

        public Criteria andBlockHashNotBetween(String value1, String value2) {
            addCriterion("block_hash not between", value1, value2, "blockHash");
            return (Criteria) this;
        }

        public Criteria andTxCountIsNull() {
            addCriterion("tx_count is null");
            return (Criteria) this;
        }

        public Criteria andTxCountIsNotNull() {
            addCriterion("tx_count is not null");
            return (Criteria) this;
        }

        public Criteria andTxCountEqualTo(Long value) {
            addCriterion("tx_count =", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotEqualTo(Long value) {
            addCriterion("tx_count <>", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountGreaterThan(Long value) {
            addCriterion("tx_count >", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountGreaterThanOrEqualTo(Long value) {
            addCriterion("tx_count >=", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountLessThan(Long value) {
            addCriterion("tx_count <", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountLessThanOrEqualTo(Long value) {
            addCriterion("tx_count <=", value, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountIn(List<Long> values) {
            addCriterion("tx_count in", values, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotIn(List<Long> values) {
            addCriterion("tx_count not in", values, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountBetween(Long value1, Long value2) {
            addCriterion("tx_count between", value1, value2, "txCount");
            return (Criteria) this;
        }

        public Criteria andTxCountNotBetween(Long value1, Long value2) {
            addCriterion("tx_count not between", value1, value2, "txCount");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table block_big_record
     *
     * @mbg.generated do_not_delete_during_merge
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table block_big_record
     *
     * @mbg.generated
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}