package com.zkxh.demo.common.da.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.handle.RuntimeOtherException;
import com.zkxh.demo.dto.UpLoadGasDto;
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
import java.util.List;
import java.util.Optional;

/**
 * @ClassName GasInfoToWS
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 14:26
 * @Vserion v0.0.1
 */
@Component
public class GasInfoToWS {

    // @Resource
    //  WSServer wsServer;
    public static List<GasWSRespVO> list = new ArrayList<GasWSRespVO>();

    Logger logger = LoggerFactory.getLogger(GasInfoToWS.class);

    /**
     * 监听kafka.tut 的 topic
     *
     * @param record
     * @param topic  topic
     */
    @KafkaListener(id = "GasInfoToWeb", topics = "kafka.tut")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            //获取消息
            Object message = kafkaMessage.get();
            String str = (String) message;
            JSONObject jsonObject = JSON.parseObject(str);

            double co = jsonObject.getDouble("co");
            int co_type = jsonObject.getInteger("co_type");
            double co2 = jsonObject.getDouble("co2");
            int co2_type = jsonObject.getInteger("co2_type");
            double t = jsonObject.getDouble("t");
            int t_type = jsonObject.getInteger("t_type");
            double h = jsonObject.getDouble("h");
            int h_type = jsonObject.getInteger("h_type");
            double ch4 = jsonObject.getDouble("ch4");
            int ch4_type = jsonObject.getInteger("ch4_type");
            double o2 = jsonObject.getDouble("o2");
            int o2_type = jsonObject.getInteger("o2_type");

            String rt = jsonObject.getString("rT");
            String createTime = jsonObject.getString("createTime");
            String sequenceId = jsonObject.getString("sequenceId");
            String stationIp = jsonObject.getString("stationIp");
            String stationId = jsonObject.getString("stationId");
            String terminalId = jsonObject.getString("terminalId");
            String terminalIp = jsonObject.getString("terminalIp");


            System.out.println(co + " co " + ch4 + "  t  " + t + " h " + h + "   co2 " + co2 + "  o2  " + o2);

            GasWSRespVO gasWSRespVO = new GasWSRespVO();
            gasWSRespVO.setO2(o2);
            gasWSRespVO.setO2_type(o2_type);
            gasWSRespVO.setTemperature(t);
            gasWSRespVO.setTemperature_type(t_type);
            gasWSRespVO.setHumidity(h);
            gasWSRespVO.setHumidity_type(h_type);
            gasWSRespVO.setCo2(co2);
            gasWSRespVO.setCo2_type(co2_type);
            gasWSRespVO.setCh4(ch4);
            gasWSRespVO.setCh4_type(ch4_type);
            gasWSRespVO.setSequenceId(sequenceId);
            gasWSRespVO.setCo(co);
            gasWSRespVO.setCo_type(co_type);
            gasWSRespVO.setStaffName("张三");
            gasWSRespVO.setRt(rt);
            gasWSRespVO.setCreateTime(createTime);

            list.add(gasWSRespVO);

            JSONArray jsonArray = new JSONArray();


            if (list.size() > 10) {
                try {

                    for (GasWSRespVO gasWSRespVO1 : list) {
                        jsonArray.add(gasWSRespVO1);
                    }

                    WSServer.sendInfo(jsonArray.toJSONString());
                    logger.info("" + jsonArray.toJSONString());
                    list.clear();
                    jsonArray.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeOtherException(ResultEnum.WEBSOCKET_SEND_ERROR);
                }
            }

//            gasWSRespVO.setCh4(upLoadGasDto.getCh4());
//            gasWSRespVO.setCo(upLoadGasDto.getCo());
//            gasWSRespVO.setCo2(upLoadGasDto.getCo2());
//            gasWSRespVO.setO2(upLoadGasDto.getO2());
//            gasWSRespVO.setHumidity(upLoadGasDto.getH());
//            gasWSRespVO.setTemperature(upLoadGasDto.getT());
            //TODO 缺少字段 待完善
            // wsServer.(gasWSRespVO);
//            JSONObject json = JSONObject.parseObject();


            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);

        }
    }
}
