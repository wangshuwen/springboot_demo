package com.zkxh.demo.netty.handle;

import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ProcessVoice
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/29 13:11
 * @Vserion v0.0.1
 */

public class ProcessVoice {

    private static Map<String, String> mapOfSequenceId = new ConcurrentHashMap<>();

    Logger logger = LoggerFactory.getLogger(ProcessVoice.class);

    public String process(RequestData requestData) {
        Integer sequenceId = requestData.getSequenceId();
        Integer terminalId = requestData.getTerminalId();
        String terminalIp = requestData.getTerminalIp();
        Integer length = requestData.getLength();
//        int cmd = requestData.getCmd();
        byte[] body = requestData.getBody();
//        int ndName = ((body[0] & 0xff) << 8) + (body[1] & 0xff);
        int bodyLength = body.length;
        int count = ((body[4] & 0xff) << 8) + ((body[5] & 0xff));
        logger.info("来自[" + requestData.getTerminalId() + "]的第[" + count + "]个语音数据包");

        //创建根目录文件夹
        StringBuffer folderName = new StringBuffer(ConstantValue.basePath);
        folderName.append(requestData.getTerminalId()); //根路径

        String currentTime = DateConvert.convert(new Date(), 15);

        //创建文件名称  格式 ： 时间 + 终端ID + 序列号
        StringBuffer fileName = new StringBuffer(); //文件夹
        fileName.append(currentTime).append(requestData.getTerminalId()).append(requestData.getSequenceId());

        StringBuffer file = new StringBuffer(fileName);
        file.append(".").append(FileType.PCM);  //文件名称

        StringBuffer realPath = new StringBuffer(folderName);
        realPath.append(fileName).append(".").append(FileType.PCM);

        logger.info("文件路径 : [" + folderName.toString() + "] , 文件名称 ：[" + fileName.toString() + "] ，文件类型 [" + FileType.PCM + "]");
        String key = Integer.toString(sequenceId) + terminalId;
        if (!mapOfSequenceId.containsKey(key)) {  //如果不存在，则认为是语音的第一天 也可以利用语音数据包的序号判断 数组的31，32位
            mapOfSequenceId.put(key, currentTime);
            logger.info("当前终端: ID>[" + terminalId + "] || IP>[" + terminalId + "] 当前语音队列中无该终端语音信息，已创建改音频文件");
            FileUtils.createFile(folderName.toString(), fileName.toString(), FileType.PCM);
            WriteFileUtil.writeByteToFile(fileName.toString(), body, 6, bodyLength - 6, true);
        } else {
            logger.info("当前终端: ID>[" + terminalId + "] || IP>[" + terminalIp + "] 当前语音队列中已存在终端语音信息，持续接收中......");
            if (length == 36) {
                //TODO 执行 PCM ==> WAV 函数
                logger.info("当前终端: ID>[" + terminalId + "] || IP>[" + terminalIp + "] 接收语音结束，执行PCM转WAV");

                logger.info("当前终端: ID>[" + terminalId + "] || IP>[" + terminalIp + "] 已被移除语音队列");
                //TODO pcm => wav
                String v = mapOfSequenceId.get(key);
                StringBuffer temp = new StringBuffer(folderName);
                temp.append("/").append(v).append(terminalId).append(sequenceId).append(".").append(FileType.PCM);
                StringBuffer real = new StringBuffer(folderName);
                real.append("\\").append(v).append(terminalId).append(sequenceId).append(".").append(FileType.WAV);
                try {
                    FileUtils.createFile(folderName.toString(), v + terminalId + sequenceId, FileType.WAV);
                    PcmToWavConvert.convert(temp.toString(), real.toString());
                    return real.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mapOfSequenceId.remove(sequenceId);
            }
            //TODO 向文件中写入内容（除第一条外其他内容）
            String val = mapOfSequenceId.get(key);
            System.out.println("获取的val ： " + val);
            //TODO pkg[32]=>最后

            StringBuffer t_path = new StringBuffer(folderName);
            t_path.append("\\").append(val).append(terminalId).append(sequenceId).append(".").append(FileType.PCM);
            WriteFileUtil.writeByteToFile(t_path.toString(), body, 6, bodyLength - 6, true);
        }
        return "error";

    }

    public static void main(String[] args) throws IOException {

//        ProcessVoice processVoice = new ProcessVoice();
//        RequestData requestData = new RequestData();
//        processVoice.process(requestData);

        String path = "D:\\a\\b\\";
        File file = new File(path);
        boolean r = false;
        if (!file.exists()) {
            r = file.mkdirs();
        }
        System.out.println(r);
        File file1 = new File(file, "v.txt");
        file1.createNewFile();
    }
}
