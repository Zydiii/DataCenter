package zyd.datacenter.Controllers.Asset;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.AnnounceBoard.AnnouncementBoard;
import zyd.datacenter.Entities.Asset.Asset;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.Asset.AssetService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/asset")
@Api(value = "资源包接口", tags="资源包接口")
public class AssetController {
    @Autowired
    AssetService assetService;

    @PostMapping("/manage/write")
    @ApiOperation(value = "写入新资源包信息", notes = "管理员写入新资源包信息需要调用的接口")
    public ResponseEntity<?> writeAssets(@Valid @RequestBody @ApiParam("资源包json数据，需要给versionId、dataUrl、userId") Asset asset){
        Result result = assetService.setNewAsset(asset);
        return resultToResponse(result);
    }

    @GetMapping("/user/getLatest")
    @ApiOperation(value = "获取最新资源包信息", notes = "用户可以获取最新的资源包数据，不需要参数")
    public ResponseEntity<?> getLatestAnnounce(){

        return ResponseEntity.ok(assetService.getLatestAsset());
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
