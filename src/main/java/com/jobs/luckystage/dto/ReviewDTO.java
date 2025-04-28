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
    private int hitcount;
    private double rating;
    private String username; // members.username
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private List<ReviewImageDTO> imageList;
    private List<ReviewCommentDTO> commentList;

    public void updateHitcount() {
        this.hitcount += 1;
    }
}

