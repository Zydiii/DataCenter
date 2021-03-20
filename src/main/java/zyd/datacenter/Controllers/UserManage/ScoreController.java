package zyd.datacenter.Controllers.UserManage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.User.UserScore;
import zyd.datacenter.Service.User.UserScoreService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/score")
@Api(value = "分数接口", tags="分数接口")
public class ScoreController {
    @Autowired
    UserScoreService userScoreService;

    @PostMapping("/addUserScore")
    @ApiOperation(value = "添加用户获取的分数", notes = "目前没有用")
    public ResponseEntity<?> addUserScore(@Valid @RequestBody UserScore userScore)
    {
        return ResponseEntity.ok(userScoreService.addUserScore(userScore).getMessage());
    }
}
