package com.zkxh.demo.dto;

import java.io.Serializable;

/**
 * @ClassName UpLoadGasDto
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 11:07
 * @Vserion v0.0.1
 */

public class UpLoadGasDto implements Serializable {

    private String terminalId;
    private String stationId;
    private String terminalIp;
    private String stationIp;
    private String sequenceId;
    private String RT;
    private String createTime;
    private String result;
    private double co;
    private double co2;
    private double o2;
    private double ch4;
    private double t;
    private double h;

    public UpLoadGasDto() {
    }

    public UpLoadGasDto(String terminalId, String stationId, String terminalIp, String stationIp, String sequenceId, String RT, String createTime, String result, double co, double co2, double o2, double ch4, double t, double h) {
        this.terminalId = terminalId;
        this.stationId = stationId;
        this.terminalIp = terminalIp;
        this.stationIp = stationIp;
        this.sequenceId = sequenceId;
        this.RT = RT;
        this.createTime = createTime;
        this.result = result;
        this.co = co;
        this.co2 = co2;
        this.o2 = o2;
        this.ch4 = ch4;
        this.t = t;
        this.h = h;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getTerminalIp() {
        return terminalIp;
    }

    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp;
    }

    public String getStationIp() {
        return stationIp;
    }

    public void setStationIp(String stationIp) {
        this.stationIp = stationIp;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public String getRT() {
        return RT;
    }

    public void setRT(String RT) {
        this.RT = RT;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public double getO2() {
        return o2;
    }

    public void setO2(double o2) {
        this.o2 = o2;
    }

    public double getCh4() {
        return ch4;
    }

    public void setCh4(double ch4) {
        this.ch4 = ch4;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    public double getH() {
        return h;
    }

    public void setH(double h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "UpLoadGasDto{" +
                "terminalId='" + terminalId + '\'' +
                ", stationId='" + stationId + '\'' +
                ", terminalIp='" + terminalIp + '\'' +
                ", stationIp='" + stationIp + '\'' +
                ", sequenceId='" + sequenceId + '\'' +
                ", RT='" + RT + '\'' +
                ", createTime='" + createTime + '\'' +
                ", result='" + result + '\'' +
                ", co=" + co +
                ", co2=" + co2 +
                ", o2=" + o2 +
                ", ch4=" + ch4 +
                ", t=" + t +
                ", h=" + h +
                '}';
    }
}
