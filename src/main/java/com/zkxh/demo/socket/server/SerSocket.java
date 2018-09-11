package com.zkxh.demo.socket.server;

import com.zkxh.demo.socket.SocketCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;

/**
 * @ClassName ServerSocket
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/16 17:00
 * @Vserion v0.0.1
 */
public class SerSocket implements Runnable {

    //日志
    Logger logger = LoggerFactory.getLogger(SerSocket.class);

    private static final int SERVER_PORT = 8087;

//    private int server_port;
//    public SerSocket(int serverPort) {
//        this.server_port = SERVER_PORT;
//    }

    public SerSocket() {

    }

    @Override
    public void run() {
        try {
            // 服务端在20006端口监听客户端请求的TCP连接

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
        final String in = "C:\\Users\\wr\\Desktop\\6666.pcm";

        private Socket client = null;

        public RecvServerThread(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                // 获取Socket的输出流，用来向客户端发送数据
                OutputStream outStream = client.getOutputStream();
                InputStream inStream = client.getInputStream();
                boolean flag = true;
                System.out.println("开启线程");
                while (flag) {
                    // 接收从客户端发送过来的数据
                    int count = 1024;
                    byte[] recvCmd = new byte[count];
                    int recvCount = inStream.read(recvCmd);
                    if (recvCount == -1) {
                        System.out.println("接收完毕.");
                        break;
                    } else {
                        for (int i = 0; i < recvCount; i++) {
                            System.out.printf("0x%02x ", recvCmd[i]);
                        }
                        System.out.printf("\nrecvCount: %d\n", recvCount);
                        //处理数据包过程
                        processPkg(outStream, recvCmd, recvCount);
                        //   SerSocket.inputPcmFile(in,recvCmd);
                    }
                }
                inStream.close();
                outStream.close();
                client.close();
            } catch (Exception e) {
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
    private int processPkg(OutputStream outStream, byte[] pkg, int pkgLen) throws NumberFormatException {

        //解析数据协议头
        int ret = 0;

        //数据帧头
        long message_header_frame_head = ((pkg[0] & 0xFF)) + ((pkg[1] & 0xFF) << 8);

        //设备端ID
        long message_header_terminal_id = ((pkg[2] & 0xFF)) + ((pkg[3] & 0xFF) << 8) + ((pkg[4] & 0xFF) << 16) + ((pkg[5] & 0xFF) << 24);

        //基站ID
        long message_header_station_id = ((pkg[6] & 0xFF)) + ((pkg[7] & 0xFF) << 8) + ((pkg[8] & 0xFF) << 16) + ((pkg[9] & 0xFF) << 24);

        //设备端IP
        long message_header_terminal_ip = ((pkg[10] & 0xFF)) + ((pkg[11] & 0xFF) << 8);

        //基站IP
        long message_header_station_ip = ((pkg[12] & 0xFF)) + ((pkg[13] & 0xFF) << 8);

        //数据总长度
        long message_header_total_length = ((pkg[14] & 0xFF)) + ((pkg[15] & 0xFF) << 8);

        //控制类型
        long message_header_control = (pkg[16] & 0xFF) + ((pkg[17] & 0xFF) << 8);

        //流水号
        long message_header_sequence_id = (pkg[18] & 0xFF) + ((pkg[19] & 0xFF) << 8);

        //数据头时间
        long meaasge_header_real_time = ((pkg[20] & 0xFF)) + ((pkg[21] & 0xFF) << 8) + ((pkg[22] & 0xFF) << 16) + ((pkg[23] & 0xFF) << 24)
                + ((pkg[24] & 0xFF) << 32) + ((pkg[25] & 0xFF) << 40);

        //信息传送结果 0x55成功 ， 0x22失败 ， -1无意义
        long message_body_result = (pkg[26] & 0xFF);

        //基站中  设备挂载数量
        long message_body_nodeCount = (pkg[27] & 0xFF);


        long message_body_data_type = (pkg[28] & 0xFF) + ((pkg[29] & 0xFF) << 8);

        //long message_body_data_content = (pkg[28] & 0xFF) + ((pkg[29] & 0xFF) << 8);


        //根据数据帧头判定是否为错误信息
        if (message_header_frame_head != SocketMsg.MSG_HEADER_FREAME_HEAD.getCode()) {
            return SocketEnum.ERR_RECV_PKG.getCode();
        }
        int confirmStatus = 0x0;

        System.out.println(message_body_data_type == 0x0120);
        switch ((int) message_header_control) {
            case SocketCode.MSG_HEADER_COMMAND_ID_HEARTBEAT: // 心跳包
                logger.info("收到心跳包......");
                System.out.println("\n=============================");
                for (int i = 0; i < pkgLen; i++) {
                    System.out.printf("0x%02x ", pkg[i]);
                }
                System.out.println("\n=============================");
                break;
//            case SocketCode.MSG_HEADER_COMMAND_ID_LOGIN: // (保留) web登录命令
//                sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                break;
//            case SocketCode.MSG_HEADER_COMMAND_ID_RESPONSE: // 应答
//                sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                break;
            case SocketCode.MSG_HEADER_COMMAND_ID_REQUEST: // 采集数据上报
                logger.info("收到终端采集的数据信息");
                switch ((int) message_body_data_type) {
                    case SocketCode.MSG_BODY_NODE_NAME_SENSOR_DATA: //传感器环境数据
                        logger.info("传感器环境数据......");

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
                        logger.info("收到语音数据......");
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
                        break;
                }
                sendConfirmPkg(outStream, message_header_control, confirmStatus);
                break;
            case SocketCode.MSG_HEADER_COMMAND_ID_NULL: // 语音数据上报
                sendConfirmPkg(outStream, message_header_control, confirmStatus);
                break;
//            case SocketCode.MSG_HEADER_COMMAND_ID_CONTROL: // 控制
//                sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                break;
//            case SocketCode.MSG_HEADER_COMMAND_ID_SEARCH: // 查询
//                sendConfirmPkg(outStream, message_header_control, confirmStatus);
//                break;
            default:    //未知数据
                System.out.println("未知数据指令包内容:");
                System.out.println("\n=============================");
                for (int i = 0; i < pkgLen; i++) {
                    System.out.printf("0x%02x ", pkg[i]);
                }
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

        os.write(s, 0, s.length);    //3、写入文件
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
