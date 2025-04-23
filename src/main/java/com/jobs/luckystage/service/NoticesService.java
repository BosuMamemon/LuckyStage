package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.dto.NoticesDTO;
import com.jobs.luckystage.dto.NoticesPageRequestDTO;
import com.jobs.luckystage.dto.NoticesPageResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface NoticesService {
    void registerNotices(NoticesDTO noticesDTO);
    NoticesDTO readNotices(Long notice_num);
    void updateNotices(NoticesDTO noticesDTO);
    void deleteNotices(Long notice_num);
    NoticesPageResponseDTO<NoticesDTO> list(NoticesPageRequestDTO noticesPageRequestDTO);

    default Notices dtoToEntity(NoticesDTO dto) {
        Notices noticesEntity = Notices.builder()
                .noticeNum(dto.getNotice_num())
                .title(dto.getTitle())
                .content(dto.getContent())
                .members(dto.getMembers())
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
                .notice_num(noticesEntity.getNoticeNum())
                .title(noticesEntity.getTitle())
                .content(noticesEntity.getContent())
                .members(noticesEntity.getMembers())
//                .regDate(noticesEntity.getRegDate())
                .build();
        List<String> fileNames =
                noticesEntity.getImageSet().stream().sorted().map(noticeImage ->
                                noticeImages.getUuid()+"_"+noticeImages.getFileName())
                        .collect(Collectors.toList());
        noticesDTO.setFileNames(fileNames);
        return noticesDTO;
    }
}
