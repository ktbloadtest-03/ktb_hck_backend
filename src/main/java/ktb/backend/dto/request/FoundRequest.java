package ktb.backend.dto.request;

import ktb.backend.entity.Location;
import ktb.backend.entity.Report;
import ktb.backend.enums.ReportType;

import java.time.LocalDateTime;

public record FoundRequest(
    LocalDateTime foundTime,
    double latitude,
    double longitude,
    String lostLocationDetail,
    String featureDetail
) {
    public Report toEntity(Long id, Location location) {
        return Report.builder()
            .id(id)
            .reportType(ReportType.FOUND)
            .caseTime(foundTime)
            .additionalInfo(featureDetail)
            .location(location)
            .build();
    }
}
