package zyd.datacenter.Controllers.Room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.Room.Room;
import zyd.datacenter.Entities.Room.RoomType;
import zyd.datacenter.Entities.User.Spectator;
import zyd.datacenter.Entities.User.UserInRoom;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.Room.RoomService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/getAllRoom")
    public ResponseEntity<?> getAllRoom()
    {
        return ResponseEntity.ok(roomService.getAllRoom());
    }

    @GetMapping("/getRoom/{type}")
    public ResponseEntity<?> getRoom(@PathVariable("type") RoomType type)
    {
        return ResponseEntity.ok(roomService.getRoom(type));
    }

    @PostMapping("/createRoom")
    public ResponseEntity<?> createRoom(@Valid @RequestBody Room room)
    {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @PostMapping("/deleteRoom")
    public ResponseEntity<?> deleteRoom(@Valid @RequestBody Room room)
    {
        return resultToResponse(roomService.deleteRoom(room));
    }

    @PostMapping("/updateRoom")
    public ResponseEntity<?> updateRoom(@Valid @RequestBody Room room)
    {
        return resultToResponse(roomService.updateRoom(room));
    }

    @PostMapping("/joinRoom")
    public ResponseEntity<?> joinRoom(@Valid @RequestBody UserInRoom userInRoom)
    {
        return resultToResponse(roomService.joinRoom(userInRoom));
    }

    @PostMapping("/leaveRoom")
    public ResponseEntity<?> leaveRoom(@Valid @RequestBody UserInRoom userInRoom)
    {
        return resultToResponse(roomService.leaveRoom(userInRoom));
    }

    @PostMapping("/match")
    public ResponseEntity<?> match(@Valid @RequestBody UserInRoom userInRoom)
    {
        return ResponseEntity.ok(roomService.match(userInRoom));
    }

    @PostMapping("/watchGame")
    public ResponseEntity<?> watchGame(@Valid @RequestBody Spectator spectator)
    {
        return resultToResponse(roomService.joinWatchRoom(spectator));
    }

    public ResponseEntity<?> resultToResponse(Result result)
    {
        if(result.getCode() == 0)
            return ResponseEntity
                    .badRequest()
                    .body(result);
        else
            return ResponseEntity.ok(result);
    }




}
