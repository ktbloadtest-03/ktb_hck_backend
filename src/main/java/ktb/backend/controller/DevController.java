package ktb.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import ktb.backend.service.AiService;
import ktb.backend.service.MailService;
import ktb.backend.utils.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DevController {
    private final AiService aiService;
    private final MailService mailService;
    private final Snowflake snowflake;

    @Operation(summary = "test용 API")
    @PostMapping("/dev/test")
    public String test() {
        return "test sucecss";
    }


    @PostMapping(value = "/dev/ai/test", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<Void> testAi(
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart("description") String description
    ) {
        long id = snowflake.nextId();
        aiService.analyze(images, id,description);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/dev/mail/test")
    public ResponseEntity<Void> testMail() throws MessagingException {
        return null;
    }
}
