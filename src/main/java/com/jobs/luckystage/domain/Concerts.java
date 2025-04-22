package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
public class Concerts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long concertNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String performanceTime;
    @Column(nullable = false)
    private String location;
    @OneToMany(mappedBy = "concerts", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConcertImages> concertImage;
}
