package ktb.backend.entity;

import jakarta.persistence.*;
import ktb.backend.enums.ReportType;
import lombok.Getter;

@Entity
@Getter
public class Report extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private ReportType reportType;

    private String email;

    @OneToOne(mappedBy = "report")
    private Information information;
}
