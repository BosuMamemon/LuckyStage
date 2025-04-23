package com.jobs.luckystage.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class NoticesUploadFileDTO {
    private List<MultipartFile> files;
}
