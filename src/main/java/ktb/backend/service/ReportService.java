package ktb.backend.service;

import ktb.backend.dto.request.FoundRequest;
import ktb.backend.dto.request.MissingRequest;
import ktb.backend.entity.Location;
import ktb.backend.entity.Report;
import ktb.backend.repository.LocationRepository;
import ktb.backend.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {
    private final ReportRepository reportRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public Report createMissingReport(Long id, MissingRequest request) {
        Location location = getLocation(id, request.latitude(), request.longitude(), request.lostLocationDetail());
        Report report = request.toEntity(id, location);
        return reportRepository.save(report);
    }

    @Transactional
    public Report createFoundReport(Long id, FoundRequest request) {
        Location location = getLocation(id, request.latitude(), request.longitude(), request.lostLocationDetail());
        Report report = request.toEntity(id, location);
        return reportRepository.save(report);
    }

    private Location getLocation(Long id, double latitude, double longitude, String detail) {
        locationRepository.saveLocation(id, latitude, longitude, detail);
        Location location = locationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NOT_FOUND_LOCATION"));
        log.info("coordinate: {}", location.getCoordinate().toString());
        return location;
    }

    private Report findById(long id) {
        return reportRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("NOT_FOUND_REPORT"));
    }

    public Page<Report> findByReportType(ReportType reportType, int page, int size) {
        return reportRepository.findByReportType(reportType, PageRequest.of(page-1,size));
    }
}
