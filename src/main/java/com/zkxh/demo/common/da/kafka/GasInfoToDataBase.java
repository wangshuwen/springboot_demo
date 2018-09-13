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


            RtGasInfo rtGasInfo = new RtGasInfo();
//            rtGasInfo.setCh4(upLoadGasDto.getCh4());
//            rtGasInfo.setCo(upLoadGasDto.getCo());
//            rtGasInfo.setCreateTime(new Date());
//            rtGasInfo.setO2(upLoadGasDto.getO2());
//            rtGasInfo.setSequenceId(upLoadGasDto.getSequenceId());
//            rtGasInfo.setStationId(upLoadGasDto.getStationId());
//            rtGasInfo.setStationIp(upLoadGasDto.getStationIp());
//            rtGasInfo.setTerminalId(upLoadGasDto.getTerminalId());
//            rtGasInfo.setTerminalIp(upLoadGasDto.getTerminalIp());
//            rtGasInfo.setTerminalRealTime(DateConvert.convertStringToDate(upLoadGasDto.getCreateTime(),19));
//            rtGasInfo.settTemperature(upLoadGasDto.getT());
//            rtGasInfo.settHumidity(upLoadGasDto.getH());

            rtGasInfoMapper.insert(rtGasInfo);

            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);
        }
    }


}
