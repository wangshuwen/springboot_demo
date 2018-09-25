package com.zkxh.demo.socket.server;

import com.zkxh.demo.common.handle.RuntimeOtherException;
import com.zkxh.demo.common.util.convert.DateConvert;
import com.zkxh.demo.common.util.file.FileTypeEnums;
import com.zkxh.demo.common.util.file.FileUtils;
import com.zkxh.demo.dto.UpLoadGasDto;
import com.zkxh.demo.service.gas.UpLoadService;
import com.zkxh.demo.socket.SocketCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName ServerSocket
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/16 17:00
 * @Vserion v0.0.1
 */
@Component
public class SerSocket implements Runnable {

//    private int server_port;
//    public SerSocket(int serverPort) {
//        this.server_port = SERVER_PORT;
//    }

    //日志
    Logger logger = LoggerFactory.getLogger(SerSocket.class);

    private static final int SERVER_PORT = 8087;
    private static SerSocket serSocket;

    @Autowired
    protected UpLoadService upLoadService;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        serSocket = this;
        serSocket.upLoadService = this.upLoadService;
    }

    public SerSocket() {
    }

    @Override
    public void run() {
        try {
            // 服务端在8087端口监听客户端请求的TCP连接

            ServerSocket server = new ServerSocket(SERVER_PORT);
            System.out.println("创建监听" + SERVER_PORT + "的服务端SerSocket");
            Socket client;
            boolean f = true;
            while (f) {
                // 等待客户端的连接，如果没有获取连接
                client = server.accept();
                System.out.println("\n收到客户端指令。");
                // 为每个客户端连接开启一个线程
                new Thread(new RecvServerThread(client)).start();
            }
            server.close();
            System.out.println("server close");
        } catch (Exception e) {
            System.out.println("run ServerThread error.");
            System.out.println(e.toString());
        }
    }


    /**
     * @param
     * @description 回复客户端的内容
     * @date 11:19 2018/9/4
     * @auther lifeng
     * @return
     **/
    class RecvServerThread implements Runnable {

        //定义生成的 pcm 临时文件 内容
        final String in = "C:\\Users\\wr\\Desktop\\demo.pcm";

        private Socket client = null;

        public static final int PACKET_HEAD_LENGTH = 26;//包头长度
        private volatile byte[] bytes = new byte[0];

        public RecvServerThread(Socket client) {
            this.client = client;
        }

        public byte[] mergebyte(byte[] a, byte[] b, int begin, int end) {
            byte[] add = new byte[a.length + end - begin];
            int i = 0;
            for (i = 0; i < a.length; i++) {
                add[i] = a[i];
            }
            for (int k = begin; k < end; k++, i++) {
                add[i] = b[k];
            }
            return add;
        }


        @Override
        public void run() {
            int count = 0;
            OutputStream outStream = null;
            InputStream inStream = null;
            try {
                outStream = client.getOutputStream();
                inStream = client.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }

            while (true) {
                try {

                    // 获取Socket的输出流，用来向客户端发送数据

//                boolean flag = true;
//                System.out.println("开启线程");
//                while (flag) {
//                    // 接收从客户端发送过来的数据
//                    int count = 1024;
//                    byte[] recvCmd = new byte[count];
//                    int recvCount = inStream.read(recvCmd);
//                    if (recvCount == -1) {
//                        System.out.println("接收完毕.");
//                        break;
//                    } else {
//                        System.out.println("length : " + recvCount);
//                        for (int i = 0; i < recvCount; i++) {
//                            System.out.printf("0x%02x ", recvCmd[i]);
//                        }
//                        long message_header_total_length = ((recvCmd[14] & 0xFF) << 8) + ((recvCmd[15] & 0xFF));
//                        int current_length = recvCount;
//                        int total_length = (int)message_header_total_length;
//                        int head_length = 44;
//                        int body_length = total_length - 26;
//
//
//
//                        //处理数据包过程
//                        processPkg(outStream, recvCmd, recvCount);
//
//                    }
//                }
                    System.out.println("数据头长 : " + PACKET_HEAD_LENGTH);
                    System.out.println("临时数组长度  : " + bytes.length);

                    int c = 0;
                    int one_pkg_length = 0;
                    if (bytes.length < PACKET_HEAD_LENGTH) {
                        byte[] head = new byte[PACKET_HEAD_LENGTH - bytes.length];
                        int couter = inStream.read(head);
                        System.out.println("第 " + count + "次 获取IO流中的数据长度 ： " + couter);
                        c = couter;
                        if (c == -1) {
                            System.out.println("客户端断开连接");
                            break;
                        }
                        if (c == 0) {
                            continue;
                        }

                        if (c < PACKET_HEAD_LENGTH) {
                            continue;
                        }
                        bytes = mergebyte(bytes, head, 0, PACKET_HEAD_LENGTH);
//                        if (c == 26) {

//                        }

                    }

                    long message_header_total_length = ((bytes[14] & 0xFF) << 8) + ((bytes[15] & 0xFF));
                    one_pkg_length = (int) message_header_total_length;
                    //数据头读取结束
                    int body_length = one_pkg_length - 26;  //当前数据体 应有的长度
                    System.out.println("数据体应该有的长度 ： " + body_length);

                    byte[] temp = new byte[0];
                    temp = mergebyte(temp, bytes, 0, PACKET_HEAD_LENGTH);
                    String templength = new String(temp);
                    int len = templength.length();
                    if (bytes.length - PACKET_HEAD_LENGTH < body_length) {//不够一个包
                        byte[] body = new byte[body_length + PACKET_HEAD_LENGTH - bytes.length];//剩下应该读的字节(凑一个包)
                        int couter1 = inStream.read(body);
                        System.out.println("第 " + count + "次 获取IO流中的数据长度 ： " + couter1);
                        if (couter1 < 0) {
                            continue;
                        }
                        if (couter1 == -1) {
                            System.out.println("客户端断开连接");
                            break;
                        }
                        bytes = mergebyte(bytes, body, 0, couter1);
                        if (couter1 < body.length) {
                            continue;
                        }
                    }
                    byte[] body = new byte[0];
                    body = mergebyte(body, bytes, PACKET_HEAD_LENGTH, bytes.length);


                    System.out.println("=====================" + count);
                    for (int i = 0; i < bytes.length; i++) {
                        System.out.printf("0x%02x ", bytes[i]);
                    }
                    System.out.println("\n=====================" + count);
                    processPkg(outStream, bytes, bytes.length);

                    count++;
                    bytes = new byte[0];

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            try {
                inStream.close();
                outStream.close();
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param [outStream, pkg, pkgLen]
     * @return int
     * @description 处理 数据包 内容
     * 对获取的数据进行解析
     * <p>
     * 根据通信协议据体解析协议
     * @date 15:08 2018/9/3
     * @auther lifeng
     **/
    private static Map<String, String> mapOfSequenceId = new ConcurrentHashMap<>();

    private int processPkg(OutputStream outStream, byte[] pkg, int pkgLen) throws IOException {


        final String in = "C:\\Users\\wr\\Desktop\\demo.pcm";
        // SerSocket.inputPcmFile(in,pkg);

        //解析数据协议头
        int ret = 0;

        //数据帧头
        long message_header_frame_head = ((pkg[0] & 0xFF) << 8) + ((pkg[1] & 0xFF));
        //根据数据帧头判定是否为错误信息
        if (message_header_frame_head != SocketCode.MSG_HEADER_FREAME_HEAD) {
            //TODO 回复数据错误
            //sendConfirmPkg(outStream, message_header_control, confirmStatus);
            return SocketEnum.ERR_RECV_PKG.getCode();
        }
        //数据总长度
        long message_header_total_length = ((pkg[14] & 0xFF) << 8) + ((pkg[15] & 0xFF));
//

        //设备端ID
        long message_header_terminal_id = ((pkg[2] & 0xFF) << 24) + ((pkg[3] & 0xFF) << 16) + ((pkg[4] & 0xFF) << 8) + ((pkg[5] & 0xFF));
        String terminalId = String.valueOf(message_header_terminal_id);
        //基站ID
        long message_header_station_id = ((pkg[6] & 0xFF) << 24) + ((pkg[7] & 0xFF) << 16) + ((pkg[8] & 0xFF) << 8) + ((pkg[9] & 0xFF));
        String stationId = String.valueOf(message_header_station_id);
        //设备端IP
        long message_header_terminal_ip1 = (pkg[10] & 0xFF);
        long message_header_terminal_ip2 = (pkg[11] & 0xFF);
        String terminalIp1 = String.valueOf(message_header_terminal_ip1);
        String terminalIp2 = String.valueOf(message_header_terminal_ip2);
        StringBuilder terminalIp = new StringBuilder();
        terminalIp.append(terminalIp1).append(".").append(terminalIp2);
        //基站IP
        long message_header_station_ip1 = ((pkg[12] & 0xFF));
        long message_header_station_ip2 = ((pkg[13] & 0xFF));
        String stationIp1 = String.valueOf(message_header_station_ip1);
        String stationIp2 = String.valueOf(message_header_station_ip2);
        StringBuilder stationIp = new StringBuilder();
        stationIp.append(stationIp1).append(".").append(stationIp2);


        //控制类型
        long message_header_control = ((pkg[16] & 0xFF) << 8) + ((pkg[17] & 0xFF));

        //流水号
        long message_header_sequence_id = ((pkg[18] & 0xFF) << 8) + ((pkg[19] & 0xFF));
        String sqeuenceId = String.valueOf(message_header_sequence_id);
        //数据头时间
        long meaasge_header_real_time_yy = ((pkg[20] & 0xFF));
        long meaasge_header_real_time_MM = ((pkg[21] & 0xFF));
        long meaasge_header_real_time_dd = ((pkg[22] & 0xFF));
        long meaasge_header_real_time_hh = ((pkg[23] & 0xFF));
        long meaasge_header_real_time_mm = ((pkg[24] & 0xFF));
        long meaasge_header_real_time_ss = ((pkg[25] & 0xFF));

        String yy = String.valueOf(meaasge_header_real_time_yy);
        String MM = String.valueOf(meaasge_header_real_time_MM);
        String dd = String.valueOf(meaasge_header_real_time_dd);
        String hh = String.valueOf(meaasge_header_real_time_hh);
        String mm = String.valueOf(meaasge_header_real_time_mm);
        String ss = String.valueOf(meaasge_header_real_time_ss);

        StringBuilder rt = new StringBuilder("20").append(yy).append("-").append(MM)
                .append("-").append(dd).append(" ").append(hh).append(":").append(mm).append(":").append(ss);


        //信息传送结果 0x55成功 ， 0x22失败 ， -1无意义
        //long message_body_result = (pkg[26] & 0xFF);

        //基站中  设备挂载数量
        // long message_body_nodeCount = (pkg[27] & 0xFF);


        long message_body_data_type = ((pkg[28] & 0xFF) << 8) + ((pkg[29] & 0xFF));

        System.out.println("数据体 的 数据与类型 : " + message_body_data_type);

        int confirmStatus = 0x0;
//        final String in = "C:\\Users\\wr\\Desktop\\demo.pcm";
        switch ((int) message_header_control) {
            case SocketCode.MSG_HEADER_COMMAND_ID_HEARTBEAT: // 心跳包
                logger.info("收到心跳包......");
                System.out.println("\n=============================");
                for (int i = 0; i < pkgLen; i++) {
                    System.out.printf("0x%02x ", pkg[i]);
                }
                System.out.println("\n=============================");
                sendConfirmPkg(outStream, message_header_control, confirmStatus);
                break;
//            case SocketCode.MSG_HEADER_COMMAND_ID_LOGIN: // (保留) web登录命令
//                sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                break;
//            case SocketCode.MSG_HEADER_COMMAND_ID_RESPONSE: // 应答
//                sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                break;
                case SocketCode.MSG_HEADER_COMMAND_ID_REQUEST: // TODO 采集数据上报
                    logger.info("收到终端采集的数据信息");
                    switch ((int) message_body_data_type) {
                        case SocketCode.MSG_BODY_NODE_NAME_SENSOR_DATA: //传感器环境数据
                            logger.info("传感器环境数据......");
                            for (int i = 30; i < pkgLen - 2; i++) {
                                System.out.printf("0x%02x ", pkg[i]);
                            }
                            System.out.println();
                            long CO = (pkg[30] & 0xFF << 8) + ((pkg[31] & 0xFF));
                            double co = CO / 10.0;
                            long co_type = (pkg[32] & 0xFF);
                            long CO2 = (pkg[33] & 0xFF << 8) + ((pkg[34] & 0xFF));
                            double co2 = CO2 / 10.0;
                            long co2_type = (pkg[35] & 0xFF);
                            long O2 = (pkg[36] & 0xFF << 8) + ((pkg[37] & 0xFF));
                            double o2 = O2 / 10.0;
                            long o2_type = (pkg[38] & 0xFF);
                            long CH4 = (pkg[39] & 0xFF << 8) + ((pkg[40] & 0xFF));
                            double ch4 = CH4 / 10.0;
                            long ch4_type = (pkg[41] & 0xFF);
                            long T = (pkg[42] & 0xFF << 8) + ((pkg[43] & 0xFF));
                            double t = T / 10.0;
                            long t_type = (pkg[44] & 0xFF);
                            long H = (pkg[45] & 0xFF << 8) + ((pkg[46] & 0xFF));
                            double h = H / 10.0;
                            long h_type = (pkg[47] & 0xFF);


                            UpLoadGasDto upLoadGasDto = new UpLoadGasDto();

                            upLoadGasDto.setTerminalId(terminalId);
                            upLoadGasDto.setStationId(stationId);
                            upLoadGasDto.setT(t);
                            upLoadGasDto.setH(h);
                            upLoadGasDto.setCo2(co2);
                            upLoadGasDto.setCo(co);
                            upLoadGasDto.setRT(rt.toString());
                            upLoadGasDto.setCreateTime(DateConvert.convert(new Date(), 19));
                            upLoadGasDto.setCh4(ch4);
                            upLoadGasDto.setResult("success");
                            upLoadGasDto.setTerminalIp(terminalIp.toString());
                            upLoadGasDto.setStationIp(stationIp.toString());
                            upLoadGasDto.setSequenceId(sqeuenceId);
                            upLoadGasDto.setO2(o2);
                            upLoadGasDto.setT_type((int) t_type);
                            upLoadGasDto.setH_type((int) h_type);
                            upLoadGasDto.setCo2_type((int) co2_type);
                            upLoadGasDto.setCo_type((int) co_type);
                            upLoadGasDto.setCh4_type((int) ch4_type);
                            upLoadGasDto.setO2_type((int) o2_type);

                            try {
                                serSocket.upLoadService.sendGasInfoToQueue(upLoadGasDto);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            //TODO
                            sendConfirmPkg(outStream, message_header_control, confirmStatus);
                            break;
                        case SocketCode.MSG_BODY_NODE_NAME_HANDWARE_VERSION:    //硬件版本号
                            logger.info("收到硬件版本号......");
                            break;
                        case SocketCode.MSG_BODY_NODE_NAME_SOFTWARE_VERSION: //软件版本号
                            logger.info("收到软件版本号......");
                            break;
                        case SocketCode.MSG_BODY_NODE_NAME_SELFCHECK_RESULT:   //自检结果
                            logger.info("收到自检结果......");
                            break;
                        case SocketCode.MSG_BODY_NODE_NAME_LOCATOR_DATA:    //定位数据
                            logger.info("收到定位数据......");
                            break;
                        case SocketCode.MSG_BODY_NODE_NAME_VOICE_DATA:  //语音数据
                            logger.info("原始包 收到语音数据......");
                            break;
                        case SocketCode.MSG_BODY_NODE_NAME_HEARTBEAT:   //心跳数据
                            logger.info("收到心跳数据......");
                            break;
                        default:
                            System.out.println("未知数据指令包内容:");
                            System.out.println("\n=============================");
                            for (int i = 0; i < pkgLen; i++) {
                                System.out.printf("0x%02x ", pkg[i]);
                            }
                            System.out.println("\n=============================");
                            sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                        try {
////                            SerSocket.inputPcmFile(in,pkg);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                            break;
                    }
                    sendConfirmPkg(outStream, message_header_control, confirmStatus);
                    break;
            case SocketCode.MSG_HEADER_COMMAND_ID_NULL: // 语音数据上报
                logger.info("收到语音数据......");
                String basePath = "D:\\resources\\file\\voice\\";

                //创建根目录文件夹
                StringBuffer folderName = new StringBuffer(basePath);
                folderName.append(terminalId);

                String currentTime = DateConvert.convert(new Date(), 15);

                //创建文件名称  格式 ： 时间 + 终端ID + 序列号
                StringBuffer fileName = new StringBuffer();
                fileName.append(currentTime).append(terminalId).append(sqeuenceId);

                StringBuffer realPath = new StringBuffer(folderName);
                realPath.append("\\").append(fileName).append(".").append(FileTypeEnums.PCM);
                logger.info("文件路径 : [" + folderName.toString() + "] , 文件名称 ：[" + fileName.toString() + "] ，文件类型 [" + FileTypeEnums.PCM + "]");
                byte[] temp = new byte[512]; //TODO 是否加锁
                //判断是否存在与Map 中
                if (!mapOfSequenceId.containsKey(sqeuenceId)) {  //如果不存在，则认为是语音的第一天 也可以利用语音数据包的序号判断 数组的31，32位
                    mapOfSequenceId.put(sqeuenceId, currentTime);

                    logger.info("当前终端: ID>[" + terminalId + "] || IP>[" + terminalIp + "] 当前语音队列中无该终端语音信息，已创建改音频文件");
                    File file = new File(folderName.toString() + fileName.toString() + FileTypeEnums.PCM);
                    boolean flag = false;
                    if (!file.exists()) {
                        flag = FileUtils.createFile(folderName.toString(), fileName.toString(), FileTypeEnums.PCM);
                    }

//                        if (true){//如果创建成功
                    //TODO 写入此条数据（第一条）
                    //TODO pkg[32]=>最后

                    System.arraycopy(pkg, 32, temp, 0, 511);
                    SerSocket.inputPcmFile(realPath.toString(), temp);
//                            for (int i = 32 ; i < 544 ; i ++){
//                                SerSocket.inputPcmFile(realPath.toString(),pkg[i]);
//                            }
                }
//                   }
                else {
                    logger.info("当前终端: ID>[" + terminalId + "] || IP>[" + terminalIp + "] 当前语音队列中已存在终端语音信息，持续接收中......");
                    int voiceDateLength = (int) (((pkg[14] & 0xff) << 8) + ((pkg[15] & 0xff)));
                    if (voiceDateLength == 36) {
                        //TODO 执行 PCM ==> WAV 函数
                        logger.info("当前终端: ID>[" + terminalId + "] || IP>[" + terminalIp + "] 接收语音结束，执行PCM转WAV");

                        mapOfSequenceId.remove(sqeuenceId);

                        logger.info("当前终端: ID>[" + terminalId + "] || IP>[" + terminalIp + "] 已被移除语音队列");
                        break;
                    }
                    //TODO 向文件中写入内容（除第一条外其他内容）
                    String val = mapOfSequenceId.get(sqeuenceId);
                    //TODO pkg[32]=>最后
                    System.arraycopy(pkg, 32, temp, 0, 511);
                    StringBuffer t_path = new StringBuffer(folderName);
                    t_path.append("\\").append("\\").append(val).append(terminalId).append(sqeuenceId).append(".").append(FileTypeEnums.PCM);
                    SerSocket.inputPcmFile(t_path.toString(), temp);

//                        for (int i = 32 ; i < 544 ; i ++){
//                            SerSocket.inputPcmFile(t_path.toString(),pkg[i]);
//                        }
                    long t_num = (pkg[30] & 0xFF << 8) + ((pkg[31] & 0xFF));
                    int num = (int) t_num;

                    logger.info("第几[" + num + "]包");
                }
                // sendConfirmPkg(outStream, message_header_control, confirmStatus);
                break;
//            case SocketCode.MSG_HEADER_COMMAND_ID_CONTROL: // 控制
//                sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                break;
//            case SocketCode.MSG_HEADER_COMMAND_ID_SEARCH: // 查询
//                sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                break;
            default:    //未知数据
                System.out.println("未知数据指令包内容:" + pkg.length);
                System.out.println("\n=============================");
                for (int i = 0; i < pkgLen; i++) {
                    System.out.printf("0x%02x ", pkg[i]);
                }
//                SerSocket.inputPcmFile(in,pkg);
                System.out.println("\n=============================");
                sendConfirmPkg(outStream, message_header_control, confirmStatus);
                break;
        }

        return ret;
    }


    /**
     * @param [str, s]
     * @return void
     * @description 产生Pcm文件
     * @date 11:06 2018/9/4
     * @auther lifeng
     **/
    public static void inputPcmFile(String str, byte[] s) throws IOException {

        File file = new File(str);    //1、建立连接
        OutputStream os = null;
        //2、选择输出流,以追加形式(在原有内容上追加) 写出文件 必须为true 否则为覆盖
        //    os = new FileOutputStream(file,true);
        //和上一句功能一样，BufferedInputStream是增强流，加上之后能提高输出效率，建议
        os = new BufferedOutputStream(new FileOutputStream(file, true));
        // String string = "Programmer say : Hello World!";
        //  byte[] data = s.getBytes();    //将字符串转换为字节数组,方便下面写入

        os.write(s);    //3、写入文件
        os.flush();    //将存储在管道中的数据强制刷新出去
        os.close();

    }

    public static void main(String[] args) {
        new Thread(new SerSocket()).start();
    }

    /**
     * 反馈发送指令
     * 服务端反馈发送的内容
     *
     * @param outStream
     * @param opcode
     * @param confirmStatus
     * @return int
     */
    public static int sendConfirmPkg(OutputStream outStream, long opcode,
                                     int confirmStatus) {
        int ret = SocketEnum.ERR_NO_ERROR.getCode();
        byte[] confirmPkg = setConfirmPkg(opcode, confirmStatus);
        try {
            outStream.write(confirmPkg, 0, confirmPkg.length);
        } catch (Exception e) {
            ret = SocketEnum.ERR_NO_ERROR.getCode();
        }
        return ret;
    }

    /**
     * @param [opcode, confirmStatus]
     * @return byte[]
     * @description 设置 数据包 具体内容
     * @date 15:06 2018/9/3
     * @auther lifeng
     **/
    public static byte[] setConfirmPkg(long opcode, int confirmStatus) {
        byte[] pkg = new byte[20];

        // 包类型
        pkg[0] = (byte) 0x88;
        pkg[1] = (byte) 0x77;
        pkg[2] = (byte) 0x66;
        pkg[3] = (byte) 0x55;
        // 包体大小
        pkg[4] = (byte) 0xc;
        pkg[5] = (byte) 0x0;
        pkg[6] = (byte) 0x0;
        pkg[7] = (byte) 0x0;
        // 操作字
        pkg[8] = (byte) (opcode & 0xFF);
        pkg[9] = (byte) 0x0;
        pkg[10] = (byte) 0x0;
        pkg[11] = (byte) 0x0;
        // 收取状态
        pkg[12] = (byte) (confirmStatus & 0xFF);
        pkg[13] = (byte) 0x0;
        pkg[14] = (byte) 0x0;
        pkg[15] = (byte) 0x0;
        // 校验码
        pkg[16] = (byte) 0x55;
        pkg[17] = (byte) 0x66;
        pkg[18] = (byte) 0x77;
            pkg[19] = (byte) 0x88;

            return pkg;
        }



}
