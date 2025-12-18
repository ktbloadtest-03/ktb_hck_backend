package ktb.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ReportImage {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    public ReportImage(Long id, Report report) {
        this.id = id;
        this.report = report;
    }
}
