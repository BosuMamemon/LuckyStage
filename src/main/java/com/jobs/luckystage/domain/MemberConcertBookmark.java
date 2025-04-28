package com.jobs.luckystage.domain;

import jakarta.persistence.*;

@Entity
public class MemberConcertBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members member;
    @ManyToOne(fetch = FetchType.LAZY)
    private Concerts concert;
}
