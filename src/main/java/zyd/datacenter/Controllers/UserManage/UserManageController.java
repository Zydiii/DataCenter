package zyd.datacenter.Controllers.UserManage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Request.CreateUserRequest;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.User.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/manage/user")
@Api(value = "用户管理接口", tags="用户管理接口")
public class UserManageController {
    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/getAllUser")
    @ApiOperation(value = "获取所有用户", notes = "获取所有用户，不需要参数")
    public ResponseEntity<?> getAllUser()
    {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getOneUser/{id}")
    @ApiOperation(value = "获取用户信息", notes = "获取某个特定用户数据")
    public ResponseEntity<?> getOneUser(@PathVariable("id") @ApiParam("路径添加用户编号is") String id)
    {
        Optional<User> result = userService.findUser(id);
        if(result == null)
            return ResponseEntity
                    .badRequest()
                    .body("不存在此用户");
        else
            return ResponseEntity.ok(result);
    }

    @PostMapping("/createUser")
    @ApiOperation(value = "添加用户信息", notes = "添加用户")
    public ResponseEntity<?> createUser(@Valid @RequestBody @ApiParam("用户json数据，至少需要password、username") User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        Result result = userService.insertUser(user);
        return resultToResponse(result);
    }

    @GetMapping("/deleteUser/{id}")
    @ApiOperation(value = "删除用户", notes = "删除某个特定用户")
    public ResponseEntity<?> deleteUser(@PathVariable("id") @ApiParam("路径添加用户编号id") String id)
    {
        Result result = userService.deleteUser(id);
        return resultToResponse(result);
    }

    @PostMapping("/updateUser")
    @ApiOperation(value = "更新用户", notes = "更新用户数据")
    public ResponseEntity<?> updateUser(@Valid @RequestBody @ApiParam("用户json数据，至少需要id") User user)
    {
        Result result = userService.updateUser(user);
        return resultToResponse(result);
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
