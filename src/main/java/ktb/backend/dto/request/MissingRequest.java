package ktb.backend.dto.request;

import ktb.backend.entity.Location;
import ktb.backend.entity.Report;
import ktb.backend.enums.AnimalType;
import ktb.backend.enums.Gender;
import ktb.backend.enums.ReportType;
import lombok.Getter;

import java.time.LocalDateTime;

public record MissingRequest(
    String email,
    String phoneNumber,
    String petName,
    AnimalType types,
    Gender gender,
    String species,
    LocalDateTime lostTime,
    double latitude,
    double longitude,
    String locationName,
    String lostLocationDetail,
    String featureDetail
) {
    public Report toEntity(Long id, Location location) {
        return Report.builder()
            .id(id)
            .reportType(ReportType.LOST)
            .caseTime(lostTime)
            .additionalInfo(featureDetail)
            .email(email)
            .phoneNumber(phoneNumber)
            .petName(petName)
            .types(types)
            .gender(gender)
            .species(species)
            .location(location)
            .build();
    }
}
