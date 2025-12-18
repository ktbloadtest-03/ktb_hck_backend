package ktb.backend.facade;

import ktb.backend.dto.LocationCoordinates;
import ktb.backend.dto.PageApiResponse;
import ktb.backend.dto.PageInfo;
import ktb.backend.dto.response.GetReportResponse;
import ktb.backend.entity.Report;
import ktb.backend.entity.ReportImage;
import ktb.backend.enums.ReportType;
import ktb.backend.service.LocationService;
import ktb.backend.service.ReportImageService;
import ktb.backend.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportQueryFacade {
    private final ReportService reportService;
    private final LocationService locationService;
    private final ReportImageService reportImageService;

    public PageApiResponse<GetReportResponse> getPagedReportsByReportType(ReportType reportType, int page, int size) {
        Page<Report> pagedReports = reportService.findByReportType(reportType, page, size);

        List<Long> reportIds = pagedReports.stream().map(Report::getId).toList();
        Map<Long, LocationCoordinates> locationCoordinatesMap = locationService.getLocationCoordinatesByReportIds(reportIds);

        java.util.Map<Long, List<Long>> reportImageIdsMap = reportImageService.findAllByReportId(reportIds);

        List<GetReportResponse> responses = pagedReports.getContent().stream()
                .map(report -> {
                    LocationCoordinates coordinates = locationCoordinatesMap.get(report.getId());
                    List<Long> reportImageIds = reportImageIdsMap.getOrDefault(report.getId(), List.of());

                    return GetReportResponse.of(report, report.getLocation(), coordinates, reportImageIds);
                }).toList();

        PageInfo pageInfo = PageInfo.from(pagedReports);


        return PageApiResponse.success(responses, pageInfo);
    }
}
