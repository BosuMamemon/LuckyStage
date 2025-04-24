package com.jobs.luckystage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {
    private Long reviewNum;
    private String title;
    private String content;
    private String username; // members.username
    private LocalDateTime regDate;
    private List<ReviewImageDTO> imageList;
    private List<ReviewCommentDTO> commentList;
}
