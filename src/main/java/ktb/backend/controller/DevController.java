package ktb.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import ktb.backend.dto.AiAnalysisResult;
import ktb.backend.service.AiService;
import ktb.backend.utils.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class DevController {
    private final AiService aiService;
    private final Snowflake snowflake;

    @Operation(summary = "test용 API")
    @PostMapping("/dev/test")
    public String test() {
        return "test sucecss";
    }


    @PostMapping("/dev/ai/test")
    public ResponseEntity<AiAnalysisResult> testAi(
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        long id = snowflake.nextId();
        AiAnalysisResult aiAnalysisResult = aiService.analyze(images, id);
        return ResponseEntity.ok(aiAnalysisResult);
    }
}
