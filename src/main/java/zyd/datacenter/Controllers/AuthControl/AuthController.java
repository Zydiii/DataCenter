package zyd.datacenter.Controllers.AuthControl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.User.ERole;
import zyd.datacenter.Entities.User.Role;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Request.LoginRequest;
import zyd.datacenter.Payload.Request.SignupRequest;
import zyd.datacenter.Payload.Response.JwtResponse;
import zyd.datacenter.Payload.Response.MessageResponse;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.User.RoleRepository;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Security.jwt.JwtUtils;
import zyd.datacenter.Security.services.UserDetailsImpl;
import zyd.datacenter.Service.Auth.AuthService;
import zyd.datacenter.Service.User.UserService;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@Api(value = "权限验证接口", tags="权限验证接口")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/offline/login")
    @ApiOperation(value = "登录", notes = "用户登录需要调用此接口")
    public ResponseEntity<?> loginUser(@Valid @RequestBody @ApiParam("登录json数据，需要给password、username") LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginCheck(loginRequest);
        if(jwtResponse.getCode() == 0)
            return ResponseEntity.badRequest().body(jwtResponse.getMessage());
        else
            return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/offline/login1")
    @ApiOperation(value = "登录", notes = "已弃用")
    public String loginUser1(@Valid @RequestBody @ApiParam("登录json数据，需要给password、username") LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginCheck(loginRequest);
        return jwtResponse.getAccessToken();
    }

    @PostMapping("/offline/signup")
    @ApiOperation(value = "注册", notes = "注册新用户需要调用此接口")
    public ResponseEntity<?> registerUser(@Valid @RequestBody @ApiParam("注册json数据，至少需要给username、password") SignupRequest signUpRequest) {
        return authService.signUpCheck(signUpRequest);
    }

    @GetMapping("/online/logout/{id}")
    @ApiOperation(value = "登出", notes = "退出登录，改变用户状态")
    public ResponseEntity<?> logoutUser(@PathVariable("id") @ApiParam("登出用户id") String id){
        Result result = authService.logOut(id);
        if(result.getCode() == 0)
            return ResponseEntity
                    .badRequest()
                    .body(result.getMessage());
        else
            return ResponseEntity.ok(result.getMessage());
    }

    @GetMapping("/offline/forgetpasswordEmail/{email}")
    @ApiOperation(value = "用邮箱找回密码", notes = "弃用")
    public ResponseEntity<?> forgetPasswordEmail(@PathVariable("email") @ApiParam("邮箱email") String email){
        Result result = authService.getPasswordEmail(email);
        if(result.getCode() == 1){
            return ResponseEntity.ok(result.getMessage());
        }
        else{
            return ResponseEntity.badRequest().body(result.getMessage());
        }
    }

    @GetMapping("/offline/forgetpasswordUsername/{username}")
    @ApiOperation(value = "用用户名找回密码", notes = "输入用户名，判断该用户名是否存在，并向其邮箱发送密码")
    public ResponseEntity<?> forgetPasswordUsername(@PathVariable("username") @ApiParam("用户名username") String username){
        Result result = authService.getPasswordUsername(username);
        if(result.getCode() == 1){
            return ResponseEntity.ok(result.getMessage());
        }
        else{
            return ResponseEntity.badRequest().body(result.getMessage());
        }
    }

}
