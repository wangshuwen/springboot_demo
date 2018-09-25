package com.zkxh.demo.common.da.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkxh.demo.common.util.convert.DateConvert;
import com.zkxh.demo.dao.rt_gas.RtGasInfoMapper;
import com.zkxh.demo.dto.UpLoadGasDto;
import com.zkxh.demo.model.rt_gas.RtGasInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

/**
 * @ClassName GasInfoToDataBase
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 14:25
 * @Vserion v0.0.1
 */

@Component
public class GasInfoToDataBase {

    @Resource
    RtGasInfoMapper rtGasInfoMapper;

    Logger logger = LoggerFactory.getLogger(GasInfoToDataBase.class);

    /**
     * 监听kafka.tut 的 topic
     *
     * @param record
     * @param topic  topic
     */
    @KafkaListener(id = "GasInfoToDataBase", topics = "kafka.tut")
    public void listen(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws ParseException {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            //获取消息
            Object message = kafkaMessage.get();

            String str = (String) message;
            JSONObject jsonObject = JSON.parseObject(str);

            double co = jsonObject.getDouble("co");
            double co2 = jsonObject.getDouble("co2");
            double t = jsonObject.getDouble("t");
            double h = jsonObject.getDouble("h");
            double ch4 = jsonObject.getDouble("ch4");
            double o2 = jsonObject.getDouble("o2");

            String rt = jsonObject.getString("rT");
            String createTime = jsonObject.getString("createTime");
            String sequenceId = jsonObject.getString("sequenceId");
            String stationIp = jsonObject.getString("stationIp");
            String stationId = jsonObject.getString("stationId");
            String terminalId = jsonObject.getString("terminalId");
            String terminalIp = jsonObject.getString("terminalIp");

            //封装插入数据库
            RtGasInfo rtGasInfo = new RtGasInfo();

            rtGasInfo.settHumidity(h);
            rtGasInfo.settTemperature(t);
            rtGasInfo.setTerminalRealTime(DateConvert.convertStringToDate(rt, 19));
            rtGasInfo.setTerminalIp(terminalIp);
            rtGasInfo.setTerminalId(terminalId);
            rtGasInfo.setStationIp(stationIp);
            rtGasInfo.setStationId(stationId);
            rtGasInfo.setO2(o2);
            rtGasInfo.setCo(co);
            rtGasInfo.settCo2(co2);
            rtGasInfo.setCh4(ch4);
            rtGasInfo.setInfoType(false);
            rtGasInfo.setSequenceId(sequenceId);
            rtGasInfo.setCreateTime(DateConvert.convertStringToDate(createTime, 19));

//            rtGasInfo.setCh4(upLoadGasDto ''to.getH());

            rtGasInfoMapper.insert(rtGasInfo);

            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);
        }
    }


}
