package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.dto.UploadFileDTO;
import com.jobs.luckystage.service.ConcertService;
import com.jobs.luckystage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
public class ReviewController {
    @Value("${com.jobs.upload.path}")
    private String uploadPath;

    private final ReviewService reviewService;
    private final ConcertService concertService;

    @GetMapping("/list")
    public void getList(Model model) {
        List<ReviewDTO> reviewList = reviewService.getAllReviews();
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
        return "redirect:/review/read";
    }

    @GetMapping("/read")
    public void getRead() {}

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
}
