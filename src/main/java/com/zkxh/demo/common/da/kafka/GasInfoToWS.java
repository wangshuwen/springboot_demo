package com.zkxh.demo.common.da.kafka;

import com.alibaba.fastjson.JSON;
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

            GasWSRespVO gasWSRespVO = new GasWSRespVO();
            gasWSRespVO.setO2(11.1);
//            gasWSRespVO.setCh4(upLoadGasDto.getCh4());
//            gasWSRespVO.setCo(upLoadGasDto.getCo());
//            gasWSRespVO.setCo2(upLoadGasDto.getCo2());
//            gasWSRespVO.setO2(upLoadGasDto.getO2());
//            gasWSRespVO.setHumidity(upLoadGasDto.getH());
//            gasWSRespVO.setTemperature(upLoadGasDto.getT());
            //TODO 缺少字段 待完善
            // wsServer.(gasWSRespVO);
            try {
                WSServer.sendInfo(gasWSRespVO.toString());
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeOtherException(ResultEnum.WEBSOCKET_SEND_ERROR);
            }

            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);

        }
    }
}
