package com.zkxh.demo.common.da.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.handle.RuntimeOtherException;
import com.zkxh.demo.service.staff.StaffService;
import com.zkxh.demo.vo.resp.GasWSRespVO;
import com.zkxh.demo.websocket.WSServer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @ClassName GasWarnInfoToWS
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/22 16:16
 * @Vserion v0.0.1
 */
@Component
public class GasWarnInfoToWS {

    @Resource
    private StaffService staffService;
    @Resource
    WSServer wsServer;

    public static List<GasWSRespVO> list = new ArrayList<GasWSRespVO>();

    Logger logger = LoggerFactory.getLogger(GasInfoToWS.class);

    /**
     * 监听kafka.tut 的 topic
     *
     * @param record
     * @param topic  topic
     */
    @KafkaListener(id = "GasWarnInfoToWeb", topics = "warn_kafka.tut")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            //获取消息
            Object message = kafkaMessage.get();
            String str = (String) message;
            JSONObject jsonObject = JSON.parseObject(str);
            JSONObject gasInfo = jsonObject.getJSONObject("gasInfo");

            double co = gasInfo.getDouble("co");
            Integer coFlag = gasInfo.getInteger("coFlag");
            double co2 = gasInfo.getDouble("co2");
            Integer co2Flag = gasInfo.getInteger("co2Flag");
            double t = gasInfo.getDouble("t");
            Integer tFlag = gasInfo.getInteger("tFlag");
            double h = gasInfo.getDouble("h");
            Integer hFlag = gasInfo.getInteger("hFlag");
            double ch4 = gasInfo.getDouble("ch4");
            Integer ch4Flag = gasInfo.getInteger("ch4Flag");
            double o2 = gasInfo.getDouble("o2");
            Integer o2Flag = gasInfo.getInteger("o2Flag");

            Date rt = jsonObject.getDate("rT");
            Date createTime = jsonObject.getDate("createTime");
            Integer sequenceId = jsonObject.getInteger("sequenceId");
            String stationIp = jsonObject.getString("stationIp");
            Integer stationId = jsonObject.getInteger("stationId");
            Integer terminalId = jsonObject.getInteger("terminalId");
            String terminalIp = jsonObject.getString("terminalIp");

            GasWSRespVO staff = staffService.findStaffNameByTerminalId(terminalId);
            //TODO 根据 terminalId 查找人名称
            //            System.out.println(co + " co " + ch4 + "  t  " + t + " h " + h + "   co2 " + co2 + "  o2  " + o2);
            GasWSRespVO gasWSRespVO = new GasWSRespVO();
            gasWSRespVO.setO2(o2);
            gasWSRespVO.setO2_type(o2Flag);
            gasWSRespVO.setTemperature(t);
            gasWSRespVO.setTemperature_type(tFlag);
            gasWSRespVO.setHumidity(h);
            gasWSRespVO.setHumidity_type(hFlag);
            gasWSRespVO.setCo2(co2);
            gasWSRespVO.setCo2_type(co2Flag);
            gasWSRespVO.setCh4(ch4);
            gasWSRespVO.setCh4_type(ch4Flag);
            gasWSRespVO.setSequenceId(sequenceId);
            gasWSRespVO.setCo(co);
            gasWSRespVO.setCo_type(coFlag);
            gasWSRespVO.setStaffId(staff.getStaffId());
            gasWSRespVO.setStaffName(staff.getStaffName());
            gasWSRespVO.setRt(rt);
            gasWSRespVO.setCreateTime(createTime);

            list.add(gasWSRespVO);

            JSONArray jsonArray = new JSONArray();


            if (list.size() > 10) {
                try {

                    for (GasWSRespVO vo : list) {
                        jsonArray.add(vo);
                    }
                    WSServer.sendInfo(jsonArray.toJSONString());
                    list.clear();
                    jsonArray.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeOtherException(ResultEnum.WEBSOCKET_SEND_ERROR);
                }
            }

            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);

        }
    }
}
