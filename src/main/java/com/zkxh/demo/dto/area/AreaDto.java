package com.zkxh.demo.dto.area;

import com.zkxh.demo.common.base.entity.BaseEntity;
import com.zkxh.demo.model.area.Area;

/**
 * @ClassName AreaDto
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/23 15:09
 * @Vserion v0.0.1
 */

public class AreaDto extends BaseEntity {
    private Area area;

    private Integer baseStationNum;


    public AreaDto() {
    }

    public AreaDto(Area area, Integer baseStationNum) {
        this.area = area;
        this.baseStationNum = baseStationNum;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getBaseStationNum() {
        return baseStationNum;
    }

    public void setBaseStationNum(Integer baseStationNum) {
        this.baseStationNum = baseStationNum;
    }

    @Override
    public String toString() {
        return "AreaDto{" +
                "area=" + area +
                ", baseStationNum=" + baseStationNum +
                '}';
    }
}
