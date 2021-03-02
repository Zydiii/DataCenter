package zyd.datacenter.Service.Impl.AnnounceBoard;

import org.springframework.stereotype.Service;
import zyd.datacenter.Entities.AnnounceBoard.AnnouncementBoard;
import zyd.datacenter.Payload.Result;
import zyd.datacenter.Repository.AnnounceBoard.AnnounceBoardRepository;
import zyd.datacenter.Repository.User.UserRepository;
import zyd.datacenter.Service.AnnounceBoard.AnnounceBoardService;

import java.util.Date;
import java.util.List;

@Service
public class AnnounceBoardServiceImpl implements AnnounceBoardService {
    private AnnounceBoardRepository announceBoardRepository;

    private UserRepository userRepository;

    public AnnounceBoardServiceImpl(AnnounceBoardRepository announcementBoardRepository, UserRepository userRepository) {
        this.announceBoardRepository = announcementBoardRepository;
        this.userRepository = userRepository;
    }

    public Result writeAnnounce(AnnouncementBoard announceBoard){
        String managerId = announceBoard.getUserId();
        if(userRepository.existsById(managerId)){
            Date date = new Date();
            announceBoard.setDate(date);
            announceBoardRepository.insert(announceBoard);
            return new Result("操作成功", 1);
        }
        else
            return new Result("没有权限", 0);
    }

    public AnnouncementBoard getLatestAnnounce(){
        List<AnnouncementBoard> announceBoards = announceBoardRepository.findAll();
        if(announceBoards.isEmpty())
            return null;
        else
            return announceBoards.get(announceBoards.size() - 1);
    }

}
