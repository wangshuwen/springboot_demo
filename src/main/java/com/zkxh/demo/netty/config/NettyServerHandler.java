package com.zkxh.demo.netty.config;

import com.zkxh.demo.netty.data.GasInfo;
import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.data.response.ResponseData;
import com.zkxh.demo.netty.handle.ProcessVoice;
import com.zkxh.demo.netty.request.ChannelMap;
import com.zkxh.demo.netty.request.Client;
import com.zkxh.demo.netty.utils.ConstantValue;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @ClassName NettyServerHandler
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/17 16:43
 * @Vserion v0.0.1
 */
@Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    private static Map<String, String> mapOfSequenceId = new ConcurrentHashMap<>();

    private ProcessVoice processVoice = new ProcessVoice();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ResponseData resp = new ResponseData();
        if (msg instanceof RequestData) {
            RequestData customMsg = (RequestData) msg;

            if (customMsg.getType() == ConstantValue.MSG_HEADER_FREAME_HEAD) {
                //TODO
                int cmd = customMsg.getCmd();
                byte[] body = customMsg.getBody();
                int ndName = ((body[2] & 0xff) << 8) + (body[3] & 0xff);
                switch (cmd) {
                    case ConstantValue.MSG_HEADER_COMMAND_ID_NULL:
                        if (ndName != ConstantValue.MSG_BODY_NODE_NAME_VOICE_DATA) {
                            log.error("非法语音数据包");
                            break;
                        }
                        log.info("处理语音数据");
                        String wavPath = processVoice.process(customMsg);
                        if (!("error").equals(wavPath)) {
                            log.info("接收并转码成功");
                            log.info("生成文件路径" + wavPath);
                        }
                        break;
                    case ConstantValue.MSG_HEADER_COMMAND_ID_REQUEST:
                        log.info("采集数据上报");
                        switch (ndName) {
                            case ConstantValue.MSG_BODY_NODE_NAME_SENSOR_DATA:
                                log.info("气体信息");
                                GasInfo gasInfo = new GasInfo();
                                gasInfo.setCo(((body[5] & 0xff << 8) + (body[6] & 0xff) / 10.0));
                                gasInfo.setCoFlag(body[7]);
                                gasInfo.setCo2(((body[8] & 0xff << 8) + (body[9] & 0xff) / 10.0));
                                gasInfo.setCo2Flag(body[10]);
                                gasInfo.setO2(((body[11] & 0xff << 8) + (body[12] & 0xff) / 10.0));
                                gasInfo.setO2Flag(body[13]);
                                gasInfo.setCh4(((body[14] & 0xff << 8) + (body[15] & 0xff) / 10.0));
                                gasInfo.setCh4Flag(body[16]);
                                gasInfo.setT(((body[17] & 0xff << 8) + (body[18] & 0xff) / 10.0));
                                gasInfo.settFlag(body[19]);
                                gasInfo.setH(((body[20] & 0xff << 8) + (body[21] & 0xff) / 10.0));
                                gasInfo.sethFlag(body[21]);
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_SELFCHECK_RESULT:
                                log.info("自检结果");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_SOFTWARE_VERSION:
                                log.info("软件版本号");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_HANDWARE_VERSION:
                                log.info("硬件版本号");
                                break;
                            case ConstantValue.MSG_HEADER_COMMAND_ID_UPDATE_IP:
                                log.info("更新IP");
                                break;
                            default:
                                log.error("未知命令包");
                                break;
                        }
                        customMsg.setCmd(0x0016);
                        resp.setCustomMsg(customMsg);
                        resp.setCode((byte) 0x55);
                        Client.sendCmd(resp);
                        break;
                    case ConstantValue.MSG_HEADER_COMMAND_ID_HEARTBEAT:
                        log.info("心跳数据");
                        resp.setCustomMsg(customMsg);
                        resp.setCode((byte) 0x55);
                        Client.sendCmd(resp);
                        break;
                    default:
                        log.error("未知命令");
                        break;
                }
            } else {
                resp.setCustomMsg(customMsg);
                resp.setCode((byte) ConstantValue.MSG_BODY_RESULT_ERROR);
                Client.sendCmd(resp);
            }
            log.info("服务端接收来自客户端[ " + ctx.channel().remoteAddress() + "] 的消息为 " + customMsg.toString());
        } else {
            log.error("异常数据包{}" + msg.toString());
        }

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("客户端[" + clientIP + ":" + port + "] 连接成功");
        ChannelMap.addChannel(clientIP, ctx.channel());
        log.info("客户端[" + clientIP + ":" + port + "] 加入session");
        log.info("当前连接基站数量" + ChannelMap.getChannelNum());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("客户端[" + clientIP + ":" + port + "] 已断开连接");
        ChannelMap.removeChannelByName(clientIP);
        log.info("客户端[" + clientIP + "] 被移出session");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("读取客户端[" + clientIP + ":" + port + "] 数据完成");
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                //TODO 读超时
                ctx.channel().close();
            }
            if (event.state() == IdleState.WRITER_IDLE) {
                //TODO 写超时
                ctx.channel().close();
            }
            if (event.state() == IdleState.ALL_IDLE) {
                //清除超时会话
                ChannelFuture writeAndFlush = ctx.writeAndFlush("client will be remove");
                writeAndFlush.addListener(new ChannelFutureListener() {

                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        //TODO 通知web端显示并存储数据库
                        //TODO 客户端掉线
                        ctx.channel().close();
                    }
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        log.error("客户端[" + clientIP + "] 出现异常" + cause.getLocalizedMessage());
        cause.printStackTrace();
    }


}