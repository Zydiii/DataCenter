package zyd.datacenter.Service.Auth;

import org.springframework.http.ResponseEntity;
import zyd.datacenter.Payload.Request.LoginRequest;
import zyd.datacenter.Payload.Request.SignupRequest;
import zyd.datacenter.Payload.Response.JwtResponse;
import zyd.datacenter.Payload.Result;

public interface AuthService {
    public JwtResponse loginCheck(LoginRequest loginRequest);

    public ResponseEntity<?> signUpCheck(SignupRequest signUpRequest);

    public Result logOut(String username);

    public Result getPassword(String email);
}
