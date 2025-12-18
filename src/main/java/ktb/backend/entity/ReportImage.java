package ktb.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class ReportImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
