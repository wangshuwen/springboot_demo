package com.zkxh.demo.common.da.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zkxh.demo.dao.terminal.TerminalUpdateIpMapper;
import com.zkxh.demo.model.terminal.TerminalUpdateIp;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Optional;

/**
 * @ClassName UpdateIpProcess
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/19 14:31
 * @Vserion v0.0.1
 */
@Component
public class UpdateIpProcess {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    KafkaSender kafkaSender;

    @Resource
    TerminalUpdateIpMapper terminalUpdateIpMapper;

    //    @Transactional
    @KafkaListener(id = "UpdateIpToDB", topics = "updateIp.tut")
    public void sendUpdateIp(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            //获取消息
            Object message = kafkaMessage.get();

            String str = (String) message;
            JSONObject jsonObject = JSON.parseObject(str);

            String terminalIp = jsonObject.getString("terminalIp");
            String stationIp = jsonObject.getString("stationIp");
            Integer terminalId = jsonObject.getInteger("terminalId");
            Integer stationId = jsonObject.getInteger("stationId");

            TerminalUpdateIp terminalUpdateIp = new TerminalUpdateIp();
            terminalUpdateIp.setStationId(stationId);
            terminalUpdateIp.setStationIp(stationIp);
            terminalUpdateIp.setTerminalIp(terminalIp);
            terminalUpdateIp.setTerminalNum(terminalId);
            terminalUpdateIp.setUpdateTime(new Date());


            /**
             * @description 根据terminalId 检查是否存在terminalId;
             *                  如果存在则更新
             *                  若不存在直接插入
             * @date 14:55 2018/10/19
             * @auther lifeng
             **/
            boolean isExits = terminalUpdateIpMapper.checkTerminalIdIsNotExist(terminalId);
            if (isExits) {
                logger.info("终端IP已存在，更新IP");
                terminalUpdateIpMapper.updateIpInfoByTerminalId(terminalUpdateIp);
            } else {
                logger.info("终端IP不存在，新增IP");
                terminalUpdateIpMapper.insertSelective(terminalUpdateIp);
            }
            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);
        }
    }
}
