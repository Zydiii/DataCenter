package zyd.datacenter.Service.Socket;

import com.vividsolutions.jts.util.Debug;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.spring.web.json.Json;
import zyd.datacenter.Entities.Asset.Asset;
import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.Chat.ChannelChatRepository;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Service.Asset.AssetService;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

import static zyd.datacenter.Service.Socket.WebSocketUtils.*;

@Component
@RestController
@ServerEndpoint(value = "/chat-room/{username}", encoders = WebSocketCustomEncoding.class)
public class ChatRoomServerEndpoint {
    private static final Logger logger = LoggerFactory.getLogger(ChatRoomServerEndpoint.class);

    private static GetChannelChat getChannelChat;
    @Autowired
    public void setGetChannelChatImpl(GetChannelChat getChannelChat){
        ChatRoomServerEndpoint.getChannelChat = getChannelChat;
    }

    @OnOpen
    public void openSession(@PathParam("username") String username, Session session) {
        ONLINE_USER_SESSIONS.put(username, session);

        //sendMessageAllText("{\"chat\":" + username + "}");
        //sendMessageAll(getChannelChat.getChannelChat(username, "欢迎用户[" + username + "] 来到公共频道"));
    }

    @OnMessage
    public void onMessage(@PathParam("username") String username, String message) {
        sendMessageAll(getChannelChat.getChannelChat(username, message));
    }

    @OnClose
    public void onClose(@PathParam("username") String username, Session session) {
        //当前的Session 移除
        ONLINE_USER_SESSIONS.remove(username);
        //并且通知其他人当前用户已经离开聊天室了
        //sendMessageAll(getChannelChat.getChannelChat(username, "欢迎用户[" + username + "] 来到公共频道"));

        try {
            session.close();
        } catch (IOException e) {
            logger.error("onClose error",e);
        }
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            logger.error("onError excepiton",e);
        }
        logger.info("Throwable msg "+throwable.getMessage());
    }


}

