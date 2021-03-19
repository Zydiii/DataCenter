package zyd.datacenter.Controllers.Game;

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
public class GameController {
    @Autowired
    private GameHistoryService gameHistoryService;

    @Autowired
    private GameOverviewService gameOverviewService;

    @PostMapping("/addGame")
    public ResponseEntity<?> addGame(@Valid @RequestBody GameOverview gameOverview)
    {
        return ResponseEntity.ok(gameOverviewService.addGame(gameOverview).getMessage());
    }

    @GetMapping("/getHistory/{userId}")
    public ResponseEntity<?> getHistory(@PathVariable("userId") String userId)
    {
        return ResponseEntity.ok(gameHistoryService.getGameHistory(userId));
    }

    @PostMapping("/addHistory")
    public ResponseEntity<?> addHistory(@Valid @RequestBody GameHistory gameHistory)
    {
        return ResponseEntity.ok(gameHistoryService.addHistory(gameHistory).getMessage());
    }

    @GetMapping("/replay/{gameId}")
    public ResponseEntity<?> replay(@PathVariable("gameId") String gameId)
    {
        return ResponseEntity.ok(gameHistoryService.replay(gameId));
    }

    @GetMapping("/getAllGameOverview")
    public ResponseEntity<?> getAllGameOverview()
    {
        return ResponseEntity.ok(gameOverviewService.getAllGameOverview());
    }
}
