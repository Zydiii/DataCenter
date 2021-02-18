package zyd.datacenter.Controllers.Chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.Chat.ChannelChat;
import zyd.datacenter.Service.Chat.ChannelChatService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/publicChat")
public class PublicChatController {
    @Autowired
    ChannelChatService channelChatService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody ChannelChat channelChat){
        return ResponseEntity.ok(channelChatService.sendMessage(channelChat));
    }

    @GetMapping("/getMessage/{channelId}")
    public ResponseEntity<?> getMessage(@PathVariable("channelId") String channelId){
        return ResponseEntity.ok(channelChatService.getMessage(channelId));
    }
}
