package com.zkxh.demo.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName BaseStationPostionVO
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/30 10:21
 * @Vserion v0.0.1
 */

@ApiModel(value = "基站位置信息")
public class BaseStationPositionVO {

    //基站ID
    @ApiModelProperty(value = "基站ID")
    private Integer baseStationId;
    //基站编号
    @ApiModelProperty(value = "基站出厂程序编号")
    private Integer baseStationNum;
    //位置坐标X
    @ApiModelProperty(value = "基站坐标X")
    private double positionX;
    //位置坐标Y
    @ApiModelProperty(value = "基站坐标Y")
    private double positionY;
    //位置坐标Z
    @ApiModelProperty(value = "基站坐标Z")
    private double positionZ;

    public Integer getBaseStationId() {
        return baseStationId;
    }

    public void setBaseStationId(Integer baseStationId) {
        this.baseStationId = baseStationId;
    }

    public Integer getBaseStationNum() {
        return baseStationNum;
    }

    public void setBaseStationNum(Integer baseStationNum) {
        this.baseStationNum = baseStationNum;
    }

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getPositionZ() {
        return positionZ;
    }

    public void setPositionZ(double positionZ) {
        this.positionZ = positionZ;
    }
}
