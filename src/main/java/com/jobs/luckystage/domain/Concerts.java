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
    @OneToMany(mappedBy = "concerts", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConcertContents> concertContent;
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
    private Set<ConcertPosters> concertPoster;
}
