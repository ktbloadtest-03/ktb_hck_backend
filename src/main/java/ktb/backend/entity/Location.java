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
            name = "location",
            columnDefinition = "POINT",
            nullable = false
    )
    private Object location;

    @Column(columnDefinition = "TEXT")
    private String detail;
}
