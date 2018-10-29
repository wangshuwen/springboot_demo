package com.zkxh.demo.netty.data.request;

import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName CustomMsg
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/25 19:17
 * @Vserion v0.0.1
 */


public class RequestData {

    //类型  系统编号 0xAB 表示A系统，0xBC 表示B系统
    private int type;

    //信息标志  0xAB 表示心跳包    0xBC 表示超时包  0xCD 业务信息包
    //  private byte flag;

    private int terminalId;

    private int stationId;

    private int terminalIp1;
    private int terminalIp2;

    private int stationIp1;
    private int stationIp2;

    private String terminalIp;
    private String stationIp;

    private int length;  //主题信息的长度

    private int cmd;
    private int sequenceId;

    private Date time;

    private byte result;

    private byte nodeCount;

    private int ndName;

    private int count;

    //主题信息
    private byte[] body;

    //private Integer crc;


    public RequestData() {
    }

    public RequestData(int type, int terminalId, int stationId, int terminalIp1, int terminalIp2, int stationIp1, int stationIp2, int length, int cmd, int sequenceId, Date time, byte result, byte nodeCount, int ndName, byte[] body) {
        this.type = type;
        this.terminalId = terminalId;
        this.stationId = stationId;
        this.terminalIp1 = terminalIp1;
        this.terminalIp2 = terminalIp2;
        this.stationIp1 = stationIp1;
        this.stationIp2 = stationIp2;
        this.length = length;
        this.cmd = cmd;
        this.sequenceId = sequenceId;
        this.time = time;
        this.result = result;
        this.nodeCount = nodeCount;
        this.ndName = ndName;
        this.body = body;
        //  this.crc = crc;
        this.terminalIp = terminalIp1 + "." + terminalIp2;
        this.stationIp = stationIp1 + "." + stationIp2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(int terminalId) {
        this.terminalId = terminalId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public int getTerminalIp1() {
        return terminalIp1;
    }

    public void setTerminalIp1(int terminalIp1) {
        this.terminalIp1 = terminalIp1;
    }

    public int getTerminalIp2() {
        return terminalIp2;
    }

    public void setTerminalIp2(int terminalIp2) {
        this.terminalIp2 = terminalIp2;
    }

    public int getStationIp1() {
        return stationIp1;
    }

    public void setStationIp1(int stationIp1) {
        this.stationIp1 = stationIp1;
    }

    public int getStationIp2() {
        return stationIp2;
    }

    public void setStationIp2(int stationIp2) {
        this.stationIp2 = stationIp2;
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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
        this.sequenceId = sequenceId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public byte getResult() {
        return result;
    }

    public void setResult(byte result) {
        this.result = result;
    }

    public byte getNodeCount() {
        return nodeCount;
    }

    public void setNodeCount(byte nodeCount) {
        this.nodeCount = nodeCount;
    }

    public int getNdName() {
        return ndName;
    }

    public void setNdName(int ndName) {
        this.ndName = ndName;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "type=" + type +
                ", terminalId=" + terminalId +
                ", stationId=" + stationId +
                ", terminalIp1=" + terminalIp1 +
                ", terminalIp2=" + terminalIp2 +
                ", stationIp1=" + stationIp1 +
                ", stationIp2=" + stationIp2 +
                ", terminalIp='" + terminalIp + '\'' +
                ", stationIp='" + stationIp + '\'' +
                ", length=" + length +
                ", cmd=" + cmd +
                ", sequenceId=" + sequenceId +
                ", time=" + time +
                ", result=" + result +
                ", nodeCount=" + nodeCount +
                ", ndName=" + ndName +
                ", body=" + Arrays.toString(body) +
                '}';
    }
}
