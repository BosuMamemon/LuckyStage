package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //이거 디비에 공연정보가 아닌 회원정보가 쳐 넘어가길래 일단은 해놓았는데 삭제해도됨
    private long id;
    @Setter
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
