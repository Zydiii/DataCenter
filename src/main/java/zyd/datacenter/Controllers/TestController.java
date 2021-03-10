package zyd.datacenter.Controllers;

import io.swagger.annotations.Api;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import zyd.datacenter.Entities.User.User;
import zyd.datacenter.Payload.Result;

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
        //String test = restTemplate.getForObject("http://202.120.40.8:30609/api/test/all", String.class);
        //Result result = restTemplate.getForObject("http://127.0.0.1:8080/api/test/testGetObject", Result.class);
        //restTemplate.postForEntity("http://127.0.0.1:8080/auth/login");
        //RestTemplate restTemplate = new RestTemplate();

         Set<String> roles = new HashSet<>(); // 角色名称

         Set<String> realNames = new HashSet<>(); // 玩家真实姓名

         Set<String> realIds = new HashSet<>(); // 玩家学号

        HttpEntity<User> request = new HttpEntity<>(new User("user4", "123456", "123456", "1", "1", roles, realNames, realIds ));//将对象装入HttpEntity中
        //String info = restTemplate.postForObject("http://202.120.40.8:30609/auth/offline/login", request, String.class);
        String info = restTemplate.postForObject("http://202.120.40.8:30604", request, String.class);

        return info;
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

