package com.zkxh.demo.service.gas.impl;

import com.zkxh.demo.common.base.log.BaseLog;
import com.zkxh.demo.common.da.kafka.KafkaSender;
import com.zkxh.demo.common.util.convert.DateConvert;
import com.zkxh.demo.dao.rt_gas.RtGasInfoMapper;
import com.zkxh.demo.dto.UpLoadGasDto;
import com.zkxh.demo.model.chat.ChatMsg;
import com.zkxh.demo.netty.data.GasInfo;
import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.service.gas.UpLoadService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.Date;

/**
 * @ClassName UpLoadServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 13:16
 * @Vserion v0.0.1
 */

@Service
public class UpLoadServiceImpl extends BaseLog implements UpLoadService {


    @Resource
    RtGasInfoMapper rtGasInfoMapper;

    //@Autowired
    //KafkaTemplate<String, Object> kafkaTemplate;

    @Resource
    KafkaSender kafkaSender;


    @Override
    public void sendGasInfoToQueue(RequestData requestData) throws ParseException {


        byte[] body = requestData.getBody();

        GasInfo gasInfo = new GasInfo();
        gasInfo.setCo(((long) (((body[0] & 0xff) << 8) + (body[1] & 0xff)) / 10.0));
        gasInfo.setCoFlag(body[2]);
        gasInfo.setCo2(((long) (((body[3] & 0xff) << 8) + (body[4] & 0xff)) / 10.0));
        gasInfo.setCo2Flag(body[5]);
        gasInfo.setO2(((long) (((body[6] & 0xff) << 8) + (body[7] & 0xff)) / 10.0));
        gasInfo.setO2Flag(body[8]);
        gasInfo.setCh4(((long) (((body[9] & 0xff) << 8) + (body[10] & 0xff)) / 10.0));
        gasInfo.setCh4Flag(body[11]);
        gasInfo.setT(((long) (((body[12] & 0xff) << 8) + (body[13] & 0xff)) / 10.0));
        gasInfo.settFlag(body[14]);
        gasInfo.setH(((long) (((body[15] & 0xff) << 8) + (body[16] & 0xff)) / 10.0));
        gasInfo.sethFlag(body[17]);

        UpLoadGasDto upLoadGasDto = new UpLoadGasDto();

        upLoadGasDto.setSequenceId(requestData.getSequenceId());

        upLoadGasDto.setRT(DateConvert.convert(requestData.getTime(), 19));

        upLoadGasDto.setTerminalIp(requestData.getTerminalIp());

        upLoadGasDto.setCreateTime(DateConvert.convert(new Date(), 19));

        upLoadGasDto.setStationId(requestData.getStationId());

        upLoadGasDto.setStationIp(requestData.getStationIp());

        upLoadGasDto.setTerminalId(requestData.getTerminalId());

        upLoadGasDto.setGasInfo(gasInfo);

        kafkaSender.send(upLoadGasDto, "kafka.tut");
    }


    @Override
    public void sendSelfCheckResult(RequestData customMsg) {

    }

    @Override
    public void sendUpLoadIp(RequestData customMsg) {
        kafkaSender.send(customMsg, "updateIp.tut");
    }

    @Override
    public void sendHandWareVersion(RequestData customMsg) {

    }

    @Override
    public void sendSoftWareVersion(RequestData customMsg) {

    }

    @Override
    public void sendVoice(ChatMsg chatMsg) {
        kafkaSender.send(chatMsg, "voice.tut");
    }
}
