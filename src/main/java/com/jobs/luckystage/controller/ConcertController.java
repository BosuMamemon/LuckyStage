package com.jobs.luckystage.controller;

import com.jobs.luckystage.config.auth.PrincipalDetails;
import com.jobs.luckystage.dto.ConcertDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.service.ConcertService;
import com.jobs.luckystage.service.MemberService;
import com.jobs.luckystage.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/concert")
public class ConcertController {
    private final ConcertService concertService;
    private final ReviewService reviewService;
    private final MemberService memberService;
    private final String uploadPath = new File("src/main/resources/static/images/concerts").getAbsolutePath();

//    list - get
    @GetMapping("/list")
    public void list(@AuthenticationPrincipal PrincipalDetails principalDetails, PageRequestDTO pageRequestDTO, Model model) {
        List<ConcertDTO> dtoList = concertService.list(pageRequestDTO);
        if(principalDetails != null) {
            List<Long> bookmarkList = memberService.getBookmarkConcertNum(principalDetails.getUsername());
            model.addAttribute("bookmarkList", bookmarkList);
        }
        model.addAttribute("dtoList", dtoList);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }

//    static/images/concerts에서 이미지 불러오기
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

    @GetMapping("/read")
    public void getRead(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam("concertNum") long concertNum, Model model) {
        ConcertDTO dto = concertService.findById(concertNum);
        List<ReviewDTO> reviewList = reviewService.getAllReviewsByConcertNum(concertNum);
        if(principalDetails != null) {
            List<Long> bookmarkList = memberService.getBookmarkConcertNum(principalDetails.getUsername());
            model.addAttribute("bookmarkList", bookmarkList);
        }
        model.addAttribute("concert", dto);
        model.addAttribute("reviewList", reviewList);
    }

    @GetMapping("/bookmark")
    @ResponseBody
    public String getBookmark(@RequestParam Map<String, String> params) {
        long concertNum = Long.parseLong(params.get("concertNum"));
        String username = params.get("username");
        concertService.bookmark(concertNum, username);
        return "ok";
    }

    @GetMapping("/deleteBookmark")
    @ResponseBody
    public String getDeleteBookmark(@RequestParam Map<String, String> params) {
        long concertNum = Long.parseLong(params.get("concertNum"));
        String username = params.get("username");
        concertService.deleteBookmark(concertNum, username);
        return "ok";
    }

    @PostMapping("/deleteBookmark")
    public String postDeleteBookmark(String concertNum, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        long concertNumVal = Long.parseLong(concertNum);
        concertService.deleteBookmark(concertNumVal, principalDetails.getUsername());
        return "redirect:/mypage/pick";
    }
}
