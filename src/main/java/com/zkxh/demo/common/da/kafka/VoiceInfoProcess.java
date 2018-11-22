package com.zkxh.demo.common.da.kafka;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.zkxh.demo.dao.chat.ChatMsgMapper;
import com.zkxh.demo.model.chat.ChatMsg;
import com.zkxh.demo.model.rt_gas.RtGasInfo;
import com.zkxh.demo.netty.utils.ConstantValue;
import com.zkxh.demo.netty.utils.DateConvert;
import com.zkxh.demo.service.gas.GasInfoService;
import com.zkxh.demo.service.staff.StaffService;
import com.zkxh.demo.vo.resp.GasWSRespVO;
import com.zkxh.demo.vo.resp.VoiceWSRespVo;
import com.zkxh.demo.websocket.WSServer;
import com.zkxh.demo.websocket.WSVoiceServer;
import io.swagger.models.auth.In;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * @ClassName VoiceInfoProcess
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/11 15:21
 * @Vserion v0.0.1
 */
@Component
public class VoiceInfoProcess {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    WSVoiceServer wsVoiceServer;

    @Resource
    private StaffService staffService;

    @Resource
    private GasInfoService gasInfoService;

    @Resource
    private ChatMsgMapper chatMsgMapper;

    @Resource
    KafkaSender kafkaSender;

    @KafkaListener(id = "VoiceInfoToDataBase", topics = "voice.tut")
    public void processVoiceInfoToDB(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            //获取消息
            Object message = kafkaMessage.get();

            String str = (String) message;
            JSONObject jsonObject = JSON.parseObject(str);

            String postIp = jsonObject.getString("postIp");
            String stationIp = jsonObject.getString("stationIp");
            Date postTime = jsonObject.getDate("postTime");
            Date convertTime = jsonObject.getDate("convertTime");
            String postMsg = jsonObject.getString("postMsg");
            boolean status = jsonObject.getBoolean("status");
            Integer terminalId = jsonObject.getInteger("terminalId");

            ChatMsg chatMsg = new ChatMsg();
            chatMsg.setPostIp(postIp);
            chatMsg.setStationIp(stationIp);
            chatMsg.setPostTime(postTime);
            chatMsg.setConvertTime(convertTime);
            chatMsg.setPostMsg(postMsg);
            chatMsg.setStatus(status);
            chatMsg.setTerminalId(terminalId);
            Map<String, Object> map = staffService.findStaffIdByTerminalId(terminalId);
            Integer staffId = (Integer) map.get("staff_id");
//            chatMsg.setTurnSend(false);
            chatMsg.setPostUserId(staffId);
            chatMsgMapper.insertSelective(chatMsg);
//            System.out.println("msgId: " + msgId);
//            try {
//
//                WSVoiceServer.sendInfo(JSON.toJSONString(chatMsg));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);
            kafkaSender.send(chatMsg, "voiceToWs.tut");
        }
    }

    @KafkaListener(id = "VoiceInfoToWebSocket", topics = "voiceToWs.tut")
    public void processVoiceInfoToWS(ConsumerRecord<?, ?> record, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) throws ParseException {
        //判断是否NULL
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());

        if (kafkaMessage.isPresent()) {
            //获取消息
            Object message = kafkaMessage.get();

            String str = (String) message;
            JSONObject jsonObject = JSON.parseObject(str);
            logger.info(jsonObject.toJSONString());
            logger.info("SEND WS");
            String postIp = jsonObject.getString("postIp");
            String stationIp = jsonObject.getString("stationIp");
            Date postTime = jsonObject.getDate("postTime");
            Date convertTime = jsonObject.getDate("convertTime");
            String postMsg = jsonObject.getString("postMsg");
            boolean status = jsonObject.getBoolean("status");
            Integer terminalId = jsonObject.getInteger("terminalId");

            //生成语音文件路径
            String voiceUrl = postMsg.replace(ConstantValue.basePath, ConstantValue.webBaseUrl);

            GasWSRespVO staffInfo = staffService.findStaffNameByTerminalId(terminalId);

            Map<String, Object> resultMap = staffService.findStaffGroupAndDeptByStaffId(staffInfo.getStaffId());

            GasWSRespVO gasWSRespVO = gasInfoService.findGasInfoByStaffIdAndTerminalId(terminalId);

            gasWSRespVO.setStaffId(staffInfo.getStaffId());
            gasWSRespVO.setStaffName(staffInfo.getStaffName());
            //TODO 封装WS 数据

            VoiceWSRespVo voiceWSRespVo = new VoiceWSRespVo();
            voiceWSRespVo.setStaffId(gasWSRespVO.getStaffId());
            voiceWSRespVo.setStatus(status);
            voiceWSRespVo.setGasWSRespVO(gasWSRespVO);

            voiceWSRespVo.setVoiceUrl(voiceUrl);
            voiceWSRespVo.setUploadTime(postTime);
            voiceWSRespVo.setTerminalId(terminalId);

            voiceWSRespVo.setDeptName((String) resultMap.get("dept_name"));
            voiceWSRespVo.setGroupName((String) resultMap.get("group_name"));

            System.out.println("json:" + JSONObject.toJSONString(voiceWSRespVo));
            try {
                WSVoiceServer.sendInfo(JSONObject.toJSONString(voiceWSRespVo));
            } catch (IOException e) {
                e.printStackTrace();
            }

            logger.info("Receive： +++++++++++++++ Topic:" + topic);
            logger.info("Receive： +++++++++++++++ Record:" + record);
            logger.info("Receive： +++++++++++++++ Message:" + message);
        }
    }
}
