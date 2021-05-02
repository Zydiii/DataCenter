package zyd.datacenter.Service.Socket;

import com.alibaba.fastjson.JSON;
import zyd.datacenter.Entities.Chat.ChannelChat;

import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class WebSocketCustomEncoding implements Encoder.Text<ChannelChat> {
    @Override
    public String encode(ChannelChat channelChat) {
        assert channelChat!=null;
        return JSON.toJSONString(channelChat);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }

}
