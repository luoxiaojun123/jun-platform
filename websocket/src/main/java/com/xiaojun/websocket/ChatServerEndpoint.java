package com.xiaojun.websocket;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Date;

/**
 * @author long.luo
 * @date 2019/3/1 10:13
 */
@Controller
@ServerEndpoint("/chat/{username}")
public class ChatServerEndpoint {

    @GetMapping("/chatPage")
    public String chatPage() {
        return "chat.html";
    }

    /**
     * 在连接创建时触发
     *
     * @param username username
     * @param session  session
     */
    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        WebSocketUtil.USERS_ONLINE.put(username, session);
        String message = "【" + username + "】进入聊天室";
        System.out.println(message);
        WebSocketUtil.sendMessageToAllOnlineUser(message);
    }

    /**
     * 在连接断开时触发
     *
     * @param username username
     * @param session  session
     */
    @OnClose
    public void closeSession(@PathParam("username") String username, Session session) {
        WebSocketUtil.USERS_ONLINE.remove(username);
        String message = "【" + username + "】离开聊天室";
        System.out.println(message);
        WebSocketUtil.sendMessageToAllOnlineUser(message);

        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 在接收到消息时触发
     *
     * @param username username
     * @param message  message
     */
    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        message = "[" + username + "]：" + message;
        System.out.println(message);
        WebSocketUtil.sendMessageToAllOnlineUser(message);
    }

    /**
     * 在连接发生异常时触发
     *
     * @param session   session
     * @param throwable throwable
     */
    @OnError
    public void sessionError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("WebSocket连接发生异常，message:" + throwable.getMessage());
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void sendMessage() {
        // 所有在线用户广播通知
        WebSocketUtil.USERS_ONLINE.forEach((username, session) -> {
            String message = "【" + username + "】在线时间" + new Date();
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
