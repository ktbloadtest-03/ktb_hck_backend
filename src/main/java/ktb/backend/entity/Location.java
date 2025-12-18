package ktb.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Location {
    @Id
    private Long id;

    @Column(
            name = "coordinate",
            columnDefinition = "POINT",
            nullable = false
    )
    private Object coordinate;

    @Column(columnDefinition = "TEXT")
    private String detail;
}
