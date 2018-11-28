package com.zkxh.demo.common.util;

import ICT.RSSI;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zkxh.demo.model.base_station.BaseStation;
import com.zkxh.demo.model.teminal_road.TeminalRoad;
import com.zkxh.demo.service.base_station.BaseStationService;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author wangshuwen
 * @Description:实时定位
 * @Date 2018/11/27/18:36
 */
public class RSTLUtil {
    @Resource
    private static BaseStationService baseStationService;

    public static TeminalRoad locateConvert(int terminalId, int stationId1, int stationId2, double wifiStrength1, double wifiStrength2){
        TeminalRoad teminalRoad = new TeminalRoad();
        teminalRoad.setCreatetime(new Date());
        teminalRoad.setStaffTeminalId(terminalId);
        teminalRoad.setStationId1(stationId1);
        teminalRoad.setStationId2(stationId2);
        teminalRoad.setWifiStrength1(wifiStrength1);
        teminalRoad.setWifiStrength2(wifiStrength2);

        BaseStation station1=baseStationService.findBaseStationById(stationId1);
        BaseStation station2=baseStationService.findBaseStationById(stationId2);
        if(station1!=null&&station2!=null){
            Double positionY1 = station1.getPositionY();
            Double positionY2 = station2.getPositionY();
            //wifiStength是负为距离station1的距离，为正是距离station2的距离
          if(positionY2>positionY1){
              double distance = RSSI.locationCal(positionY1, positionY2, wifiStrength1, wifiStrength2);

              if(wifiStrength1<0&&wifiStrength2<0){
                  teminalRoad.setPositionX(station1.getPositionX());
                  teminalRoad.setPositionY(positionY1+distance);
                  teminalRoad.setPositionZ(station1.getPositionZ());
              }else if(wifiStrength1>0&&wifiStrength2>0){
                  teminalRoad.setPositionX(station2.getPositionX());
                  teminalRoad.setPositionY(positionY2-distance);
                  teminalRoad.setPositionZ(station2.getPositionZ());
              }else{
                  return null;
              }

          }

        }


        return teminalRoad;
    }

}
