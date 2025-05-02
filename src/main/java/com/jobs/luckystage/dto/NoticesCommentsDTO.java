package com.jobs.luckystage.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticesCommentsDTO {
    private Long noticeCommentNum;
    @NotEmpty
    private String content;
    @NotEmpty
    private String members_username;
    private String nickname;
    private Long noticeNum;
    private LocalDateTime regDate;
}
