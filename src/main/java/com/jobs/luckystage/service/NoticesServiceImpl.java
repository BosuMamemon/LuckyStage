package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.dto.*;
import com.jobs.luckystage.repository.MemberRepository;
import com.jobs.luckystage.repository.NoticesCommentsRepository;
import com.jobs.luckystage.repository.NoticesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class NoticesServiceImpl implements NoticesService {
//    private final NoticesCommentsRepository noticesCommentsRepository;
    @Autowired
    private NoticesRepository noticesRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private NoticesCommentsRepository noticesCommentsRepository;

    @Override
    public void registerNotices(NoticesDTO noticesDTO, Members members) {
        Notices notices = dtoToEntity(noticesDTO);
        notices.setMembers(members);
        noticesRepository.save(notices);
    }

    @Override
    public NoticesDTO readNotices(Long noticeNum) {

        Notices notices = noticesRepository.findByIdWithImages(noticeNum)
                .orElse(null);
        notices.updateHitcount();
        noticesRepository.save(notices);
        return entityToDto(notices);

    }

    @Override
    public void updateNotices(NoticesDTO noticesDTO) {
        Notices notices = noticesRepository.findById(noticesDTO.getNoticeNum()).get();
        notices.change(noticesDTO.getTitle(), noticesDTO.getContent());
        notices.clearImages();
        if(noticesDTO.getFileNames() !=null){
            for(String fileName : noticesDTO.getFileNames()){
                String[] arr=fileName.split("_");
                notices.addImage(arr[0], arr[1]);
            }
        }
        noticesRepository.save(notices);
    }

    @Override
    public void deleteNotices(Long noticeNum) {
        noticesCommentsRepository.deleteById(noticeNum);
        noticesRepository.deleteById(noticeNum);
    }



    @Override
    public PageResponseDTO<NoticesDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("noticeNum");
        Page<Notices> result = noticesRepository.searchAll(
                pageRequestDTO.getTypes(),
                pageRequestDTO.getKeyword(),
                pageable);
        if (result == null) {
           // throw new IllegalStateException("검색 결과가 없습니다.");
            return  null;
        }

        List<NoticesDTO> dtoList = result.stream()
                .map(notices -> entityToDto(notices))
                .collect(Collectors.toList());

        return PageResponseDTO.<NoticesDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();
   }
}