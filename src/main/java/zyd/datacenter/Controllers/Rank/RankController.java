package zyd.datacenter.Controllers.Rank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.Rank.RankSetting;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.Rank.RankService;
import zyd.datacenter.Service.Rank.RankSettingService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rank")
public class RankController {
    @Autowired
    private RankSettingService rankSettingService;

    @Autowired
    private RankService rankService;

    @PostMapping("/setting")
    public ResponseEntity<?> setRankRules(@Valid @RequestBody RankSetting rankSetting){
        return resultToResponse(rankSettingService.setRule(rankSetting));
    }

    @GetMapping("/setting/getSetting")
    public ResponseEntity<?> getSetting(){
        return ResponseEntity.ok(rankSettingService.getSetting());
    }

    // 排名策略待定
    @GetMapping("/getRank")
    public ResponseEntity<?> getRank(){
        return ResponseEntity.ok(rankService.getRank());
    }

    public ResponseEntity<?> resultToResponse(Result result)
    {
        if(result.getCode() == 0)
            return ResponseEntity
                    .badRequest()
                    .body(result);
        else
            return ResponseEntity.ok(result);
    }
}
