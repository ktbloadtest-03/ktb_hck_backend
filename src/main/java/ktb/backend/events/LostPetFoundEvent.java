package ktb.backend.events;

import ktb.backend.entity.Report;
import org.springframework.web.multipart.MultipartFile;

public record LostPetFoundEvent(
        Report report,
        MultipartFile image
) {
}
