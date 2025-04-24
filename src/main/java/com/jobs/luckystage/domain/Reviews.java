package com.jobs.luckystage.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members members;
    @OneToMany(mappedBy = "reviews", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewComments> reviewComments;
    private Date regDate;
    private double rating;
    @OneToMany(mappedBy = "reviews", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewImages> reviewImages;
}
