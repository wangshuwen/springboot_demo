package com.zkxh.demo.vo.resp;

import java.util.Date;

/**
 * @ClassName GasWSRespVO
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 9:22
 * @Vserion v0.0.1
 */

public class GasWSRespVO {

    private double o2;
    private Integer o2_type;

    private double ch4;
    private Integer ch4_type;

    private double co;
    private Integer co_type;

    private double co2;
    private Integer co2_type;

    private double Temperature;
    private Integer Temperature_type;

    private double Humidity;
    private Integer Humidity_type;

    private String staffName;

    private Integer sequenceId;

    private String staffNumber;

    private Integer staffId;


    private Date rt;
    private Date createTime;


    //TODO 定位信息


    @Override
    public String toString() {
        return "GasWSRespVO{" +
                "o2=" + o2 +
                ", o2_type=" + o2_type +
                ", ch4=" + ch4 +
                ", ch4_type=" + ch4_type +
                ", co=" + co +
                ", co_type=" + co_type +
                ", co2=" + co2 +
                ", co2_type=" + co2_type +
                ", Temperature=" + Temperature +
                ", Temperature_type=" + Temperature_type +
                ", Humidity=" + Humidity +
                ", Humidity_type=" + Humidity_type +
                ", staffName='" + staffName + '\'' +
                ", sequenceId=" + sequenceId +
                ", staffNumber='" + staffNumber + '\'' +
                ", staffId=" + staffId +
                ", rt=" + rt +
                ", createTime=" + createTime +
                '}';
    }

    public double getO2() {
        return o2;
    }

    public void setO2(double o2) {
        this.o2 = o2;
    }

    public Integer getO2_type() {
        return o2_type;
    }

    public void setO2_type(Integer o2_type) {
        this.o2_type = o2_type;
    }

    public double getCh4() {
        return ch4;
    }

    public void setCh4(double ch4) {
        this.ch4 = ch4;
    }

    public Integer getCh4_type() {
        return ch4_type;
    }

    public void setCh4_type(Integer ch4_type) {
        this.ch4_type = ch4_type;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public Integer getCo_type() {
        return co_type;
    }

    public void setCo_type(Integer co_type) {
        this.co_type = co_type;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public Integer getCo2_type() {
        return co2_type;
    }

    public void setCo2_type(Integer co2_type) {
        this.co2_type = co2_type;
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double temperature) {
        Temperature = temperature;
    }

    public Integer getTemperature_type() {
        return Temperature_type;
    }

    public void setTemperature_type(Integer temperature_type) {
        Temperature_type = temperature_type;
    }

    public double getHumidity() {
        return Humidity;
    }

    public void setHumidity(double humidity) {
        Humidity = humidity;
    }

    public Integer getHumidity_type() {
        return Humidity_type;
    }

    public void setHumidity_type(Integer humidity_type) {
        Humidity_type = humidity_type;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Integer getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public Date getRt() {
        return rt;
    }

    public void setRt(Date rt) {
        this.rt = rt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
