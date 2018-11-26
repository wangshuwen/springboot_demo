package com.zkxh.demo.model.zone;

import java.util.ArrayList;
import java.util.List;

public class ZoneExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table zone
     *
     * @mbg.generated
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table zone
     *
     * @mbg.generated
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table zone
     *
     * @mbg.generated
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
     *
     * @mbg.generated
     */
    public ZoneExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
     *
     * @mbg.generated
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
     *
     * @mbg.generated
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
     *
     * @mbg.generated
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
     *
     * @mbg.generated
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
     *
     * @mbg.generated
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
     *
     * @mbg.generated
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
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
     * This method corresponds to the database table zone
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
     * This method corresponds to the database table zone
     *
     * @mbg.generated
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table zone
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
     * This class corresponds to the database table zone
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

        public Criteria andZoneIdIsNull() {
            addCriterion("zone_id is null");
            return (Criteria) this;
        }

        public Criteria andZoneIdIsNotNull() {
            addCriterion("zone_id is not null");
            return (Criteria) this;
        }

        public Criteria andZoneIdEqualTo(Integer value) {
            addCriterion("zone_id =", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotEqualTo(Integer value) {
            addCriterion("zone_id <>", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdGreaterThan(Integer value) {
            addCriterion("zone_id >", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("zone_id >=", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdLessThan(Integer value) {
            addCriterion("zone_id <", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdLessThanOrEqualTo(Integer value) {
            addCriterion("zone_id <=", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdIn(List<Integer> values) {
            addCriterion("zone_id in", values, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotIn(List<Integer> values) {
            addCriterion("zone_id not in", values, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdBetween(Integer value1, Integer value2) {
            addCriterion("zone_id between", value1, value2, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotBetween(Integer value1, Integer value2) {
            addCriterion("zone_id not between", value1, value2, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneNameIsNull() {
            addCriterion("zone_name is null");
            return (Criteria) this;
        }

        public Criteria andZoneNameIsNotNull() {
            addCriterion("zone_name is not null");
            return (Criteria) this;
        }

        public Criteria andZoneNameEqualTo(String value) {
            addCriterion("zone_name =", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameNotEqualTo(String value) {
            addCriterion("zone_name <>", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameGreaterThan(String value) {
            addCriterion("zone_name >", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameGreaterThanOrEqualTo(String value) {
            addCriterion("zone_name >=", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameLessThan(String value) {
            addCriterion("zone_name <", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameLessThanOrEqualTo(String value) {
            addCriterion("zone_name <=", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameLike(String value) {
            addCriterion("zone_name like", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameNotLike(String value) {
            addCriterion("zone_name not like", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameIn(List<String> values) {
            addCriterion("zone_name in", values, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameNotIn(List<String> values) {
            addCriterion("zone_name not in", values, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameBetween(String value1, String value2) {
            addCriterion("zone_name between", value1, value2, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameNotBetween(String value1, String value2) {
            addCriterion("zone_name not between", value1, value2, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdIsNull() {
            addCriterion("zone_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdIsNotNull() {
            addCriterion("zone_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdEqualTo(Integer value) {
            addCriterion("zone_parent_id =", value, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdNotEqualTo(Integer value) {
            addCriterion("zone_parent_id <>", value, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdGreaterThan(Integer value) {
            addCriterion("zone_parent_id >", value, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("zone_parent_id >=", value, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdLessThan(Integer value) {
            addCriterion("zone_parent_id <", value, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("zone_parent_id <=", value, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdIn(List<Integer> values) {
            addCriterion("zone_parent_id in", values, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdNotIn(List<Integer> values) {
            addCriterion("zone_parent_id not in", values, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdBetween(Integer value1, Integer value2) {
            addCriterion("zone_parent_id between", value1, value2, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("zone_parent_id not between", value1, value2, "zoneParentId");
            return (Criteria) this;
        }

        public Criteria andZoneTypeIsNull() {
            addCriterion("zone_type is null");
            return (Criteria) this;
        }

        public Criteria andZoneTypeIsNotNull() {
            addCriterion("zone_type is not null");
            return (Criteria) this;
        }

        public Criteria andZoneTypeEqualTo(Integer value) {
            addCriterion("zone_type =", value, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeNotEqualTo(Integer value) {
            addCriterion("zone_type <>", value, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeGreaterThan(Integer value) {
            addCriterion("zone_type >", value, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("zone_type >=", value, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeLessThan(Integer value) {
            addCriterion("zone_type <", value, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeLessThanOrEqualTo(Integer value) {
            addCriterion("zone_type <=", value, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeIn(List<Integer> values) {
            addCriterion("zone_type in", values, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeNotIn(List<Integer> values) {
            addCriterion("zone_type not in", values, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeBetween(Integer value1, Integer value2) {
            addCriterion("zone_type between", value1, value2, "zoneType");
            return (Criteria) this;
        }

        public Criteria andZoneTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("zone_type not between", value1, value2, "zoneType");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table zone
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
     * This class corresponds to the database table zone
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