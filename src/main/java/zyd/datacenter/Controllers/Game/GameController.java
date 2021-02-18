package zyd.datacenter.Controllers.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.Game.GameOverview;
import zyd.datacenter.Service.Game.GameHistoryService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/game")
public class GameController {
    @Autowired
    private GameHistoryService gameHistoryService;

    @GetMapping("/getHistory/{userId}")
    public ResponseEntity<?> getHistory(@PathVariable("userId") String userId)
    {
        return ResponseEntity.ok(gameHistoryService.getGameHistory(userId));
    }

    @GetMapping("/replay/{gameId}")
    public ResponseEntity<?> replay(@PathVariable("gameId") String gameId)
    {
        return ResponseEntity.ok(gameHistoryService.replay(gameId));
    }
}
