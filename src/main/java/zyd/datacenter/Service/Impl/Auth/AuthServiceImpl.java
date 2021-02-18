package zyd.datacenter.Service.Impl.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Request.LoginRequest;
import zyd.datacenter.Payload.Request.SignupRequest;
import zyd.datacenter.Payload.Response.JwtResponse;
import zyd.datacenter.Payload.Response.MessageResponse;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Security.jwt.JwtUtils;
import zyd.datacenter.Security.services.UserDetailsImpl;
import zyd.datacenter.Service.Auth.AuthService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    public JwtResponse loginCheck (LoginRequest loginRequest) {
        if (userRepository.existsByUsername(loginRequest.getUsername())) {
            User user = userRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + loginRequest.getUsername()));
            if (encoder.matches(loginRequest.getPassword(), user.getPassword())) {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(item -> item.getAuthority())
                        .collect(Collectors.toList());

                user.setState(1);
                userRepository.save(user);

                return new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        userDetails.getPhone(),
                        userDetails.getScore(),
                        userDetails.getLevel(),
                        userDetails.getEXP(),
                        userDetails.getWeapons(),
                        userDetails.getRealNames(),
                        userDetails.getRealIds(),
                        roles,
                        "登录成功");
            } else
                return new JwtResponse("密码错误");
        } else {
            return new JwtResponse("用户名不存在");
        }
    }

    public ResponseEntity<?> signUpCheck(SignupRequest signUpRequest){
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("用户名已存在"));
        }
        else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("该邮箱已被注册"));
        }
        else if(userRepository.existsByPhone(signUpRequest.getPhone())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("该手机号已被注册"));
        }
        else{
            User user = new User(signUpRequest.getUsername(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getEmail(),
                    signUpRequest.getPhone(),
                    signUpRequest.getRoles(),
                    signUpRequest.getRealNames(),
                    signUpRequest.getRealIds());

            Set<String> strRoles = signUpRequest.getRoles();

            user.setRoles(strRoles);
            userRepository.save(user);

            return ResponseEntity.ok(new MessageResponse("注册成功"));
        }
    }

    public Result logOut(String username){
        if(userRepository.existsByUsername(username)){
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
            user.setState(0);
            userRepository.save(user);
            return new Result("登出成功", 1);
        }
        else
            return new Result("登出失败，请重试", 0);
    }

    public Result getPassword(String email){
        return new Result("邮件发送成功，请查收", 1);
    }
}
