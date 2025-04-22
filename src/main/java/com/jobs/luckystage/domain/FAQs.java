package com.jobs.luckystage.domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class FAQs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long faqNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private Date regDate;
}
