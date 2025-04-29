package com.jobs.luckystage.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long reviewNum;
    private String title;
    private String content;
    private double rating;
    private String username; // members.username
    private long concertNum;
    private String concertTitle;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private List<String> imageFilenameList;
    private List<ReviewCommentDTO> commentList;

    public String getReviewStars() {
        String reviewStars = "";
        for(int i = 0; i < this.rating; i++) {
            reviewStars += "★";
        }
        return reviewStars;
    }
}

