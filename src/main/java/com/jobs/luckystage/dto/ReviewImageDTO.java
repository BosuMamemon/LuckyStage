package com.jobs.luckystage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ReviewImageDTO.java
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewImageDTO {
    private String uuid;
    private String filename;
    private int ord;
}

