package zyd.datacenter.Controllers.Game;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.Game.GameHistory;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Service.Game.GameHistoryService;
import zyd.datacenter.Service.Game.GameOverviewService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/game")
@Api(value = "战斗相关接口", tags="战斗相关接口")
public class GameController {
    @Autowired
    private GameHistoryService gameHistoryService;

    @Autowired
    private GameOverviewService gameOverviewService;

    @PostMapping("/addGame")
    @ApiOperation(value = "添加一场战斗", notes = "可以添加一场战斗概览，目前没有用")
    public ResponseEntity<?> addGame(@Valid @RequestBody @ApiParam("战斗概览json数据") GameOverview gameOverview)
    {
        return ResponseEntity.ok(gameOverviewService.addGame(gameOverview).getMessage());
    }

    @GetMapping("/getHistory/{userId}")
    @ApiOperation(value = "获取历史战绩", notes = "用户获取自己的历史战绩")
    public ResponseEntity<?> getHistory(@PathVariable("userId") @ApiParam("用户编号userId") String userId)
    {
        return ResponseEntity.ok(gameHistoryService.getGameHistory(userId));
    }

    @PostMapping("/addHistory")
    @ApiOperation(value = "添加历史战绩", notes = "添加一条历史战绩，目前没有用")
    public ResponseEntity<?> addHistory(@Valid @RequestBody @ApiParam("历史战绩json数据") GameHistory gameHistory)
    {
        return ResponseEntity.ok(gameHistoryService.addHistory(gameHistory).getMessage());
    }

    @GetMapping("/replay/{gameId}")
    @ApiOperation(value = "战斗回放", notes = "回放某场战斗，目前等待其他部分一起实现")
    public ResponseEntity<?> replay(@PathVariable("gameId") @ApiParam("战斗编号gameId") String gameId)
    {
        return ResponseEntity.ok(gameHistoryService.replay(gameId));
    }

    @GetMapping("/getAllGameOverview")
    @ApiOperation(value = "获取所有战斗总览", notes = "目前没有用")
    public ResponseEntity<?> getAllGameOverview()
    {
        return ResponseEntity.ok(gameOverviewService.getAllGameOverview());
    }
}
