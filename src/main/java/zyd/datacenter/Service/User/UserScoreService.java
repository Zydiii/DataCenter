package zyd.datacenter.Service.User;

import zyd.datacenter.Entities.User.UserScore;
import zyd.datacenter.Payload.Result;

public interface UserScoreService {
    public Result addUserScore(UserScore userScore);
}
