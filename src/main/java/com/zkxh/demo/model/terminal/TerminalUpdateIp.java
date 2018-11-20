package com.zkxh.demo.model.terminal;

import java.util.Date;

public class TerminalUpdateIp {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_update_ip.id
     *
     * @mbg.generated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_update_ip.terminal_ip
     *
     * @mbg.generated
     */
    private String terminalIp;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_update_ip.terminal_num
     *
     * @mbg.generated
     */
    private Integer terminalNum;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_update_ip.update_time
     *
     * @mbg.generated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_update_ip.station_id
     *
     * @mbg.generated
     */
    private Integer stationId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column terminal_update_ip.station_ip
     *
     * @mbg.generated
     */
    private String stationIp;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_update_ip.id
     *
     * @return the value of terminal_update_ip.id
     * @mbg.generated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_update_ip.id
     *
     * @param id the value for terminal_update_ip.id
     * @mbg.generated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_update_ip.terminal_ip
     *
     * @return the value of terminal_update_ip.terminal_ip
     * @mbg.generated
     */
    public String getTerminalIp() {
        return terminalIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_update_ip.terminal_ip
     *
     * @param terminalIp the value for terminal_update_ip.terminal_ip
     * @mbg.generated
     */
    public void setTerminalIp(String terminalIp) {
        this.terminalIp = terminalIp == null ? null : terminalIp.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_update_ip.terminal_num
     *
     * @return the value of terminal_update_ip.terminal_num
     * @mbg.generated
     */
    public Integer getTerminalNum() {
        return terminalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_update_ip.terminal_num
     *
     * @param terminalNum the value for terminal_update_ip.terminal_num
     * @mbg.generated
     */
    public void setTerminalNum(Integer terminalNum) {
        this.terminalNum = terminalNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_update_ip.update_time
     *
     * @return the value of terminal_update_ip.update_time
     * @mbg.generated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_update_ip.update_time
     *
     * @param updateTime the value for terminal_update_ip.update_time
     * @mbg.generated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_update_ip.station_id
     *
     * @return the value of terminal_update_ip.station_id
     * @mbg.generated
     */
    public Integer getStationId() {
        return stationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_update_ip.station_id
     *
     * @param stationId the value for terminal_update_ip.station_id
     * @mbg.generated
     */
    public void setStationId(Integer stationId) {
        this.stationId = stationId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column terminal_update_ip.station_ip
     *
     * @return the value of terminal_update_ip.station_ip
     * @mbg.generated
     */
    public String getStationIp() {
        return stationIp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column terminal_update_ip.station_ip
     *
     * @param stationIp the value for terminal_update_ip.station_ip
     * @mbg.generated
     */
    public void setStationIp(String stationIp) {
        this.stationIp = stationIp == null ? null : stationIp.trim();
    }

    @Override
    public String toString() {
        return "TerminalUpdateIp{" +
                "id=" + id +
                ", terminalIp='" + terminalIp + '\'' +
                ", terminalNum=" + terminalNum +
                ", updateTime=" + updateTime +
                ", stationId=" + stationId +
                ", stationIp='" + stationIp + '\'' +
                '}';
    }
}
