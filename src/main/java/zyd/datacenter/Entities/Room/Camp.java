package zyd.datacenter.Entities.Room;

import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import zyd.datacenter.Entities.User.UserInRoom;

import java.util.LinkedList;
import java.util.List;

@Document(value = "Camp")
@ApiModel(value="Camp", description="阵营数据，目前没有用")
public class Camp {
    @Id
    private String id;

    private String campName; // 阵营名称

    private int campId; // 编号，从0->N-1

    private List<UserInRoom> usersInCamp = new LinkedList<>();

    private int maxUserNum;

    private int userNum = 0;

    @Version
    private Long version;

    public Camp(int campId, int maxUserNum) {
        this.campId = campId;
        this.campName = "队伍" + campId;
        this.maxUserNum = maxUserNum;
    }

    public int getUserNum() {
        return userNum;
    }

    public void setUserNum(int userNum) {
        this.userNum = userNum;
    }

    public String getCampName() {
        return campName;
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public List<UserInRoom> getUsersInCamp() {
        return usersInCamp;
    }

    public void setUsersInCamp(List<UserInRoom> usersInCamp) {
        this.usersInCamp = usersInCamp;
    }

    public int getCampId() {
        return campId;
    }

    public void setCampId(int campId) {
        this.campId = campId;
    }

    public int getMaxUserNum() {
        return maxUserNum;
    }

    public void setMaxUserNum(int maxUser) {
        this.maxUserNum = maxUser;
    }

    public void leaveCamp(UserInRoom user)
    {
        for(UserInRoom userInRoom: usersInCamp){
            if(userInRoom.getUserId().equals(user.getUserId())){
                usersInCamp.remove(userInRoom);
                userNum--;
                break;
            }
        }
    }

    public void joinCamp(UserInRoom user)
    {
        boolean found = false;
        for(UserInRoom userInRoom : usersInCamp)
        {
            if(user.getUserId().equals(userInRoom.getUserId()))
            {
                found = true;
                break;
            }
        }
        if(found)
            return;
        this.usersInCamp.add(user);
        this.userNum++;
    }

    public void changeUserState(UserInRoom user)
    {
        for(UserInRoom userInRoom: usersInCamp){
            if(userInRoom.getUserId().equals(user.getUserId())){
                userInRoom.setState(user.getState());
                break;
            }
        }
    }

    public void changeUser(UserInRoom userInRoom){
        for(UserInRoom user: usersInCamp){
            if(user.getUserId().equals(userInRoom.getUserId())){
                user.setScore(userInRoom.getScore());
                user.setDamageValue(userInRoom.getDamageValue());
                user.setCrashNum(userInRoom.getCrashNum());
                user.setDestroyNum(userInRoom.getDestroyNum());
                user.setResult(userInRoom.getResult());
                break;
            }
        }
    }
}
