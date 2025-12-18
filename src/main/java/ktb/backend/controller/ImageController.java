package ktb.backend.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import ktb.backend.dto.ListResponse;
import ktb.backend.dto.response.ImageUrlResponse;
import ktb.backend.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "이미지 API", description = "이미지 관련 API입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/images/urls")
    public ResponseEntity<ListResponse<ImageUrlResponse>> getPresignedUrl(@RequestBody List<Long> imageIds) {
        imageService.ge
    }
}
