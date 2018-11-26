package com.zkxh.demo.netty.config;

import com.zkxh.demo.common.da.kafka.KafkaSender;
import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.data.response.ResponseData;
import com.zkxh.demo.netty.handle.ProcessVoice;
import com.zkxh.demo.netty.terminal.ChannelMap;
import com.zkxh.demo.netty.request.Client;
import com.zkxh.demo.netty.utils.ConstantValue;
import com.zkxh.demo.service.gas.UpLoadService;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.net.InetSocketAddress;


/**
 * @ClassName NettyServerHandler    终端 数据   业务处理类
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/17 16:43
 * @Vserion v0.0.1
 */
@Sharable
@Component
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    private static NettyServerHandler nettyServerHandler;

    //注入上传数据服务
    @Resource
    private UpLoadService upLoadService;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        nettyServerHandler = this;
        nettyServerHandler.upLoadService = this.upLoadService;
    }

    //注入Kafka
    @Resource
    KafkaSender kafkaSender;

    //注入处理声音
    private ProcessVoice processVoice = new ProcessVoice();

    /**
     * @param [ctx, msg]
     * @return void
     * @description 数据处理方法  执行主要业务
     * @date 2018/9/17 16:20
     * @auther lifeng
     **/
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ResponseData resp = new ResponseData();
        if (msg instanceof RequestData) {
            RequestData customMsg = (RequestData) msg;

            if (customMsg.getType() == ConstantValue.MSG_HEADER_FREAME_HEAD) {
                //TODO
                int cmd = customMsg.getCmd();
                byte[] body = customMsg.getBody();
                int ndName = customMsg.getNdName();
                switch (cmd) {
                    case ConstantValue.MSG_HEADER_COMMAND_ID_NULL:
                        if (ndName != ConstantValue.MSG_BODY_NODE_NAME_VOICE_DATA) {
                            log.error("非法语音数据包");
                            break;
                        }
                        log.info("处理语音数据");
                        System.out.println(customMsg.toString());
                        String wavPath = processVoice.process(customMsg);
                        if (!("error").equals(wavPath)) {
                            log.info("接收并转码成功");
                            log.info("生成文件路径" + wavPath);
                        }
                        //向终端响应信息
                        customMsg.setCmd(ConstantValue.MSG_HEADER_COMMAND_ID_RESPONSE);
                        customMsg.setLength(30);
                        customMsg.setResult((byte) 0x55);
                        customMsg.setNodeCount((byte) 0x00);
                        customMsg.setNdName(ConstantValue.MSG_BODY_NODE_NAME_VOICE_DATA);
                        resp.setCustomMsg(customMsg);
                        Client.sendCmd(resp);
                        break;
                    case ConstantValue.MSG_HEADER_COMMAND_ID_REQUEST:
                        log.info("采集数据上报");
                        switch (ndName) {
                            case ConstantValue.MSG_BODY_NODE_NAME_SENSOR_DATA:
                                log.info("气体信息");
                                //TODO 存入kafka队列问题
                                nettyServerHandler.upLoadService.sendGasInfoToQueue(customMsg);
                                customMsg.setLength(30);
                                customMsg.setCmd(ConstantValue.MSG_HEADER_COMMAND_ID_RESPONSE);
                                customMsg.setResult((byte) ConstantValue.MSG_BODY_RESULT_SUCCESS);
                                customMsg.setNodeCount((byte) 0x00);
                                resp.setCustomMsg(customMsg);
                                resp.setCode((byte) 0x55);
                                Client.sendCmd(resp);
                                System.out.println("返回气体确认结束");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_SELFCHECK_RESULT:
                                nettyServerHandler.upLoadService.sendSelfCheckResult(customMsg);
                                log.info("自检结果");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_SOFTWARE_VERSION:
                                nettyServerHandler.upLoadService.sendSoftWareVersion(customMsg);
                                log.info("软件版本号");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_HANDWARE_VERSION:
                                nettyServerHandler.upLoadService.sendHandWareVersion(customMsg);
                                log.info("硬件版本号");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_UPDATE_IP:
//                                kafkaSender.send(customMsg,"updateIp.tut");
                                nettyServerHandler.upLoadService.sendUpLoadIp(customMsg);
                                log.info("更新IP");
                                break;
                            default:
                                log.error("未知命令包");
                                break;
                        }
                        break;
                    case ConstantValue.MSG_HEADER_COMMAND_ID_HEARTBEAT:
                        log.info("心跳数据");
                        customMsg.setCmd(ConstantValue.MSG_HEADER_COMMAND_ID_RESPONSE);
                        customMsg.setLength(30);
                        customMsg.setResult((byte) 0x55);
                        customMsg.setNodeCount((byte) 0x00);
                        resp.setCustomMsg(customMsg);
                        Client.sendCmd(resp);
                        System.out.println("返回心跳包确认结束");
                        break;
                    default:
                        log.error("未知命令");
                        break;
                }
            } else {
                //数据包头错误不接收
                customMsg.setCmd(ConstantValue.MSG_HEADER_COMMAND_ID_RESPONSE);
                customMsg.setLength(30);
                customMsg.setResult((byte) ConstantValue.MSG_BODY_RESULT_ERROR);
                customMsg.setNodeCount((byte) 0x00);
                resp.setCustomMsg(customMsg);
                Client.sendCmd(resp);
            }
            log.info("终端的服务端接收来自终端[ " + ctx.channel().remoteAddress() + "] 的消息为 " + customMsg.toString());
        } else {
            log.error("异常数据包{}" + msg.toString());
        }

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
//        insocket.hashCode()
        System.out.println(insocket.getHostName());
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("终端[" + clientIP + ":" + port + "] 连接成功");
        ChannelMap.addChannel(clientIP, ctx.channel());
        log.info("终端[" + clientIP + ":" + port + "] 加入session");
        log.info("当前连接终端数量" + ChannelMap.getChannelNum());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("终端[" + clientIP + ":" + port + "] 已断开连接");
        ChannelMap.removeChannelByName(clientIP);
        log.info("终端[" + clientIP + "] 被移出session");
        System.out.println(ChannelMap.getChannelNum());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("读取终端[" + clientIP + ":" + port + "] 数据完成");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                //TODO 读超时
                ctx.channel().close();
                ChannelMap.removeChannelByName(clientIP);
            }
            if (event.state() == IdleState.WRITER_IDLE) {
                //TODO 写超时
                ctx.channel().close();
                ChannelMap.removeChannelByName(clientIP);
            }
            if (event.state() == IdleState.ALL_IDLE) {
                //清除超时会话
                ChannelFuture writeAndFlush = ctx.writeAndFlush("client will be remove");
                writeAndFlush.addListener((ChannelFutureListener) future -> {
                    //TODO 通知web端显示并存储数据库
                    //TODO 终端掉线
                    ctx.channel().close();
                    ChannelMap.removeChannelByName(clientIP);
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
            ChannelMap.removeChannelByName(clientIP);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        log.error("终端[" + clientIP + "] 出现异常" + cause.getLocalizedMessage());
        ChannelMap.removeChannelByName(clientIP);
        cause.printStackTrace();
    }


}
