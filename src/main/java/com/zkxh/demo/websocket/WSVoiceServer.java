package com.zkxh.demo.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName WSServer
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/31 14:31
 * @Vserion v0.0.1
 */

@ServerEndpoint(value = "/voiceToWs")
@Component
public class WSVoiceServer {

    private static final Logger log = LoggerFactory.getLogger(WSVoiceServer.class);

    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WSVoiceServer> webSocketSet = new CopyOnWriteArraySet<>();

    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);     //加入set中
        addOnlineCount();           //在线数加1
        //TODO 查询后台未被读取的语音数据 可加动态参数 限定某人

//        try {
//            sendMessage("123");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        log.info("有新连接语音加入！当前在线人数为" + getOnlineCount());
//        try {

//            JSONArray jsonArray = JSON.parseArray(ResultUtil.jsonToStringSuccess("WebSocket连接成功"));
//            sendMessage(jsonArray.toJSONString());
//        } catch (IOException e) {
//            log.error("websocket IOException");
//        }
    }
    //	//连接打开时执行
    //	@OnOpen
    //	public void onOpen(@PathParam("user") String user, Session session) {
    //		currentUser = user;
    //		System.out.println("Connected ... " + session.getId());
    //	}

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * @param message, session]
     * @return void
     * @description 收到客户端消息后调用的方法
     * @date 17:55 2018/10/16
     * @auther lifeng
     **/
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("来自客户端的消息:" + message);

//        //群发消息 //TODO 为不同分组人推送
//        for (WSVoiceServer item : webSocketSet) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }


    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        log.info(message);
        for (WSVoiceServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WSVoiceServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WSVoiceServer.onlineCount--;
    }

}
