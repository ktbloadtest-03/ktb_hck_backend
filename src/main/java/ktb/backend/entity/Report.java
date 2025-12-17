package ktb.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;
import ktb.backend.enums.ReportType;
import lombok.Getter;
import org.springframework.data.annotation.Id;

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
