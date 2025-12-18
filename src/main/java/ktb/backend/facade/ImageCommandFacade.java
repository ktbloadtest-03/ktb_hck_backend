package ktb.backend.facade;

import jakarta.transaction.Transactional;
import ktb.backend.dto.AiServerResponse;
import ktb.backend.entity.Image;
import ktb.backend.entity.Report;
import ktb.backend.events.ImageUploadEvent;
import ktb.backend.service.AiService;
import ktb.backend.service.ImageService;
import ktb.backend.service.ReportService;
import ktb.backend.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageCommandFacade {
    private final AiService aiService;
    private final ApplicationEventPublisher eventPublisher;
    private final ImageService imageService;

    @Transactional
    public AiServerResponse analyzeImages(List<MultipartFile> imageFiles, long id, String description) {
        AiServerResponse aiServerResponse = aiService.analyze(imageFiles, id, description);

        List<Image> imageEntities = imageFiles.stream()
                .map(img -> imageService.saveImage()).toList();

        eventPublisher.publishEvent(new ImageUploadEvent(imageFiles, imageEntities));
        return aiServerResponse;
    }
}
