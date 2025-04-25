package com.jobs.luckystage.dto;

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
public class BoardDTO {
    private Long boardNum;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private int readcount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
}