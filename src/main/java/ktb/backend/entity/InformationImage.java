package ktb.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class InformationImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "information_id")
    private Information information;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
