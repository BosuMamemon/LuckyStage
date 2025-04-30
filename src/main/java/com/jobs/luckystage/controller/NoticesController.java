package com.jobs.luckystage.controller;




import com.jobs.luckystage.config.auth.PrincipalDetails;
import com.jobs.luckystage.dto.*;
import com.jobs.luckystage.service.NoticesService;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
@Log4j2
@RequestMapping("/notices")
public class NoticesController {

    @Value("${com.jobs.upload.path}")
    private String uploadPath;

    @Autowired
    private NoticesService noticesService;

    @GetMapping("/list")
    public void listNotices(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<NoticesDTO> responseDTO = noticesService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        model.addAttribute("pageRequest", pageRequestDTO);
    }
    @GetMapping("/register")
    public void register(){
    }
    @PostMapping("/register")
    public String register(UploadFileDTO uploadFileDTO, NoticesDTO noticesDTO, @AuthenticationPrincipal PrincipalDetails principalDetails){
        List<String> strFileNames=null;
        if(uploadFileDTO.getFiles()!=null &&
                ! uploadFileDTO.getFiles().get(0).getOriginalFilename().equals("")){
            strFileNames=fileUpload(uploadFileDTO);
            log.info("!!!"+strFileNames.size());
        }
        noticesDTO.setFileNames(strFileNames);
        noticesService.registerNotices(noticesDTO, principalDetails.getMember());
        return "redirect:/notices/list";
    }
    @GetMapping({"/read","/modify"})
    public void read_modify(PageRequestDTO pageRequestDTO, Long notice_num, Model model){

        NoticesDTO noticesDTO=noticesService.readNotices(notice_num);
        model.addAttribute("notices",  noticesDTO);
    }
    @PostMapping("/modify")
    public String modify(UploadFileDTO uploadFileDTO, NoticesDTO noticesDTO, Model model) {

        List<String> strFileNames = null;
        if (uploadFileDTO.getFiles()!=null &&
                !uploadFileDTO.getFiles().get(0).getOriginalFilename().equals("")) {

            List<String> fileNames = noticesDTO.getFileNames();

            if (fileNames != null && fileNames.size() > 0) {
                removeFile(fileNames);
            }

            strFileNames = fileUpload(uploadFileDTO);
            log.info("!!!!!!" + strFileNames.size());
            noticesDTO.setFileNames(strFileNames);
        }
        noticesService.updateNotices(noticesDTO);
        return "redirect:/notices/noticesList" + noticesDTO.getNoticeNum();
    }




    @GetMapping("/remove")
    public String remove(Long notice_num){
        noticesService.deleteNotices(notice_num);
        return "redirect:/notices/noticesList";
    }
    private List<String> fileUpload(UploadFileDTO uploadFileDTO){

        List<String> list = new ArrayList<>();
        uploadFileDTO.getFiles().forEach(multipartFile -> {
            String originalName = multipartFile.getOriginalFilename();
            log.info(originalName);

            String uuid= UUID.randomUUID().toString();
            Path savePath = Paths.get(uploadPath + uuid+"_"+ originalName);
            boolean image = false;
            try{
                multipartFile.transferTo(savePath);
                if(Files.probeContentType(savePath).startsWith("image")){
                    image=true;
                    File thumbFile=new File(uploadPath,"s_" + uuid+"_"+ originalName);
                    Thumbnailator.createThumbnail(savePath.toFile(), thumbFile,200,200);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            list.add(uuid+"_"+originalName);
        });
        return list;
    }

    @PostMapping("/remove")
    public String remove(NoticesDTO noticesDTO, RedirectAttributes redirectAttributes){
        log.info("remove post.. " + noticesDTO);

        List<String> fileNames=noticesDTO.getFileNames();
        if(fileNames!=null && fileNames.size()>0){
            log.info("remove controller" + fileNames.size());
            removeFile(fileNames);
        }
        noticesService.deleteNotices(noticesDTO.getNoticeNum());

        redirectAttributes.addFlashAttribute("result","removed");
        return "redirect:/notices/noticesList";
    }

    @GetMapping("/view/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> viewFileGet(@PathVariable("fileName") String fileName){
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath() ));
            } catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    private void removeFile(List<String> fileNames){
        log.info("AAAAAAAAAAAAA"+fileNames.size());

        for(String fileName : fileNames){
            log.info("fileRemove method: "+fileName);
            Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);
            String resourceName = resource.getFilename();

            boolean removed = false;

            try{
                String contentType = Files.probeContentType(resource.getFile().toPath());
                removed = resource.getFile().delete();

                //썸네일 ㅇㅇ
                if(contentType.startsWith("image")){
                    String fileName1=fileName.replace("s_","");
                    File originalFile = new File(uploadPath + File.separator + fileName1);
                    originalFile.delete();
                }

            }catch (Exception e){
                log.error(e.getMessage());
            }
        }

    }


























}