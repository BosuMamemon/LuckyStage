package com.jobs.luckystage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewCommentDTO {
    private Long reviewCommentNum;
    private Long reviewNum;
    private String content;
    private String username;
    private LocalDateTime regDate;
}

