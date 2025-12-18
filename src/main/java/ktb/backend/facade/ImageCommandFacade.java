package ktb.backend.facade;

import jakarta.transaction.Transactional;
import ktb.backend.dto.AiAnalysisResult;
import ktb.backend.entity.Image;
import ktb.backend.service.AiService;
import ktb.backend.service.ImageService;
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

    private final S3Service s3Service;
    private final ImageService imageService;

    @Transactional
    public void analyzeImages(List<MultipartFile> images, long id, String description) {
        //ai로 전송
        //s3로 업로드
        AiAnalysisResult aiAnalysisResult = aiService.analyze(images, id, description);

        List<Image> originalImages = images.stream()
                .map(img -> imageService.saveImage()).toList();
    }
}
