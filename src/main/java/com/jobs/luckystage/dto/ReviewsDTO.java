package com.jobs.luckystage.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ReviewsDTO {
    private Long reviewNum;
    private String title;
    private String content;
    private Long memberId; //member와 연관
    private Date regDate;
}
