package ktb.backend.events.handler;

import ktb.backend.entity.Image;
import ktb.backend.entity.ReportImage;
import ktb.backend.events.ImageUploadEvent;
import ktb.backend.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class ImageUploadEventHandler {
    private final S3Service s3Service;

    @Async
    @EventListener
    public void handler(ImageUploadEvent event) {
        IntStream.range(0, event.imageEntities().size())
                .parallel()
                .forEach(i -> {
                    ReportImage imageEntity = event.imageEntities().get(i);
                    MultipartFile file = event.imageFiles().get(i);

                    s3Service.uploadPetImage(
                            imageEntity.getId(),
                            file
                    );
                });

    }

}
