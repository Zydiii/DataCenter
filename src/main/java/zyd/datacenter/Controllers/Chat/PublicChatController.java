package zyd.datacenter.Controllers.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.Chat.ChannelChatService;
import zyd.datacenter.Service.Chat.ChannelService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/publicChat")
public class PublicChatController {
    @Autowired
    ChannelChatService channelChatService;

    @Autowired
    ChannelService channelService;

    @PostMapping("/user/sendMessage")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody ChannelChat channelChat){
        return ResponseEntity.ok(channelChatService.sendMessage(channelChat));
    }

    @GetMapping("/user/getMessage/{channelId}")
    public ResponseEntity<?> getMessage(@PathVariable("channelId") String channelId){
        return ResponseEntity.ok(channelChatService.getMessage(channelId));
    }

    @PostMapping("/user/getMessageByTime")
    public ResponseEntity<?> getMessageByTime(@Valid @RequestBody ChannelChat channelChat){
        return ResponseEntity.ok(channelChatService.getMessageByTime(channelChat));
    }

    @GetMapping("/user/getChannels")
    public ResponseEntity<?> getChannels(){
        return ResponseEntity.ok(channelService.getChannels());
    }

    @GetMapping("/manage/createChannel/{channelName}")
    public ResponseEntity<?> createChannel(@PathVariable("channelName") String channelName){
        Result result = channelService.createChannel(channelName);
        if(result.getCode() == 1){
            return ResponseEntity.ok(result.getMessage());
        }
        else{
            return ResponseEntity.badRequest().body(result.getMessage());
        }
    }
}
