package com.zkxh.demo.model.area;

public class Area {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area.area_id
     *
     * @mbg.generated
     */
    private Integer areaId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area.area_name
     *
     * @mbg.generated
     */
    private String areaName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area.area_type
     *
     * @mbg.generated
     */
    private String areaType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column area.area_parent_id
     *
     * @mbg.generated
     */
    private Integer areaParentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area.area_id
     *
     * @return the value of area.area_id
     * @mbg.generated
     */
    public Integer getAreaId() {
        return areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area.area_id
     *
     * @param areaId the value for area.area_id
     * @mbg.generated
     */
    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area.area_name
     *
     * @return the value of area.area_name
     * @mbg.generated
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area.area_name
     *
     * @param areaName the value for area.area_name
     * @mbg.generated
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area.area_type
     *
     * @return the value of area.area_type
     * @mbg.generated
     */
    public String getAreaType() {
        return areaType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area.area_type
     *
     * @param areaType the value for area.area_type
     * @mbg.generated
     */
    public void setAreaType(String areaType) {
        this.areaType = areaType == null ? null : areaType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column area.area_parent_id
     *
     * @return the value of area.area_parent_id
     * @mbg.generated
     */
    public Integer getAreaParentId() {
        return areaParentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column area.area_parent_id
     *
     * @param areaParentId the value for area.area_parent_id
     * @mbg.generated
     */
    public void setAreaParentId(Integer areaParentId) {
        this.areaParentId = areaParentId;
    }
}
