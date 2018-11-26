package com.zkxh.demo.dto.zone;

import com.zkxh.demo.common.base.entity.BaseEntity;
import com.zkxh.demo.model.zone.Zone;

import java.util.List;

/**
 * @ClassName ZoneDto
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/23 10:00
 * @Vserion v0.0.1
 */

public class ZoneDto extends BaseEntity {

    private Zone zone;

    private int areaNum;    //空间数

    public ZoneDto() {
    }

    public ZoneDto(Zone zone, int areaNum) {
        this.zone = zone;
        this.areaNum = areaNum;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public int getAreaNum() {
        return areaNum;
    }

    public void setAreaNum(int areaNum) {
        this.areaNum = areaNum;
    }
}
