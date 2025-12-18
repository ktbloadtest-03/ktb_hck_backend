package ktb.backend.facade;

import ktb.backend.service.ImageService;
import ktb.backend.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageQueryFacade {
    private final ImageService imageService;
    private final S3Service s3Service;


}
