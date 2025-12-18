package ktb.backend.events.handler;

import jakarta.mail.MessagingException;
import ktb.backend.events.LostPetFoundEvent;
import ktb.backend.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LostPetFoundEventHandler {
    private final MailService mailService;

    @EventListener
    public void handle(LostPetFoundEvent event) throws MessagingException, IOException {
        mailService.sendEmailWithHtmlContent(event.report().getEmail(), event.image());
    }
}
