package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.dto.UploadFileDTO;
import com.jobs.luckystage.service.ConcertService;
import com.jobs.luckystage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j2
public class ReviewController {
    @Value("${com.jobs.upload.path}")
    private String uploadPath;

    private final ReviewService reviewService;
    private final ConcertService concertService;

    private List<String> fileUpload(UploadFileDTO uploadFileDTO) {
        List<String> filenameList = new ArrayList<>();

        uploadFileDTO.getFiles().forEach(multipartFile -> {
            String originalName = multipartFile.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            Path savePath = Paths.get(uploadPath, uuid + "_" + originalName);

            boolean imageFlag = false;
            try {
                multipartFile.transferTo(savePath);
                if(Files.probeContentType(savePath).startsWith("image")) {
                    imageFlag = true;
                    File thumbnail = new File(uploadPath, "thumbnail_" + uuid + "_" + originalName);
                    Thumbnailator.createThumbnail(savePath.toFile(), thumbnail, 200, 200);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            filenameList.add(uuid + "_" + originalName);
        });

        return filenameList;
    }

    @GetMapping("/list")
    public void getList(PageRequestDTO pageRequestDTO, Model model) {
        pageRequestDTO.setSize(8);
        PageResponseDTO<ReviewDTO> reviewList = reviewService.getAllReviews(pageRequestDTO);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        model.addAttribute("reviewList", reviewList);
    }

    @GetMapping("/register")
    public void getRegister(@RequestParam("concertNum") long concertNum, Model model) {
        String title = concertService.findById(concertNum).getTitle();
        model.addAttribute("title", title);
        model.addAttribute("concertNum", concertNum);
    }

    @PostMapping("/register")
    public String postRegister(UploadFileDTO uploadFileDTO, ReviewDTO reviewDTO) {
        List<String> filenameList = null;
        if(uploadFileDTO.getFiles() != null) {
            filenameList = fileUpload(uploadFileDTO);
        }

        reviewDTO.setImageFilenameList(filenameList);
        reviewService.saveReview(reviewDTO);
        return "redirect:/review/list";
    }

    @GetMapping("/read")
    public void getRead() {}

    @GetMapping("/img/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable("filename") String filename) {
        Resource resource = new FileSystemResource(uploadPath + File.separator + filename);
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }
}
