package com.zkxh.demo.service.gas;

import com.zkxh.demo.model.chat.ChatMsg;
import com.zkxh.demo.netty.data.request.RequestData;

import java.text.ParseException;

/**
 * @ClassName UpLoadService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 13:09
 * @Vserion v0.0.1
 */

public interface UpLoadService {

    //    void sendGasInfoToQueue(UpLoadGasDto upLoadGasDto) throws ParseException;
    void sendGasInfoToQueue(RequestData requestData) throws ParseException;

    void sendSelfCheckResult(RequestData customMsg);

    void sendUpLoadIp(RequestData customMsg);

    void sendHandWareVersion(RequestData customMsg);

    void sendSoftWareVersion(RequestData customMsg);

    void sendVoice(ChatMsg chatMsg);
}
