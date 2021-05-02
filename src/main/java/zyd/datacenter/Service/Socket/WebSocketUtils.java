package zyd.datacenter.Service.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Chat.ChannelChatRepository;

import javax.websocket.EncodeException;
import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class WebSocketUtils {
    private static final Logger logger = LoggerFactory.getLogger(WebSocketUtils.class);

    // 存储 websocket session
    public static final Map<String, Session> ONLINE_USER_SESSIONS = new ConcurrentHashMap<>();

    /**
     * @param session 用户 session
     * @param message 发送内容
     */
    public static void sendMessage(Session session, ChannelChat message) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendObject(message);
            //basic.sendText(message);
        } catch (IOException | EncodeException e) {
            logger.error("sendMessage IOException ",e);
        }
    }

    public static void sendMessageAll(ChannelChat channelChat) {
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> sendMessage(session, channelChat));
    }

    /**
     * @param session 用户 session
     * @param message0 发送内容
     */
    public static void sendMessageText(Session session, String message0) {
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            return;
        }
        try {
            basic.sendText(message0);
        } catch (IOException e) {
            logger.error("sendMessage IOException ",e);
        }
    }

    public static void sendMessageAllText(String message) {
        ONLINE_USER_SESSIONS.forEach((sessionId, session) -> sendMessageText(session, message));
    }
}
