package zyd.datacenter.Controllers.Room;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.Room.Room;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Entities.User.IpUtil;
import zyd.datacenter.Entities.User.Spectator;
import zyd.datacenter.Entities.User.UserInRoom;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.Room.RoomService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/room")
@Api(value = "房间接口", tags="房间接口")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/getAllRoom")
    @ApiOperation(value = "获取所有房间", notes = "获取所有房间")
    public ResponseEntity<?> getAllRoom()
    {
        return ResponseEntity.ok(roomService.getAllRoom());
    }

    @GetMapping("/getRoom/{type}")
    @ApiOperation(value = "获取某种类型的所有房间", notes = "获取某种类型的所有房间")
    public ResponseEntity<?> getRoom(@PathVariable("type") @ApiParam("在路径中加入房间类型type，ROOM_FREE->练习场，ROOM_SCORE->正式场，ROOM_AI->人机场") RoomType type)
    {
        return ResponseEntity.ok(roomService.getRoom(type));
    }

    @GetMapping("/getOneRoom/{roomId}")
    @ApiOperation(value = "获取某个特定房间", notes = "获取某个房间号对应的房间")
    public ResponseEntity<?> getOneRoom(@PathVariable("roomId") @ApiParam("房间号roomId") String roomId)
    {
        return ResponseEntity.ok(roomService.getOneRoom(roomId));
    }

    @RequestMapping(value = "/getIp", method = RequestMethod.POST)
    @ResponseBody
    public String getIp(HttpServletRequest request) {
        return IpUtil.getIpAddr(request);
    }

    @PostMapping("/joinCamp")
    @ApiOperation(value = "加入阵营", notes = "加入阵营")
    public ResponseEntity<?> joinCamp(@Valid @RequestBody @ApiParam("房间json数据，至少需要给roomId、userId、campId") UserInRoom userInRoom)
    {
        return resultToResponse(roomService.joinCamp(userInRoom));
    }

    @PostMapping("/createRoom")
    @ApiOperation(value = "创建房间", notes = "创建房间")
    public ResponseEntity<?> createRoom(@Valid @RequestBody @ApiParam("房间json数据，至少需要给ownerId、maxPlayerNum、maxSpectatorsNum、frequency、roomType") Room room)
    {
        return resultToResponse(roomService.createRoom(room));
    }

    @PostMapping("/deleteRoom")
    @ApiOperation(value = "删除房间", notes = "只有房主能删除其创建的房间")
    public ResponseEntity<?> deleteRoom(@Valid @RequestBody @ApiParam("房间json数据，至少需要给id、ownerId") Room room)
    {
        return resultToResponse(roomService.deleteRoom(room));
    }

    @PostMapping("/updateRoom")
    @ApiOperation(value = "更新房间", notes = "只有房主能更新其创建的房间")
    public ResponseEntity<?> updateRoom(@Valid @RequestBody @ApiParam("房间json数据，至少需要给id、ownerId") Room room)
    {
        return resultToResponse(roomService.updateRoom(room));
    }

    @PostMapping("/joinRoom")
    @ApiOperation(value = "加入房间", notes = "加入某个特定房间")
    public ResponseEntity<?> joinRoom(HttpServletRequest request, @Valid @RequestBody @ApiParam("房间内用户json数据，至少需要给roomId、userId和weaponName") UserInRoom userInRoom)
    {
        return resultToResponse(roomService.joinRoom(userInRoom, request));
    }

    @PostMapping("/leaveRoom")
    @ApiOperation(value = "离开房间", notes = "离开某个特定房间")
    public ResponseEntity<?> leaveRoom(@Valid @RequestBody @ApiParam("房间内用户json数据，至少需要给roomId、userId") UserInRoom userInRoom)
    {
        return resultToResponse(roomService.leaveRoom(userInRoom));
    }

    @PostMapping("/match")
    @ApiOperation(value = "匹配", notes = "目前没有实现")
    public ResponseEntity<?> match(@Valid @RequestBody UserInRoom userInRoom)
    {
        return ResponseEntity.ok(roomService.match(userInRoom));
    }

    @PostMapping("/watchGame")
    @ApiOperation(value = "观战", notes = "目前没有实现")
    public ResponseEntity<?> watchGame(@Valid @RequestBody Spectator spectator)
    {
        return resultToResponse(roomService.joinWatchRoom(spectator));
    }

    @PostMapping("/readyInRoom")
    @ApiOperation(value = "准备", notes = "在房间内准备")
    public ResponseEntity<?> readyInRoom(@Valid @RequestBody @ApiParam("房间内用户json数据，至少需要给roomId、userId") UserInRoom userInRoom)
    {
        return resultToResponse(roomService.readyInRoom(userInRoom));
    }

    @PostMapping("/cancelReadyInRoom")
    @ApiOperation(value = "取消准备", notes = "在房间内准备")
    public ResponseEntity<?> cancelReadyInRoom(@Valid @RequestBody @ApiParam("房间内用户json数据，至少需要给roomId、userId") UserInRoom userInRoom)
    {
        return resultToResponse(roomService.cancelReadyInRoom(userInRoom));
    }

    public ResponseEntity<?> resultToResponse(Result result)
    {
        if(result.getCode() == 0)
            return ResponseEntity
                    .badRequest()
                    .body(result.getMessage());
        else
            return ResponseEntity.ok(result.getMessage());
    }

    @PostMapping("/beginGame")
    @ApiOperation(value = "开始战斗", notes = "只有房主可以开始房间，并且房间内所有玩家都准备好以后才可以开始")
    public ResponseEntity<?> beginGame(@Valid @RequestBody @ApiParam("房间内用户json数据，至少需要给roomId、userId") UserInRoom userInRoom)
    {
        return resultToResponse(roomService.beginGame(userInRoom));
    }

    @PostMapping("/changeScore")
    @ApiOperation(value = "修改分数", notes = "战斗中玩家分数变化以后需要调用此接口修改房间内用户的战斗数据")
    public ResponseEntity<?> changeScore(@Valid @RequestBody @ApiParam("房间内用户json数据，至少需要给roomId、userId、score、damageValue、crashNum、destroyNum、result") UserInRoom userInRoom)
    {
        return resultToResponse(roomService.changeScore(userInRoom));
    }

    @PostMapping("/endGame")
    @ApiOperation(value = "结束战斗", notes = "房主才能调用此接口")
    public ResponseEntity<?> endGame(@Valid @RequestBody @ApiParam("房间json数据，至少需要给id") Room room)
    {
        System.out.println("get end");
        return resultToResponse(roomService.endGame(room));

    }
}