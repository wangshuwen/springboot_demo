package com.zkxh.demo.service.gas.impl;

import com.alibaba.fastjson.JSON;
import com.zkxh.demo.common.base.log.BaseLog;
import com.zkxh.demo.dao.rt_gas.RtGasInfoMapper;
import com.zkxh.demo.dto.UpLoadGasDto;
import com.zkxh.demo.service.gas.UpLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.text.ParseException;

/**
 * @ClassName UpLoadServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 13:16
 * @Vserion v0.0.1
 */

@Service
public class UpLoadServiceImpl extends BaseLog implements UpLoadService {


    @Autowired
    RtGasInfoMapper rtGasInfoMapper;

    @Autowired
    KafkaTemplate<String, Object> kafkaTemplate;

    public void sendGasInfoToQueue(UpLoadGasDto upLoadGasDto) throws ParseException {

        String jsonObj = JSON.toJSONString(upLoadGasDto);
        logger.info("------------ message = {}", jsonObj);
        //发送消息
        //ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka.tut", upLoadGasDto);
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("kafka.tut", jsonObj);

        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.info("Produce: The message failed to be sent:" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //TODO 业务处理
                logger.info("Produce: The message was sent successfully:");
                logger.info("Produce: _+_+_+_+_+_+_+ result: " + stringObjectSendResult.toString());
            }
        });
    }
}
