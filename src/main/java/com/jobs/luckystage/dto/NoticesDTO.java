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
@AllArgsConstructor
@NoArgsConstructor
public class NoticesDTO {
    private Long notice_num;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @NotEmpty
    private String members_username;
    private Members members;
    private LocalDateTime regDate;
    private LocalDateTime updateDate;
    private List<String> fileNames;
}
