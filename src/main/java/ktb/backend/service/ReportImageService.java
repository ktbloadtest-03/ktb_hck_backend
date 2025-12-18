package ktb.backend.service;

import ktb.backend.entity.Report;
import ktb.backend.entity.ReportImage;
import ktb.backend.repository.ReportImageRepository;
import ktb.backend.utils.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportImageService {
    private final Snowflake snowflake;
    private final ReportImageRepository reportImageRepository;

    public ReportImage saveReportImage(Report report) {
        Long id = snowflake.nextId();
        return reportImageRepository.save(new ReportImage(id, report));
    }

    public Map<Long, List<Long>> findAllByReportId(List<Long> reportIds) {
        List<ReportImage> reportImages =  reportImageRepository.findAllByReportId(reportIds);


        return reportImages.stream()
                .collect(Collectors.groupingBy(
                        reportImage -> reportImage.getReport().getId(),
                        Collectors.mapping(
                                ReportImage::getId,
                                Collectors.toList()
                        )
                ));
    }
}
