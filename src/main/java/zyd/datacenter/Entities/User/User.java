package zyd.datacenter.Entities.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import org.bson.types.Binary;

@Document(collection = "Users")
@ApiModel(value="Users", description="用户数据，用户的主要信息，包括账号、等级、分数等")
public class User {
    @Id
    private String id;

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @ApiModelProperty(value = "头像")
    private Binary avatar; // 用户头像

    private String avatarBase = ""; // Base64

    @ApiModelProperty(value = "用户名")
    private String username; // 用户名

    @ApiModelProperty(value = "加密后的密码，仅用于登录验证")
    private String password; // 加密密码

    @ApiModelProperty(value = "明文密码，即用户自己设定的密码")
    private String pairPassword; // 明文密码

    @ApiModelProperty(value = "邮箱")
    private String email; // 邮箱

    @ApiModelProperty(value = "手机号")
    private String phone; // 手机号

    @ApiModelProperty(value = "是否可用，0->禁用，1->可用，默认为1")
    private int active; // 是否可用, 1为可用, 0为禁用

    @ApiModelProperty(value = "玩家总积分")
    private float score; // 玩家总积分

    @ApiModelProperty(value = "用户等级")
    private int level; // 用户等级

    @ApiModelProperty(value = "经验值")
    private int EXP; // 经验值

    @ApiModelProperty(value = "用户目前的状态，0->离线，1->在线，2->在房间中，3->在游戏中，4->观战中，-1->弃用")
    private int state; // 目前状态，0为离线，1为在线，2为在房间中，3为在游戏中，4为观战中，-1为已弃用

    @ApiModelProperty(value = "用户具有的权限，ROLE_USER->普通玩家，ROLE_MODERATOR->导调台，ROLE_ADMIN->管理员")
    private Set<String> roles = new HashSet<>(); // 角色名称

    @ApiModelProperty(value = "拥有的武器编号，目前没有用")
    private Set<String> weapons = new HashSet<>(); // 拥有的武器编号

    @ApiModelProperty(value = "玩家的真实姓名")
    private Set<String> realNames = new HashSet<>(); // 玩家真实姓名

    @ApiModelProperty(value = "玩家学号")
    private Set<String> realIds = new HashSet<>(); // 玩家学号

    @Version
    private Long version;

    public User(String username, String password, String pairPassword, String email, String phone, Set<String> roles, Set<String> realNames, Set<String> realIds) {
        this.username = username;
        this.password = password;
        this.pairPassword = pairPassword;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.realNames = realNames;
        this.realIds = realIds;
        this.active = 1;
        this.score = 0;
        this.level = 1;
        this.EXP = 0;
        this.state = 0;
    }

    public Set<String> getWeapons() {
        return weapons;
    }

    public void setWeapons(Set<String> weapons) {
        this.weapons = weapons;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getAvatarBase() {
        return avatarBase;
    }

    public void setAvatarBase(String avatarBase) {
        this.avatarBase = avatarBase;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
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

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPairPassword() {
        return pairPassword;
    }

    public void setPairPassword(String pairPassword) {
        this.pairPassword = pairPassword;
    }

    public Binary getAvatar() {
        return avatar;
    }

    public void setAvatar(Binary avatar) {
        this.avatar = avatar;
    }
}
