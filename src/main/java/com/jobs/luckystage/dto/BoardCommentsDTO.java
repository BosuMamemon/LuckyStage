package com.jobs.luckystage.dto;

import com.jobs.luckystage.domain.Boards;
import com.jobs.luckystage.domain.Members;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentsDTO {
    private Long boardCommentNum;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private Members members;
    private int readcount;
    private Date regDate;
    private Boards boards;
}
