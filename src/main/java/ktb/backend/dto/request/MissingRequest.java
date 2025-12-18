package ktb.backend.dto.request;

import ktb.backend.enums.AnimalType;
import ktb.backend.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

public record MissingRequest(
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
    String featureDetail
) {
}
