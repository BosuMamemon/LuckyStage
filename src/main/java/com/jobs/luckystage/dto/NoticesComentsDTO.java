package com.jobs.luckystage.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.WithBy;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticesComentsDTO {
    private Long noticeCommentNum;
    @NotEmpty
    private String content;
    @NotEmpty
    private String members_username;
    private Long noticeNum;
    private LocalDateTime regDate;




}
