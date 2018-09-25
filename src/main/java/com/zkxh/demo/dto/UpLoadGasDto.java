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
    private int co_type;
    private double co2;
    private int co2_type;
    private double o2;
    private int o2_type;
    private double ch4;
    private int ch4_type;
    private double t;
    private int t_type;
    private double h;
    private int h_type;

    public UpLoadGasDto() {
    }

    public int getCo_type() {
        return co_type;
    }

    public void setCo_type(int co_type) {
        this.co_type = co_type;
    }

    public int getCo2_type() {
        return co2_type;
    }

    public void setCo2_type(int co2_type) {
        this.co2_type = co2_type;
    }

    public int getO2_type() {
        return o2_type;
    }

    public void setO2_type(int o2_type) {
        this.o2_type = o2_type;
    }

    public int getCh4_type() {
        return ch4_type;
    }

    public void setCh4_type(int ch4_type) {
        this.ch4_type = ch4_type;
    }

    public int getT_type() {
        return t_type;
    }

    public void setT_type(int t_type) {
        this.t_type = t_type;
    }

    public int getH_type() {
        return h_type;
    }

    public void setH_type(int h_type) {
        this.h_type = h_type;
    }

    public UpLoadGasDto(String terminalId, String stationId, String terminalIp, String stationIp, String sequenceId, String RT, String createTime, String result, double co, int co_type, double co2, int co2_type, double o2, int o2_type, double ch4, int ch4_type, double t, int t_type, double h, int h_type) {
        this.terminalId = terminalId;
        this.stationId = stationId;
        this.terminalIp = terminalIp;
        this.stationIp = stationIp;
        this.sequenceId = sequenceId;
        this.RT = RT;
        this.createTime = createTime;
        this.result = result;
        this.co = co;
        this.co_type = co_type;
        this.co2 = co2;
        this.co2_type = co2_type;
        this.o2 = o2;
        this.o2_type = o2_type;
        this.ch4 = ch4;
        this.ch4_type = ch4_type;
        this.t = t;
        this.t_type = t_type;
        this.h = h;
        this.h_type = h_type;
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
                ", co_type=" + co_type +
                ", co2=" + co2 +
                ", co2_type=" + co2_type +
                ", o2=" + o2 +
                ", o2_type=" + o2_type +
                ", ch4=" + ch4 +
                ", ch4_type=" + ch4_type +
                ", t=" + t +
                ", t_type=" + t_type +
                ", h=" + h +
                ", h_type=" + h_type +
                '}';
    }
}
