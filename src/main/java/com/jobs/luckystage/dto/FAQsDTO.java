package com.jobs.luckystage.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FAQsDTO {
    private Long faqNum;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    private int readcount;
    private LocalDateTime regDate;
}
