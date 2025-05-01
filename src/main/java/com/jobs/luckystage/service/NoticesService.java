package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.dto.*;

import java.util.List;
import java.util.stream.Collectors;


public interface NoticesService {
    void registerNotices(NoticesDTO noticesDTO, Members members);
    NoticesDTO readNotices(Long noticeNum);
    void updateNotices(NoticesDTO noticesDTO);
    void deleteNotices(Long noticeNum);
    PageResponseDTO<NoticesDTO> list(PageRequestDTO pageRequestDTO);

    default Notices dtoToEntity(NoticesDTO dto) {
        Notices noticesEntity = Notices.builder()
                .noticeNum(dto.getNoticeNum() !=null ? dto.getNoticeNum() :0l)
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        if(dto.getFileNames()!= null){
            dto.getFileNames().forEach(fileName -> {
                String[] arr = fileName.split("_");
                noticesEntity.addImage(arr[0], arr[1]);
            });
        }
        return noticesEntity;
    }
    default NoticesDTO entityToDto(Notices noticesEntity ){
        NoticesDTO noticesDTO = NoticesDTO.builder()
                .noticeNum(noticesEntity.getNoticeNum())
                .title(noticesEntity.getTitle())
                .content(noticesEntity.getContent())
                .regDate(noticesEntity.getRegDate())
                .hitcount(noticesEntity.getHitcount())
                .members_username(noticesEntity.getMembers().getUsername())
                .build();
        List<String> fileNames =
                noticesEntity.getImagesSet().stream().sorted().map(noticeImage ->
                        noticeImage.getUuid()+"_"+noticeImage.getFilename())
                        .collect(Collectors.toList());
        noticesDTO.setFileNames(fileNames);
        return noticesDTO;
    }
}
