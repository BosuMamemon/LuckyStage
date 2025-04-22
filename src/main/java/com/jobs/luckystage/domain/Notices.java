package com.jobs.luckystage.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Notices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members members;
    private Date regDate;
    @OneToMany(mappedBy = "notices", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NoticeImages> noticeImages;
    @OneToMany(mappedBy = "notices", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NoticeComments> noticeComments;
}
