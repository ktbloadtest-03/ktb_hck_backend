package ktb.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class AiGeneratedImage {
    @Id
    private Long id;

    @OneToOne
    @JoinColumn(name = "information_id")
    private Report report;
}
