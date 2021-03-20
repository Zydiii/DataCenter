package zyd.datacenter.Controllers.Rank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.Rank.RankSetting;
import zyd.datacenter.Entities.Rank.RankType;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.Rank.RankService;
import zyd.datacenter.Service.Rank.RankSettingService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/rank")
@Api(value = "排名接口", tags="排名接口")
public class RankController {
    @Autowired
    private RankSettingService rankSettingService;

    @Autowired
    private RankService rankService;

    @PostMapping("/setting")
    @ApiOperation(value = "设置排名规则", notes = "目前没有用")
    public ResponseEntity<?> setRankRules(@Valid @RequestBody @ApiParam("排行设置json数据") RankSetting rankSetting){
        return resultToResponse(rankSettingService.setRule(rankSetting));
    }

    @GetMapping("/setting/getSetting")
    @ApiOperation(value = "获取排名规则", notes = "目前没有用")
    public ResponseEntity<?> getSetting(){
        return ResponseEntity.ok(rankSettingService.getSetting());
    }

    // 排名策略待定
    @GetMapping("/getRank/{rankType}")
    @ApiOperation(value = "获取排行榜", notes = "获取某种排行榜")
    public ResponseEntity<?> getRank(@PathVariable("rankType") @ApiParam("需要在路径加上排行类型rankType，RANK_ALL->总榜，RANK_DAY->日榜，RANK_WEEK->周榜，RANK_MONTH->月榜") RankType rankType){
        return ResponseEntity.ok(rankService.getRank(rankType));
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
