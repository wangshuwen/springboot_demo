package com.zkxh.demo.vo.resp;

/**
 * @ClassName GasWSRespVO
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 9:22
 * @Vserion v0.0.1
 */

public class GasWSRespVO {

    private double o2;

    private double ch4;

    private double co;

    private double co2;

    private double Temperature;

    private double Humidity;

    private String staffName;

    private String sequenceId;

    private String staffNumber;


    public void setO2(double o2) {
        this.o2 = o2;
    }

    public double getO2() {
        return o2;
    }

    public double getCh4() {
        return ch4;
    }

    public void setCh4(double ch4) {
        this.ch4 = ch4;
    }

    public double getCo() {
        return co;
    }

    public void setCo(double co) {
        this.co = co;
    }

    public double getCo2() {
        return co2;
    }

    public void setCo2(double co2) {
        this.co2 = co2;
    }

    public double getTemperature() {
        return Temperature;
    }

    public void setTemperature(double temperature) {
        Temperature = temperature;
    }

    public double getHumidity() {
        return Humidity;
    }

    public void setHumidity(double humidity) {
        Humidity = humidity;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getStaffNumber() {
        return staffNumber;
    }

    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }
}
