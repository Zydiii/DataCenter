package zyd.datacenter.Controllers.AuthControl;

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
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.loginCheck(loginRequest);
        if(jwtResponse.getCode() == 0)
            return ResponseEntity.badRequest().body(jwtResponse.getMessage());
        else
            return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/offline/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return authService.signUpCheck(signUpRequest);
    }

    @GetMapping("/online/logout/{id}")
    public ResponseEntity<?> logoutUser(@PathVariable("id") String id){
        Result result = authService.logOut(id);
        if(result.getCode() == 0)
            return ResponseEntity
                    .badRequest()
                    .body(result.getMessage());
        else
            return ResponseEntity.ok(result.getMessage());
    }

    @GetMapping("/offline/forgetpassword/{email}")
    public ResponseEntity<?> forgetPassword(@PathVariable("email") String email){
        return ResponseEntity.ok(authService.getPassword(email));
    }
}
