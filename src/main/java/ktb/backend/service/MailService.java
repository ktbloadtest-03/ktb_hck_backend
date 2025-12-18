package ktb.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

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

    public void sendEmailWithHtmlContent(String to, MultipartFile image)
            throws MessagingException, IOException {
        String subject = "[HighPaw] 신고 접수 안내";
        String cid = "reportImage";

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);

        String htmlContent =
                "<html>" +
                        "<body style='margin:0; padding:24px; background:#f6f6f6;'>" +
                        "<table width='100%' cellpadding='0' cellspacing='0'>" +
                        "<tr>" +
                        "<td align='center'>" +
                        "<table width='600' cellpadding='0' cellspacing='0' " +
                        "style='background:#ffffff; padding:24px; border-radius:12px;'>" +
                        "<tr>" +
                        "<td align='center'>" +

                        "<img src='cid:" + cid + "' " +
                        "alt='Detected Image' " +
                        "width='500' " +
                        "style='display:block; max-width:100%; height:auto; " +
                        "margin-bottom:24px; border-radius:14px;' />" +

                        "<p>안녕하세요, HighPaw 팀입니다.</p>" +
                        "<p>실종 신고 접수해주신 반려 동물과 유사한 반려동물이 발견되어 사진과 함께 메일 드립니다.</p>" +
                        "<br><p>아래 링크를 클릭해 사이트에서 정보를 확인하실 수 있습니다.</p>" +
                        "<br><p style='font-weight: bold;'>Hipaw 사이트 링크 : " +
                        "<a href='https://high-paw.click'>바로가기</a>" +
                        "</p>" +
                        "<br><p>감사합니다.</p>" +

                        "</td>" +
                        "</tr>" +
                        "</table>" +
                        "</td>" +
                        "</tr>" +
                        "</table>" +
                        "</body>" +
                        "</html>";


        helper.setText(htmlContent, true);


        helper.addInline(
                cid,
                new ByteArrayResource(image.getBytes()),
                Optional.ofNullable(image.getContentType()).orElse("image/jpeg")
        );

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException("CANNOT_SEND_EMAIL", e);
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
