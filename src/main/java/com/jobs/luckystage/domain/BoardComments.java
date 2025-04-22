package com.jobs.luckystage.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class BoardComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardCommentNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members members;
    private Date regDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Boards boards;
}
