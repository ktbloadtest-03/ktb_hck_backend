package ktb.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class LocationMarkerImage {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;
}
