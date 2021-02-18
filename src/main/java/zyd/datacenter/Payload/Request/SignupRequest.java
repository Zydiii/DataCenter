package zyd.datacenter.Payload.Request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 15)
    private String phone; // 手机号

    private Set<String> roles;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private Set<String> realNames = new HashSet<>(); // 玩家真实姓名

    private Set<String> realIds = new HashSet<>(); // 玩家学号

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
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
