package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.dto.NoticesDTO;
import com.jobs.luckystage.dto.NoticesPageRequestDTO;
import com.jobs.luckystage.dto.NoticesPageResponseDTO;
import com.jobs.luckystage.repository.NoticesCommentsRepository;
import com.jobs.luckystage.repository.NoticesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoticesServiceImpl implements NoticesService {
//    private final NoticesCommentsRepository noticesCommentsRepository;
    private final NoticesRepository noticesRepository;

    @Override
    public void registerNotices(NoticesDTO noticesDTO) {
        Notices notices = dtoToEntity(noticesDTO);
        noticesRepository.save(notices);
    }

    @Override
    public NoticesDTO readNotices(Long noticeNum) {

//        Notices notices = noticesRepository.findByIdWithImages(noticeNum)
//                .orElse(null);
//        notices.updateReadcount();
//        noticesRepository.save(notices);
//        return entityToDto(notices);
        return null;
    }

    @Override
    public void updateNotices(NoticesDTO noticesDTO) {
        Notices notices = noticesRepository.findById(noticesDTO.getNotice_num()).get();
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
    public void deleteNotices(Long notice_num) {
        noticesRepository.deleteById(notice_num);
    }

    @Override
    public NoticesPageResponseDTO<NoticesDTO> list(NoticesPageRequestDTO noticesPageRequestDTO) {
        Pageable pageable = noticesPageRequestDTO.getPageable("notice_num");
//        Page<Notices> result = noticesRepository.searchAll(
//                noticesPageRequestDTO.getTypes(),
//                noticesPageRequestDTO.getKeyword(),
//                pageable);
//        List<NoticesDTO> dtoList = result.stream()
//                .map(notices -> entityToDto(notices))
//                .collect(Collectors.toList());






        return null;
    }


}