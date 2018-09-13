package com.zkxh.demo.service.gas;

import com.zkxh.demo.dto.UpLoadGasDto;

import java.text.ParseException;

/**
 * @ClassName UpLoadService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/12 13:09
 * @Vserion v0.0.1
 */

public interface UpLoadService {

    public void sendGasInfoToQueue(UpLoadGasDto upLoadGasDto) throws ParseException;
}
