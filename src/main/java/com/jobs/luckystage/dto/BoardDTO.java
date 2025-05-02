package com.jobs.luckystage.dto;

import com.jobs.luckystage.domain.Members;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private String members;
    private String nickname;
    private int readcount;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    private List<String> filename;
}