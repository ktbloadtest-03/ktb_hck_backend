package ktb.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ktb.backend.dto.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "신고 API", description = "신고 관련 API")
@RestController
public class ReportController {

    @Operation(summary = "실종 신고", description = "내 반려 동물을 실종한 경우에 실종 관련 내용을 신고합니다.")
    @PostMapping("/report/missing")
    public ResponseEntity<APIResponse<Void>> reportMissing() {
        return null;
    }

    @Operation(summary = "발견 신고", description = "실종 신고로 보이는 동물을 발견한 경우 해당 내용을 신고합니다.")
    @PostMapping("/report/found")
    public ResponseEntity<APIResponse<Void>> reportFound() {
        return null;
    }


}
