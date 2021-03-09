package zyd.datacenter.Service.Mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import zyd.datacenter.Payload.Result;

public interface MailService {
    public Result sendSimpleMail(String to, String subject, String content);
}