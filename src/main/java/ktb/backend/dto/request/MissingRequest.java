package ktb.backend.dto.request;

import ktb.backend.enums.AnimalType;
import ktb.backend.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

public record MissingRequest(
        String petName,
        AnimalType types,
        Gender gender,
        LocalDateTime lostTime,
        double latitude,
        double longitude,
        String lostLocationDetail,
        String featureDetail,
        List<Long> lostPetImages
) {
}
