package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Concerts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long concertNum;

    @ColumnDefault("0")
    private double rating;

    @ColumnDefault("0")
    private int hitcount;

    private String posterFileName;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private String performanceTime;

    @Column(nullable = false)
    private String location;

    private String ageRate;

    @Column(nullable = false)
    private String paymentLink;

    @OneToMany(mappedBy = "concerts", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<ConcertImages> concertImage = new HashSet<>();

    public void updateHitcount() {
        this.hitcount += 1;
    }
}
