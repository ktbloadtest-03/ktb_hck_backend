package ktb.backend.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ktb.backend.dto.AiServerResponse;
import ktb.backend.dto.PageApiResponse;
import ktb.backend.dto.request.FoundRequest;
import ktb.backend.dto.request.MissingRequest;
import ktb.backend.dto.response.GetReportResponse;
import ktb.backend.entity.Report;
import ktb.backend.enums.ReportType;
import ktb.backend.facade.ImageCommandFacade;
import ktb.backend.facade.ReportQueryFacade;
import ktb.backend.service.ReportService;
import ktb.backend.utils.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "신고 API", description = "신고 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173"})
public class ReportController {
    private final ReportService reportService;
    private final ImageCommandFacade imageCommandFacade;
    private final Snowflake snowflake;
    private final ReportQueryFacade reportQueryFacade;

    @Operation(summary = "실종 신고", description = "내 반려 동물을 실종한 경우에 실종 관련 내용을 신고합니다.")
    @PostMapping("/report/missing")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "no_content"),
            @ApiResponse(responseCode = "400", description = "invalid_request"),
            @ApiResponse(responseCode = "500", description = "internal_server_error")
    })
    public ResponseEntity<Void> reportMissing(
            @RequestPart(value = "images", required = false) List<MultipartFile> images,
            @RequestPart(value = "request") MissingRequest request
    ) {
        long id = snowflake.nextId();
        Report missingReport = reportService.createMissingReport(id, request);
        AiServerResponse aiServerResponse = imageCommandFacade.analyzeImages(images, missingReport, request.featureDetail());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "발견 신고", description = "실종 신고로 보이는 동물을 발견한 경우 해당 내용을 신고합니다.")
    @PostMapping("/report/found")
    public ResponseEntity<Void> reportFound(
        @RequestPart(value = "images", required = false) List<MultipartFile> images,
        @RequestPart(value = "request") FoundRequest request
    ) {
        long id = snowflake.nextId();
        Report foundReport = reportService.createFoundReport(id, request);
        imageCommandFacade.analyzeScores(images, foundReport);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "접수 내역 조회", description = "접수 내역 타입에 따라서 관련 내용을 구분해 페이징하여 조회합니다. ReportType은 LOST,FOUND 2가지로 구분되며, page1는 1부터 시작하고, size의 default크기는 10입니다.")
    @GetMapping("/report/{reportType}")
    public ResponseEntity<PageApiResponse<GetReportResponse>> getReportRecord(@PathVariable ReportType reportType,
                                                                              @RequestParam(defaultValue = "1") int page,
                                                                              @RequestParam(defaultValue = "10") int size) {
        PageApiResponse<GetReportResponse> reportRecords = reportQueryFacade.getPagedReportsByReportType(reportType, page, size);
        return ResponseEntity.ok(reportRecords);
    }
}
