package ktb.backend.entity;

import jakarta.persistence.*;
import ktb.backend.enums.AnimalType;
import ktb.backend.enums.Gender;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Information extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private LocalDateTime caseTime;

    @Column(columnDefinition = "TEXT")
    private String additionalInfo;

    private AnimalType type;

    private String species;

    private int age;

    private Gender gender;

    @OneToOne
    @JoinColumn(name = "report_id")
    private Report report;

    @OneToOne(mappedBy = "information")
    private Location location;
}
