package zyd.datacenter.Payload.Response;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String username;
    private String email;
    private String phone;
    private List<String> roles;
    private float score; // 玩家总积分
    private int level; // 用户等级
    private int EXP; // 经验值
    private Set<String> weapons; // 拥有的武器编号
    private Set<String> realNames; // 玩家真实姓名
    private Set<String> realIds; // 玩家学号
    private int code; // 0为登录失败，1为登陆成功
    private String message; // 登录结果信息提示

    public JwtResponse(String token, String id, String username, String email, String phone, float score, int level, int EXP, Set<String> weapons, Set<String> realNames, Set<String> realIds, List<String> roles, String message) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.score = score;
        this.level = level;
        this.EXP = EXP;
        this.weapons = weapons;
        this.realNames = realNames;
        this.realIds = realIds;
        this.code = 1;
        this.message = message;
    }

    public JwtResponse(String message)
    {
        this.code = 0;
        this.message = message;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
