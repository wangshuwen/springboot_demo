package com.zkxh.demo.service.call.impl;

import com.zkxh.demo.dao.terminal.TerminalUpdateIpMapper;
import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.data.response.ResponseData;
import com.zkxh.demo.netty.request.Client;
import com.zkxh.demo.netty.utils.*;
import com.zkxh.demo.service.call.CallService;
import com.zkxh.demo.service.terminal.TerminalService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName CallServiceImpl
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/11 9:03
 * @Vserion v0.0.1
 */
@Service
public class CallServiceImpl implements CallService {

//    private static final Integer terminalId = 65539;
//    private static final String terminalIp = "1.68";
//    private static final Integer stationId = 1;
//    private static final String stationIp = "1.251";


    @Resource
    private TerminalService terminalService;

    @Resource
    private TerminalUpdateIpMapper terminalUpdateIpMapper;

    @Override
    public boolean callStaffByStaffId(MultipartFile wavFile, Integer staffId, Integer userId) {

        boolean f = false;

        Integer terminalId = terminalService.findTerminalInfoByStaffId(staffId);

        Map<String, Object> terminalInfo = terminalUpdateIpMapper.selectTerminalIpInfoByTerminalId(terminalId);

        String stationIp = (String) terminalInfo.get("station_ip");

        String stationIps[] = stationIp.split("\\.");


        Integer stationIp1 = Integer.parseInt(stationIps[0]);
        Integer stationIp2 = Integer.parseInt(stationIps[1]);


        String terminalIp = (String) terminalInfo.get("terminal_ip");


        String terminalIps[] = terminalIp.split("\\.");
        Integer terminalIp1 = Integer.parseInt(terminalIps[0]);
        Integer terminalIp2 = Integer.parseInt(terminalIps[1]);

//        Integer terminalId = (Integer)terminalInfo.get("terminal_num");
        Integer stationId = (Integer) terminalInfo.get("station_id");

        ResponseData responseData = new ResponseData();

        RequestData requestData = new RequestData();
        responseData.setCode((byte) 0x55);

        requestData.setType(ConstantValue.MSG_HEADER_FREAME_HEAD);
        requestData.setCmd((byte) (ConstantValue.MSG_HEADER_COMMAND_ID_SEND_VOICE));
        requestData.setLength(544);
        requestData.setStationId(stationId);
        requestData.setStationIp1(stationIp1);
        requestData.setStationIp2(stationIp2);
        requestData.setTerminalId(terminalId);
        requestData.setTerminalIp1(terminalIp1);
        requestData.setTerminalIp2(terminalIp2);
        requestData.setSequenceId(1);

        requestData.setResult((byte) ConstantValue.MSG_BODY_RESULT_SUCCESS);
        requestData.setNdName(ConstantValue.MSG_BODY_NODE_NAME_VOICE_DATA);
        requestData.setTerminalIp(terminalIp);
        requestData.setStationIp(stationIp);

        //Speex
        StringBuffer folderName = new StringBuffer(ConstantValue.basePath);
        folderName.append(terminalId).append(File.separator);
        StringBuffer fileName = new StringBuffer(DateConvert.convert(new Date(), 15));
        fileName.append(terminalId).append(requestData.getSequenceId());
        FileUtils.createFile(folderName.toString(), fileName.toString(), FileType.WAV);
        File sendFile = new File(folderName.toString() + fileName.toString() + "." + FileType.WAV);
        try {
            wavFile.transferTo(sendFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        String tempUrl = folderName.toString()+ fileName.toString()+"."+FileType.PCM;
        String realUrl = folderName.toString() + fileName.toString() + "." + FileType.WAV;
        //       try {

        //       FileUtils.createFile(folderName.toString(), fileName.toString(), FileType.WAV);
//            PcmToWavConvert.convert(tempUrl, realUrl);
        //         FileUtils.removeFile(tempUrl);
        //     } catch (IOException e) {
        //        e.printStackTrace();
        //    }

        File send = new File(realUrl);
        FileInputStream inputStream = null;
        int i = 1;
        int len;
        try {
            inputStream = new FileInputStream(send);

            byte[] bo = new byte[512];

//            long start = System.currentTimeMillis();
            while (true) {
                len = inputStream.read(bo);

                System.out.println(len);
                if (len < 512)
                    break;

                requestData.setBody(bo);
                requestData.setCount(i);
                System.out.println("\n===========================");
                for (int j = 0; j < bo.length; j++) {
                    System.out.printf("0x%02x ", bo[j]);
                }
                System.out.println("\n---------------------------");
                System.out.println("\n发送的第 " + i + " 个语音包");
                System.out.println("\n包总长度为： " + requestData.getLength());
                System.out.println("\n发送的语音数据： " + requestData.toString());
                System.out.println("\n===========================");
                responseData.setCustomMsg(requestData);
                Client.sendCmd(responseData);
                try {
                    Thread.sleep((long) 15);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
            requestData.setLength(36);
            requestData.setCount(i);
            byte[] b = new byte[4];
            b[0] = (byte) 0x55;
            b[1] = (byte) 0x66;
            b[2] = (byte) 0x77;
            b[3] = (byte) 0x88;
            requestData.setBody(b);
            responseData.setCustomMsg(requestData);
            Client.sendCmd(responseData);
//            long end = System.currentTimeMillis();
//            System.out.println("时间差：" + (int)(end - start));
            System.out.println("\n发送的第 " + i + " 个语音包");
            System.out.println("\n包总长度为： " + requestData.getLength());
            System.out.println("\n发送的语音数据： " + requestData.toString());
            System.out.println("\n===========================");
            f = true;
            i = 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
//        responseData.setCode();
//        channel.write(responseData);
//        channel.flush();
        return f;
    }

    @Override
    public boolean pingTerminal(Integer staffId) {

        Integer terminalId = terminalService.findTerminalInfoByStaffId(staffId);

        Map<String, Object> terminalInfo = terminalUpdateIpMapper.selectTerminalIpInfoByTerminalId(terminalId);

        String stationIp = (String) terminalInfo.get("station_ip");

        String stationIps[] = stationIp.split("\\.");

        Integer stationIp1 = Integer.parseInt(stationIps[0]);
        Integer stationIp2 = Integer.parseInt(stationIps[1]);

        String terminalIp = (String) terminalInfo.get("terminal_ip");

        String terminalIps[] = stationIp.split("\\.");
        Integer terminalIp1 = Integer.parseInt(terminalIps[0]);
        Integer terminalIp2 = Integer.parseInt(terminalIps[1]);

//        Integer terminalId = (Integer)terminalInfo.get("terminal_num");
        Integer stationId = (Integer) terminalInfo.get("station_id");
        RequestData requestData = new RequestData();
        requestData.setType(ConstantValue.MSG_HEADER_FREAME_HEAD);
        requestData.setTerminalId(terminalId);
        requestData.setStationId(stationId);
        requestData.setTerminalIp1(terminalIp1);
        requestData.setTerminalIp2(terminalIp2);
        requestData.setStationIp1(stationIp1);
        requestData.setStationIp2(stationIp2);
        requestData.setTerminalIp(terminalIp);
        requestData.setLength(30);
        requestData.setSequenceId(111);
        requestData.setCmd(ConstantValue.MSG_HEADER_COMMAND_ID_SEARCH);
        requestData.setResult((byte) -1);
        requestData.setNodeCount((byte) 0);
        requestData.setNdName(ConstantValue.MSG_BODY_NODE_NAME_CHECK_ONLINE);
        ResponseData responseData = new ResponseData();
        responseData.setCode((byte) -1);
        responseData.setCustomMsg(requestData);
        Client.sendCmd(responseData);
        return true;
    }
}
