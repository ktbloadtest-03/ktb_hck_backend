package ktb.backend.repository;

import ktb.backend.entity.Report;
import ktb.backend.enums.ReportType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
    Page<Report> findByReportType(ReportType reportType, Pageable pageable);
}
