package com.jobs.luckystage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConcertDTO {
    private long concertNum;
    private String posterFileName;
    private String title;
    private int hitcount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String performanceTime;
    private String location;
    private String ageRate;
    private String paymentLink;
    private List<String> filenames;
}
