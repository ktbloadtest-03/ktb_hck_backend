package ktb.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender javaMailSender;

    @Transactional
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage emailForm = createEmailForm(to, subject, text);
        try {
            javaMailSender.send(emailForm);
        } catch (RuntimeException e) {
            throw new RuntimeException("CANNOT_SEND_EMAIL");
        }
    }

    public void sendEmailWithHtmlContent(String to)
            throws MessagingException {
        String subject = "[HighPaw] 신고 접수 안내";
        String htmlContent = "<html>" +
                "<body>" +
                "<img src='https://fastly.picsum.photos/id/10/2500/1667.jpg?hmac=J04WWC_ebchx3WwzbM-Z4_KC_LeLBWr5LZMaAkWkF68' " +
                "alt='Logo' " +
                "style='width: 200px; height: auto; margin-bottom: 20px;'>" +
                "<p>안녕하세요, HighPaw 팀입니다.</p>" +
                "<p>신고가 성공적으로 접수되었음을 메일을 통해 알려드립니다.</p>" +
                "<br><p>내역확인을 원하실 경우 회원가입을 진행해주시면 감사하겠습니다.</p>" +
                "<br><p style = 'font-weight: bold;'>Hipaw 사이트 링크 : " +
                "<a href='https://high-paw.click'>바로가기</a>" +
                "</p>" +
                "<br><p>감사합니다.</p>" +
                "</body>" +
                "</html>";

        MimeMessage htmlEmailForm = createHtmlEmailForm(to,subject,htmlContent);
        try {
            javaMailSender.send(htmlEmailForm);
        } catch (Exception e) {
            throw new RuntimeException("CANNOT_SEND_EMAIL");
        }
    }

    private SimpleMailMessage createEmailForm(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        return message;
    }


    private MimeMessage createHtmlEmailForm(String to, String subject, String htmlContent)
            throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        return message;
    }
}
