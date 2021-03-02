package zyd.datacenter.Controllers.AnnounceBoard;

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
public class AnnounceBoardController {
    @Autowired
    private AnnounceBoardService announceBoardService;

    @PostMapping("/manage/write")
    public ResponseEntity<?> writeAnnounce(@Valid @RequestBody AnnouncementBoard announceBoard){
        Result result = announceBoardService.writeAnnounce(announceBoard);
        System.out.println();
        return resultToResponse(result);
    }

    @GetMapping("/user/getLatest")
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
