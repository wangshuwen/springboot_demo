package com.zkxh.demo.socket.server;

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
                    int count = 512;
                    byte[] recvCmd = new byte[count];
                    int recvCount = inStream.read(recvCmd);
                    if (recvCount == -1) {
                        System.out.println("接收完毕.");
                        flag = false;
                    } else {
                        for (int i = 0; i < recvCount; i++) {
                            System.out.printf("%02x ", recvCmd[i]);
                        }

                        System.out.printf("recvCount: %d\n", recvCount);

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

        long message_header_frame_head = ((pkg[0] & 0xFF) << 0) + ((pkg[1] & 0xFF) << 8);

        long message_header_control = (pkg[19] & 0xFF) + ((pkg[20] & 0xFF) << 8)
                + ((pkg[21] & 0xFF) << 16) + ((pkg[22] & 0xFF) << 24);

        if (message_header_frame_head != SocketMsg.MSG_HEADER_FREAME_HEAD.getCode()) {
            System.out.println(1);
            return SocketEnum.ERR_NO_ERROR.getCode();
        }
        System.out.println(2);
        int confirmStatus = 0x0;
        long opcode = (pkg[8] & 0xFF) + ((pkg[9] & 0xFF) << 8)
                + ((pkg[10] & 0xFF) << 16) + ((pkg[11] & 0xFF) << 24);
        switch ((int) opcode) {
            case 0x84: // 心跳包
                logger.info("收到心跳包。。。");
                System.out.println("收到心跳包。");
                break;
            case 0x83: // 出入库状态
                sendConfirmPkg(outStream, opcode, confirmStatus);
                break;
            default:
                System.out.println("未知数据指令包内容:");
                for (int i = 0; i < pkgLen; i++) {
                    System.out.printf("0x%02x ", pkg[i]);
                }
                System.out.println("\n=============================");
                sendConfirmPkg(outStream, opcode, confirmStatus);
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

}
