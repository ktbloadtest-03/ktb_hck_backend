package ktb.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "coordinate",
            columnDefinition = "POINT",
            nullable = false
    )
    private Object coordinate;

    @Column(columnDefinition = "TEXT")
    private String detail;

    @OneToOne
    @JoinColumn(name = "information_id")
    private Information information;
}
