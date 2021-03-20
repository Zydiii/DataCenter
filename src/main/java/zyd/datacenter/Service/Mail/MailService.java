package zyd.datacenter.Service.Mail;

import com.mongodb.MongoCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.transaction.annotation.Transactional;
import zyd.datacenter.Payload.Result;

public interface MailService {
    @Transactional
    @Retryable(value = MongoCommandException.class, maxAttempts = 10, backoff = @Backoff(delay = 100))
    public Result sendSimpleMail(String to, String subject, String content);
}