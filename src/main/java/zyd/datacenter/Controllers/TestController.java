package zyd.datacenter.Controllers;

import com.vividsolutions.jts.util.Debug;
import io.swagger.annotations.Api;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import zyd.datacenter.Entities.Room.Room;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Request.LoginRequest;
import zyd.datacenter.Payload.Response.JwtResponse;
import zyd.datacenter.Payload.Result;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/testSendPost")
    public String testSendPost()
    {
        RestTemplate restTemplate = new RestTemplate();

        String roomId = "1";
        String maxNum = "5";
        String step = "2";
        String envId = "2";
        String gameType = "ROOM_SCORE";
        String playerNum = "3";
        String room = roomId + " " + maxNum + " " + step + " " + envId + " " + gameType + " " + playerNum + "\n";

        String userId = "1";
        String campId = "4";
        String host = "127.0.0.1";
        String port = "8080";
        String inGame = "1";
        String flightId = "4";
        String flightType = "FLIGHT";
        String userInfo = userId + " " + campId + " " + host + " " + port + " " + inGame + " " + flightId + " " + flightType + "\n";

        String userId1 = "2";
        String campId1 = "2";
        String host1 = "127.0.0.1";
        String port1 = "8080";
        String inGame1 = "0";
        String flightId1 = "4";
        String flightType1 = "FLIGHT";
        String userInfo1 = userId1 + " " + campId1 + " " + host1 + " " + port1 + " " + inGame1 + " " + flightId1 + " " + flightType1 + "\n";

        String userId2 = "3";
        String campId2 = "2";
        String host2 = "127.0.0.1";
        String port2 = "8080";
        String inGame2 = "1";
        String flightId2 = "5";
        String flightType2 = "FLIGHT";
        String userInfo2 = userId2 + " " + campId2 + " " + host2 + " " + port2 + " " + inGame2 + " " + flightId2 + " " + flightType2 + "\n";

        String send = room + userInfo + userInfo1 + userInfo2;

//        Set<String> roles = new HashSet<>(); // 角色名称
//        roles.add("RLOE_ADMIN");
//         Set<String> realNames = new HashSet<>(); // 玩家真实姓名
//        realNames.add("name");
//         Set<String> realIds = new HashSet<>(); // 玩家学号
//
//        User user = new User("user4", "123456", "123456", "1", "1", roles, realNames, realIds);
//        roles.add("ROLE_USER");
//        User user1 = new User("user1", "123456", "123456", "35241", "651", roles, realNames, realIds);
//        String userInfo = user.getUsername() + " " + user.getEmail() + " " + user.getPhone() + " " + user.getPassword() + " " + user.getRoles().toString() + " "  + user.getRealIds().toString() + " test a long";
//        String userInfo1 = user1.getUsername() + " " + user1.getEmail() + " " + user1.getPhone() + " " + user1.getPassword() + " " + user1.getRoles().toString() + " "  + user1.getRealIds().toString() ;

 //       String send = userInfo + "\n" + userInfo1;
        System.out.println(send);
        HttpEntity<String> request = new HttpEntity<>(send);//将对象装入HttpEntity中

        //String info = restTemplate.postForObject("http://202.120.40.8:30609/auth/offline/login", request, String.class);

        try{
            String info = restTemplate.postForObject("http://10.0.0.24:30604", request, String.class);
            return "Ok";
        }catch (Exception e)
        {
            return e.toString();
        }
    }

    @GetMapping("/testSendPost1")
    public String testSendPost1()
    {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<LoginRequest> request = new HttpEntity<>(new LoginRequest("user4", "123456"));//将对象装入HttpEntity中
        try{
            String info = restTemplate.postForObject("http://localhost:8080/auth/offline/login1", request, String.class);
            return info;
        }catch (Exception e)
        {
            return e.toString();
        }
    }

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/testGetObject")
    public Result testGetObject()
    {
        return new Result("测试", 0);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}

