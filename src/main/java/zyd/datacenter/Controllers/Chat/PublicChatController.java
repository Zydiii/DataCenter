package zyd.datacenter.Controllers.Chat;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@Api(value = "公共聊天接口", tags="公共聊天接口")
public class PublicChatController {
    @Autowired
    ChannelChatService channelChatService;

    @Autowired
    ChannelService channelService;

    @PostMapping("/user/sendMessage")
    @ApiOperation(value = "发送信息", notes = "用户可以在公共频道")
    public ResponseEntity<?> sendMessage(@Valid @RequestBody @ApiParam("聊天json数据，需要给channelName、chat、userId") ChannelChat channelChat){
        return ResponseEntity.ok(channelChatService.sendMessage(channelChat));
    }

    @GetMapping("/user/getMessage/{channelId}")
    @ApiOperation(value = "获取发言信息", notes = "用户可以获取某个频道的所有聊天")
    public ResponseEntity<?> getMessage(@PathVariable("channelId") @ApiParam("频道编号channelId") String channelId){
        return ResponseEntity.ok(channelChatService.getMessage(channelId));
    }

    @PostMapping("/user/getMessageByTime")
    @ApiOperation(value = "根据时间戳获取发言信息", notes = "获取查询时间戳之后的发言")
    public ResponseEntity<?> getMessageByTime(@Valid @RequestBody @ApiParam("查询json数据，需要给channelName、timeStamp") ChannelChat channelChat){
        return ResponseEntity.ok(channelChatService.getMessageByTime(channelChat));
    }

    @GetMapping("/user/getChannels")
    @ApiOperation(value = "获取所有频道", notes = "获取所有频道，不需要参数")
    public ResponseEntity<?> getChannels(){
        return ResponseEntity.ok(channelService.getChannels());
    }

    @GetMapping("/manage/createChannel/{channelName}")
    @ApiOperation(value = "创建频道", notes = "管理员可以创建频道")
    public ResponseEntity<?> createChannel(@PathVariable("channelName") @ApiParam("在路径加上频道名称channelName") String channelName){
        Result result = channelService.createChannel(channelName);
        if(result.getCode() == 1){
            return ResponseEntity.ok(result.getMessage());
        }
        else{
            return ResponseEntity.badRequest().body(result.getMessage());
        }
    }
}
