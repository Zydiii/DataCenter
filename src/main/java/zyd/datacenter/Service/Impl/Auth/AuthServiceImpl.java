package zyd.datacenter.Service.Impl.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.User.AvatarHelper;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Request.LoginRequest;
import zyd.datacenter.Payload.Request.SignupRequest;
import zyd.datacenter.Payload.Response.JwtResponse;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Security.jwt.JwtUtils;
import zyd.datacenter.Security.services.UserDetailsImpl;
import zyd.datacenter.Service.Auth.AuthService;
import zyd.datacenter.Service.Mail.MailService;
import zyd.datacenter.Service.SequenceGenerator.SequenceGeneratorService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    MailService mailService;

    @Autowired
    SequenceGeneratorService sequenceGenerator;

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
                        userDetails.getAvatar(),
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
                    .body("用户名已存在");
        }
        else if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("该邮箱已被注册");
        }
        else if(userRepository.existsByPhone(signUpRequest.getPhone())){
            return ResponseEntity
                    .badRequest()
                    .body("该手机号已被注册");
        }
        else{
            User user = new User(signUpRequest.getUsername(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getPassword(),
                    signUpRequest.getEmail(),
                    signUpRequest.getPhone(),
                    signUpRequest.getRoles(),
                    signUpRequest.getRealNames(),
                    signUpRequest.getRealIds());

            Set<String> strRoles = signUpRequest.getRoles();

            user.setRoles(strRoles);

            long id = sequenceGenerator.generateSequence(User.SEQUENCE_NAME);
            user.setId(Long.toString(id));

            String avatar = "";

            try{
                avatar = AvatarHelper.createBase64Avatar(Math.abs(user.getId().hashCode()));
            }
            catch (Exception e)
            {

            }

            user.setAvatarBase(avatar);

            userRepository.save(user);

            return ResponseEntity.ok("注册成功");
        }
    }

    public Result logOut(String id){
        User user = userRepository.findById(id).get();
        user.setState(0);
        userRepository.save(user);
        return new Result("登出成功", 1);
    }

    public Result getPasswordEmail(String email){
        if(userRepository.existsByEmail(email)){
            User user = userRepository.findByEmail(email).get();
            return mailService.sendSimpleMail(email, "【对抗仿真系统】 找回密码", "您的密码为：" + user.getPairPassword() + " ，请妥善保存您的密码。");
        }
        else{
            return new Result("该邮箱地址不存在", 0);
        }

    }

    public Result getPasswordUsername(String username){
        if(userRepository.existsByUsername(username)){
            User user = userRepository.findByUsername(username).get();
            return mailService.sendSimpleMail(user.getEmail(), "【对抗仿真系统】 找回密码", "您的密码为：" + user.getPairPassword() + " ，请妥善保存您的密码。");
        }
        else{
            return new Result("该用户不存在", 0);
        }

    }
}