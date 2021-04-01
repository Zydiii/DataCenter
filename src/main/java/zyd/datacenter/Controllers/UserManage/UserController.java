package zyd.datacenter.Controllers.UserManage;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.User.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getAllUser")
    @ApiOperation(value = "获取所有用户", notes = "获取所有用户，不需要参数")
    public ResponseEntity<?> getAllUser()
    {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/changeAvatar")
    public ResponseEntity<?> addPhoto(@RequestParam("image") MultipartFile image, @RequestParam("userId") String userID)
    {
        Result result = userService.addPhoto(userID, image);
        return resultToResponse(result);
    }

    @GetMapping("/getAvatar/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable("id") String userId) {
        Result result = userService.getPhoto(userId);
        return resultToResponse(result);
    }

    @GetMapping("/getInfo/{id}")
    @ApiOperation(value = "获取用户信息", notes = "获取某个特定用户数据")
    public ResponseEntity<?> getInfo(@PathVariable("id") @ApiParam("路径添加用户编号is") String id)
    {
        Optional<User> result = userService.findUser(id);
        if(result == null)
            return ResponseEntity
                    .badRequest()
                    .body("不存在此用户");
        else
            return ResponseEntity.ok(result);
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
