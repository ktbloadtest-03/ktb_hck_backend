package ktb.backend.events;

import ktb.backend.entity.ReportImage;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record ImageUploadEvent(
    List<MultipartFile> imageFiles,
    List<ReportImage> imageEntities
) {
}
