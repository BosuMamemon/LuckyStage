package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Tickets {
    @Id
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members members;
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime regDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime selectedDate;
    @OneToOne(fetch = FetchType.LAZY)
    private Concerts concerts;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lotteryDate;
}
