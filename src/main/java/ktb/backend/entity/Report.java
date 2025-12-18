package ktb.backend.entity;

import jakarta.persistence.*;
import ktb.backend.enums.AnimalType;
import ktb.backend.enums.Gender;
import ktb.backend.enums.ReportType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Report extends BaseEntity{
    // -- 공통 정보 --
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    private LocalDateTime caseTime;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(columnDefinition = "TEXT")
    private String additionalInfo;

    // -- 실종 신고 추가 정보 --
    private String email;

    private String phoneNumber;

    private String petName;

    @Enumerated(EnumType.STRING)
    private AnimalType types;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String species;

    @Builder
    public Report(Long id, ReportType reportType, LocalDateTime caseTime, Location location, String additionalInfo, String email, String phoneNumber, String petName, AnimalType types, Gender gender, String species) {
        this.id = id;
        this.reportType = reportType;
        this.caseTime = caseTime;
        this.location = location;
        this.additionalInfo = additionalInfo;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.petName = petName;
        this.types = types;
        this.gender = gender;
        this.species = species;
    }
}
