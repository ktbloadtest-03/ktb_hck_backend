package ktb.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ktb.backend.dto.APIResponse;
import ktb.backend.dto.request.MissingRequest;
import ktb.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "신고 API", description = "신고 관련 API")
@RestController
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @Operation(summary = "실종 신고", description = "내 반려 동물을 실종한 경우에 실종 관련 내용을 신고합니다.")
    @PostMapping("/report/missing")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "no_content"),
            @ApiResponse(responseCode = "400", description = "invalid_request"),
            @ApiResponse(responseCode = "500", description = "internal_server_error")
    })
    public ResponseEntity<Void> reportMissing(@RequestBody MissingRequest request) {
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "발견 신고", description = "실종 신고로 보이는 동물을 발견한 경우 해당 내용을 신고합니다.")
    @PostMapping("/report/found")
    public ResponseEntity<Void> reportFound() {
        return null;
    }


}
