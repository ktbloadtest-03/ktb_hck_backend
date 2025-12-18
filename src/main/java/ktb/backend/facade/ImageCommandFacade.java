package ktb.backend.facade;

import jakarta.transaction.Transactional;
import ktb.backend.dto.AiScoreResponse;
import ktb.backend.dto.AiServerResponse;
import ktb.backend.entity.Report;
import ktb.backend.entity.ReportImage;
import ktb.backend.events.ImageUploadEvent;
import ktb.backend.events.LostPetFoundEvent;
import ktb.backend.repository.ReportRepository;
import ktb.backend.service.AiService;
import ktb.backend.service.ImageService;
import ktb.backend.service.ReportImageService;
import ktb.backend.service.ReportService;
import ktb.backend.utils.Snowflake;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageCommandFacade {
    private final AiService aiService;
    private final ApplicationEventPublisher eventPublisher;
    private final ImageService imageService;
    private final ReportImageService reportImageService;
    private final ReportRepository reportRepository;

    private final Snowflake snowflake;

    @Transactional
    public AiServerResponse analyzeImages(List<MultipartFile> imageFiles, Report report, String description) {
        AiServerResponse aiServerResponse = aiService.analyze(imageFiles, report.getId(), description);

        List<ReportImage> imageEntities = imageFiles.stream()
            .map(img -> reportImageService.saveReportImage(report))
            .toList();

        log.info("{}", aiServerResponse.toString());
        eventPublisher.publishEvent(new ImageUploadEvent(imageFiles, imageEntities));
        return aiServerResponse;
    }

    @Transactional
    public void analyzeScores(List<MultipartFile> imageFiles, Report report) {
        AiScoreResponse[] aiScoreResponses = aiService.score(imageFiles);

        List<ReportImage> imageEntities = imageFiles.stream()
            .map(img -> reportImageService.saveReportImage(report))
            .toList();

        Arrays.stream(aiScoreResponses)
                .filter(response -> response.score() >= 0.3)
                .forEach(response -> {
                    Report missingReport = reportRepository.findById(response.id())
                        .orElseThrow(() -> new RuntimeException("NOT_FOUND_REPORT"));
                    log.info("{}", response.toString());
                    eventPublisher.publishEvent(new LostPetFoundEvent(missingReport, imageFiles.getFirst()));
                });
    }
}
