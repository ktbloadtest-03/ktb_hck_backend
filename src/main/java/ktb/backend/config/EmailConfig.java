package ktb.backend.config;

import ktb.backend.properties.EmailProperties;
import ktb.backend.properties.SmtpProperties;
import org.springframework.mail.javamail.JavaMailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class EmailConfig {

    private final EmailProperties emailProperties;
    private final SmtpProperties smtpProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(emailProperties.host());
        mailSender.setPort(emailProperties.port());
        mailSender.setUsername(emailProperties.username());
        mailSender.setPassword(emailProperties.password());
        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setJavaMailProperties(getMailProperties());

        return mailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", smtpProperties.auth());
        properties.put("mail.smtp.starttls.enable", smtpProperties.starttls().enable());
        properties.put("mail.smtp.starttls.required", smtpProperties.starttls().required());
        properties.put("mail.smtp.connectiontimeout", smtpProperties.connectiontimeout());
        properties.put("mail.smtp.timeout", smtpProperties.timeout());
        properties.put("mail.smtp.writetimeout", smtpProperties.writetimeout());

        return properties;
    }
}
