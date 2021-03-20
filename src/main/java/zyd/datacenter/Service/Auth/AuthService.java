package zyd.datacenter.Service.Auth;

import com.mongodb.MongoCommandException;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Payload.Request.LoginRequest;
import zyd.datacenter.Payload.Request.SignupRequest;
import zyd.datacenter.Payload.Response.JwtResponse;
import zyd.datacenter.Payload.Result;

public interface AuthService {
    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public JwtResponse loginCheck(LoginRequest loginRequest);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public ResponseEntity<?> signUpCheck(SignupRequest signUpRequest);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result logOut(String username);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result getPasswordEmail(String email);

    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result getPasswordUsername(String username);

}
