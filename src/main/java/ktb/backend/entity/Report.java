package ktb.backend.entity;

import jakarta.persistence.*;
import ktb.backend.enums.AnimalType;
import ktb.backend.enums.Gender;
import ktb.backend.enums.ReportType;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Report extends BaseEntity{
    // -- 공통 정보 --

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private ReportType reportType;

    private LocalDateTime caseTime;

    @OneToOne(mappedBy = "report")
    private Location location;

    @Column(columnDefinition = "TEXT")
    private String additionalInfo;

    // -- 실종 신고 추가 정보 --
    private String email;

    private String phoneNumber;

    private String petName;

    @Enumerated(EnumType.STRING)
    private AnimalType types;

    private Gender gender;

    private String species;
}
