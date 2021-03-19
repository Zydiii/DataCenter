package zyd.datacenter.Controllers.UserManage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.User.UserScore;
import zyd.datacenter.Service.User.UserScoreService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/score")
public class ScoreController {
    @Autowired
    UserScoreService userScoreService;

    @PostMapping("/addUserScore")
    public ResponseEntity<?> addUserScore(@Valid @RequestBody UserScore userScore)
    {
        return ResponseEntity.ok(userScoreService.addUserScore(userScore).getMessage());
    }
}
