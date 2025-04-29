package com.jobs.luckystage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private double rating;
    private Date startDate;
    private Date endDate;
    private String performanceTime;
    private String location;
    private String ageRate;
    private String paymentLink;
    private List<String> filenames;

    public String getReviewStars() {
        String reviewStars = "";
        for(int i = 0; i < this.rating; i++) {
            reviewStars += "★";
        }
        return reviewStars;
    }
}
