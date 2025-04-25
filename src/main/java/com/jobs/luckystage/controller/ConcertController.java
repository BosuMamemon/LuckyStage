package com.jobs.luckystage.controller;

import com.jobs.luckystage.dto.ConcertDTO;
import com.jobs.luckystage.service.ConcertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/concert")
public class ConcertController {
    private final ConcertService concertService;
    private final String uploadPath = new File("src/main/resources/static/images/concerts").getAbsolutePath();

//    list - get
    @GetMapping("/list")
    public void list(@RequestParam("type") String type, Model model) {
        List<ConcertDTO> dtoList = concertService.list(type);
        model.addAttribute("dtoList", dtoList);
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
    public void getRead(@RequestParam("concertNum") long concertNum, Model model) {
        ConcertDTO dto = concertService.findById(concertNum);
        model.addAttribute("concert", dto);
    }
}
