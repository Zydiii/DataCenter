package zyd.datacenter.Controllers.AnnounceBoard;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zyd.datacenter.Entities.AnnounceBoard.AnnouncementBoard;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Service.AnnounceBoard.AnnounceBoardService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/announceboard")
@Api(value = "公告栏接口", tags="公告栏接口")
public class AnnounceBoardController {
    @Autowired
    private AnnounceBoardService announceBoardService;

    @PostMapping("/manage/write")
    @ApiOperation(value = "写入公告栏", notes = "管理员写入公告栏数据需要调用的接口")
    public ResponseEntity<?> writeAnnounce(@Valid @RequestBody @ApiParam("公告栏json数据，需要给text、userId") AnnouncementBoard announceBoard){
        Result result = announceBoardService.writeAnnounce(announceBoard);
        return resultToResponse(result);
    }

    @GetMapping("/user/getLatest")
    @ApiOperation(value = "获取公告栏", notes = "用户可以获取最新的公告栏数据，不需要参数")
    public ResponseEntity<?> getLatestAnnounce(){
        return ResponseEntity.ok(announceBoardService.getLatestAnnounce());
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
