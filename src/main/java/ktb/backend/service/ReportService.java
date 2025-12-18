package ktb.backend.service;

import ktb.backend.entity.Report;
import ktb.backend.enums.ReportType;
import ktb.backend.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {
    private final ReportRepository reportRepository;

    public Report findById(long id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT_FOOUND_REPORT"));
    }

    public Page<Report> findByReportType(ReportType reportType, int page, int size) {
        return reportRepository.findByReportType(reportType, PageRequest.of(page-1,size));
    }
}
