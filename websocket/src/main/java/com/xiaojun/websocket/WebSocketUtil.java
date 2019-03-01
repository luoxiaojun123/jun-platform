package com.xiaojun.websocket;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author long.luo
 * @date 2019/3/1 10:07
 */
public class WebSocketUtil {

    public static final Map<String, Session> USERS_ONLINE = new ConcurrentHashMap<>();

    public static void sendMessageToAllOnlineUser(String message) {
        USERS_ONLINE.forEach((username, session) -> sendMessage(session, message));
    }

    private static void sendMessage(Session session, String message) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }

        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
