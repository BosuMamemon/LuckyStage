package com.jobs.luckystage.dto;

import com.jobs.luckystage.domain.Concerts;
import com.jobs.luckystage.domain.Members;

import java.time.LocalDateTime;
import java.util.Date;

public class TicketDTO {
    private long id;
    private Members members;
    private Concerts concerts;
    private LocalDateTime regDate;
    private LocalDateTime selectedDate;
    private Date lotteryDate;
}
