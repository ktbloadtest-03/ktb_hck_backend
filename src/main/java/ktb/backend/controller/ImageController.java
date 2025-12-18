package ktb.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ktb.backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "이미지 API", description = "이미지 관련 API입니다.")
@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
}
