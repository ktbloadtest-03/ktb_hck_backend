package ktb.backend.repository;

import ktb.backend.entity.ReportImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportImageRepository extends JpaRepository<ReportImage, Long> {
    @Query("select ri from ReportImage ri join fetch ri.report r where r.id in :reportIds")
    public List<ReportImage> findAllByReportId(@Param("reportIds") List<Long> reportIds);
}
