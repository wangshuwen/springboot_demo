package com.zkxh.demo.netty.config;

import com.zkxh.demo.common.da.kafka.KafkaSender;
import com.zkxh.demo.netty.data.request.RequestData;
import com.zkxh.demo.netty.data.response.ResponseData;
import com.zkxh.demo.netty.handle.ProcessVoice;
import com.zkxh.demo.netty.request.Client;
import com.zkxh.demo.netty.station.StationChannelMap;
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
 * @Author:        wangshuwen
 * @Description:    基站 数据   业务处理类
 * @CreateDate:     2018/11/23 12:55
 * @Version:        1.0
 */
@Sharable
@Component
public class StationServerHandler extends ChannelInboundHandlerAdapter {
    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(getClass());

    private static StationServerHandler stationServerHandler;
    //注入 上传数据服务
    @Resource
    private UpLoadService upLoadService;


//    @Resource
//    private Client client;

    @PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
    public void init() {
        stationServerHandler = this;
        stationServerHandler.upLoadService = this.upLoadService;
    }

    //注入Kafka
    @Resource
    KafkaSender kafkaSender;

    //注入处理声音
    private ProcessVoice processVoice = new ProcessVoice();



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端收到消息:"+msg);
        ResponseData resp = new ResponseData();
        if(msg instanceof RequestData){
            RequestData reqMsg=  (RequestData)msg;
            //如果请求信息的帧头为ox6688
            if(reqMsg.getType()== ConstantValue.MSG_HEADER_FREAME_HEAD){
                int cmd = reqMsg.getCmd();
                byte[] body = reqMsg.getBody();
                int ndName = reqMsg.getNdName();

                switch (cmd) {
                    case ConstantValue.MSG_HEADER_COMMAND_ID_NULL:
                        if (ndName != ConstantValue.MSG_BODY_NODE_NAME_VOICE_DATA) {
                            log.error("非法语音数据包");
                            break;
                        }
                        log.info("处理语音数据");
                        System.out.println(reqMsg.toString());
                        String wavPath = processVoice.process(reqMsg);
                        if (!("error").equals(wavPath)) {
                            log.info("接收并转码成功");
                            log.info("生成文件路径" + wavPath);
                        }
                        //向终端响应信息
                        reqMsg.setCmd(ConstantValue.MSG_HEADER_COMMAND_ID_RESPONSE);
                        reqMsg.setLength(30);
                        reqMsg.setResult((byte) 0x55);
                        reqMsg.setNodeCount((byte) 0x00);
                        reqMsg.setNdName(ConstantValue.MSG_BODY_NODE_NAME_VOICE_DATA);
                        resp.setCustomMsg(reqMsg);
                        Client.sendCmd(resp);
                        break;
                    case ConstantValue.MSG_HEADER_COMMAND_ID_REQUEST:
                        log.info("采集数据上报");
                        switch (ndName) {
                            case ConstantValue.MSG_BODY_NODE_NAME_SENSOR_DATA:
                                log.info("气体信息");
                                //TODO 存入kafka队列问题
                                stationServerHandler.upLoadService.sendGasInfoToQueue(reqMsg);
                                reqMsg.setLength(30);
                                reqMsg.setCmd(ConstantValue.MSG_HEADER_COMMAND_ID_RESPONSE);
                                reqMsg.setResult((byte) ConstantValue.MSG_BODY_RESULT_SUCCESS);
                                reqMsg.setNodeCount((byte) 0x00);
                                resp.setCustomMsg(reqMsg);
                                resp.setCode((byte) 0x55);
                                Client.sendCmd(resp);
                                System.out.println("返回气体确认结束");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_SELFCHECK_RESULT:
                                stationServerHandler.upLoadService.sendSelfCheckResult(reqMsg);
                                log.info("自检结果");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_SOFTWARE_VERSION:
                                stationServerHandler.upLoadService.sendSoftWareVersion(reqMsg);
                                log.info("软件版本号");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_HANDWARE_VERSION:
                                stationServerHandler.upLoadService.sendHandWareVersion(reqMsg);
                                log.info("硬件版本号");
                                break;
                            case ConstantValue.MSG_BODY_NODE_NAME_UPDATE_IP:
//                                kafkaSender.send(customMsg,"updateIp.tut");
                                stationServerHandler.upLoadService.sendUpLoadIp(reqMsg);
                                log.info("更新IP");
                                break;
                            default:
                                log.error("未知命令包");
                                break;
                        }
                        break;
                    case ConstantValue.MSG_HEADER_COMMAND_ID_HEARTBEAT:
                        log.info("心跳数据");
                        reqMsg.setCmd(ConstantValue.MSG_HEADER_COMMAND_ID_RESPONSE);
                        reqMsg.setLength(30);
                        reqMsg.setResult((byte) 0x55);
                        reqMsg.setNodeCount((byte) 0x00);
                        resp.setCustomMsg(reqMsg);
                        Client.sendCmd(resp);
                        System.out.println("返回心跳包确认结束");
                        break;
                    default:
                        log.error("未知命令");
                        break;
                }




            }
        }


    }

    /**
     * @param [ctx]
     * @return void
     * @description 建立连接 执行的方法
     * @date 17:35 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("基站[" + clientIP + ":" + port + "] 连接成功");
        System.out.println(clientIP);
        StationChannelMap.addChannel(clientIP, ctx.channel());
        log.info("基站[" + clientIP + ":" + port + "] 加入session");
        log.info("当前连接基站数量" + StationChannelMap.getChannelNum());


    }

    /**
     * @param [ctx]
     * @return void
     * @description 断开连接执行的方法
     * @date 17:35 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("基站[" + clientIP + ":" + port + "] 已断开连接");
        StationChannelMap.removeChannelByName(clientIP);
        log.info("基站[" + clientIP + "] 被移出session");
    }

    /**
     * @param [ctx]
     * @return void
     * @description 数据读取完成执行方法
     * @date 17:35 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        int port = insocket.getPort();
        log.info("读取基站[" + clientIP + ":" + port + "] 数据完成");
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("移除客户端");
    }

    /**
     * @param [ctx, evt]
     * @return void
     * @description 心跳处理
     * @date 17:36 2018/11/21
     * @auther lifeng
     **/
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
                StationChannelMap.removeChannelByName(clientIP);
            }
            if (event.state() == IdleState.WRITER_IDLE) {
                //TODO 写超时
                ctx.channel().close();
                StationChannelMap.removeChannelByName(clientIP);
            }
            if (event.state() == IdleState.ALL_IDLE) {
                //清除超时会话
                ChannelFuture writeAndFlush = ctx.writeAndFlush("client will be remove");
                writeAndFlush.addListener((ChannelFutureListener) future -> {
                    //TODO 通知web端显示并存储数据库
                    //TODO 基站掉线
                    ctx.channel().close();
                    StationChannelMap.removeChannelByName(clientIP);
                });
            }
        } else {
            super.userEventTriggered(ctx, evt);
            StationChannelMap.removeChannelByName(clientIP);
        }
    }

    /**
     * @param [ctx, cause]
     * @return void
     * @description 异常处理方法
     * @date 17:37 2018/11/21
     * @auther lifeng
     **/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIP = insocket.getAddress().getHostAddress();
        log.error("基站[" + clientIP + "] 出现异常" + cause.getLocalizedMessage());
        StationChannelMap.removeChannelByName(clientIP);
        cause.printStackTrace();
    }


}
