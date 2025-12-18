package ktb.backend.service;

import ktb.backend.entity.Report;
import ktb.backend.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportService {
    private final ReportRepository reportRepository;

    public Report findById(long id) {
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("NOT_FOOUND_REPORT"));
    }
}
