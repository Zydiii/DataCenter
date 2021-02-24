package zyd.datacenter.Controllers.UserManage;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
public class UserManageController {
    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/getAllUser")
    public ResponseEntity<?> getAllUser()
    {
        List<User> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/getOneUser/{id}")
    @ApiImplicitParam(name = "id",value = "id",required = true,paramType = "query",dataType = "int")
    public ResponseEntity<?> getOneUser(@PathVariable("id") String id)
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
    @ApiOperation("/添加学生信息")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        Result result = userService.insertUser(user);
        return resultToResponse(result);
    }

    @GetMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") String id)
    {
        Result result = userService.deleteUser(id);
        return resultToResponse(result);
    }

    @PostMapping("/updateUser")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user)
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
