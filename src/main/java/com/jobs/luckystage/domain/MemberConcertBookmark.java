package com.jobs.luckystage.domain;

import jakarta.persistence.*;

@Entity
public class MemberConcertBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_username")
    private Members members;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concerts_concertNum")
    private Concerts concerts;

}
