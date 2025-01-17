package zyd.datacenter.Security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.bson.types.Binary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import zyd.datacenter.Entities.User.User;

import java.util.*;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;

    private String username;
    private String avatar;

    private String email;

    private String phone;
    private Set<String> roles;
    private float score; // 玩家总积分
    private int level; // 用户等级
    private int EXP; // 经验值
    private Set<String> weapons; // 拥有的武器编号
    private Set<String> realNames; // 玩家真实姓名
    private Set<String> realIds; // 玩家学号


    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String id, String username, String avatar, String email, String phone, Set<String> roles, float score, int level, int EXP, Set<String> weapons, Set<String> realNames, Set<String> realIds, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.roles = roles;
        this.score = score;
        this.level = level;
        this.EXP = EXP;
        this.weapons = weapons;
        this.realNames = realNames;
        this.realIds = realIds;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                //.map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        if(user.getAvatarBase().length() == 0)
            user.setAvatarBase("");

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getAvatarBase(),
                user.getEmail(),
                user.getPhone(),
                user.getRoles(),
                user.getScore(),
                user.getLevel(),
                user.getEXP(),
                user.getWeapons(),
                user.getRealNames(),
                user.getRealIds(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
