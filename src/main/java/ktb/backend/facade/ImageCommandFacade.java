package ktb.backend.facade;

import ktb.backend.service.ImageService;
import ktb.backend.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageCommandFacade {
    private final S3Service s3Service;
    private final ImageService imageService;

    public void saveImages(List<MultipartFile> images) {
        //ai로 전송
        //s3로 업로드
    }
}
