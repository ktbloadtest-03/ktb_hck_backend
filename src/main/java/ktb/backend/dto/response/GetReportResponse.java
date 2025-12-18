package ktb.backend.dto.response;

import ktb.backend.dto.LocationCoordinates;
import ktb.backend.entity.Location;
import ktb.backend.entity.Report;
import ktb.backend.enums.AnimalType;
import ktb.backend.enums.Gender;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record GetReportResponse(
        String email,
        String phoneName,
        String petName,
        AnimalType types,
        Gender gender,
        String species,
        LocalDateTime lostTime,
        double latitude,
        double longitude,
        String lostLocationDetail,
        String featureDetail,
        List<Long> reportImageIds
) {
    public static GetReportResponse of(Report report, Location location, LocationCoordinates coordinates, List<Long> reportImageIds) {
        return GetReportResponse.builder()
                .email(report.getEmail())
                .phoneName(report.getPhoneNumber())
                .petName(report.getPetName())
                .types(report.getTypes())
                .gender(report.getGender())
                .species(report.getSpecies())
                .lostTime(report.getCaseTime())
                .latitude(coordinates.latitude())
                .longitude(coordinates.longitude())
                .lostLocationDetail(location.getDetail())
                .featureDetail(report.getAdditionalInfo())
                .reportImageIds(reportImageIds)
                .build();
    }
}
