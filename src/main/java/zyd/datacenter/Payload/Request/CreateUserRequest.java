package zyd.datacenter.Payload.Request;

import java.util.HashSet;
import java.util.Set;

public class CreateUserRequest {
    private String username; // 用户名

    private String password; // 密码

    private String email; // 邮箱

    private String phone; // 手机号

    private int active; // 是否可用, 1为可用, 0为禁用

    private float score; // 玩家总积分

    private int level; // 用户等级

    private int EXP; // 经验值

    private Set<String> roles = new HashSet<>(); // 角色名称

    private Set<String> weapons = new HashSet<>(); // 拥有的武器编号

    private Set<String> realNames = new HashSet<>(); // 玩家真实姓名

    private Set<String> realIds = new HashSet<>(); // 玩家学号

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEXP() {
        return EXP;
    }

    public void setEXP(int EXP) {
        this.EXP = EXP;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(Set<String> weapons) {
        this.weapons = weapons;
    }

    public Set<String> getRealNames() {
        return realNames;
    }

    public void setRealNames(Set<String> realNames) {
        this.realNames = realNames;
    }

    public Set<String> getRealIds() {
        return realIds;
    }

    public void setRealIds(Set<String> realIds) {
        this.realIds = realIds;
    }
}
