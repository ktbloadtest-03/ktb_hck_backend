package ktb.backend.service;

import ktb.backend.entity.Image;
import ktb.backend.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public Image saveImage(Long id) {
        Image image = new Image(id);
        return imageRepository.save(image);
    }

    public Image findById(Long imageId) {
        return imageRepository.findById(imageId).orElseThrow(() -> new RuntimeException("NOT_FOUND_IMAGE"));
    }

    public List<Image> findByIds(List<Long> imageIds) {
        return imageRepository.findAllById(imageIds);
    }


}
