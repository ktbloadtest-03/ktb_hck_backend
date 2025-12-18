package ktb.backend.service;

import ktb.backend.entity.ReportImage;
import ktb.backend.repository.ReportImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportImageService {
    private ReportImageRepository reportImageRepository;

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
