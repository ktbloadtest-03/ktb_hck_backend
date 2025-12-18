package ktb.backend.service;

import ktb.backend.dto.LocationCoordinates;
import ktb.backend.dto.LocationCoordinatesProjection;
import ktb.backend.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public Map<Long, LocationCoordinates> getLocationCoordinatesByReportIds(List<Long> reportIds) {
        return locationRepository.findCoordinatesByReportIds(reportIds)
                .stream()
                .collect(Collectors.toMap(
                        LocationCoordinatesProjection::getReportId,
                        projection -> new LocationCoordinates(
                                projection.getReportId(),
                                projection.getLatitude(),
                                projection.getLongitude()
                        )
                ));

    }
}
