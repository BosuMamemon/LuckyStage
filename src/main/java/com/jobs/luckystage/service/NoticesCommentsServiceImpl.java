package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.NoticeComments;
import com.jobs.luckystage.dto.NoticesCommentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.repository.NoticesCommentsRepository;
import com.jobs.luckystage.repository.NoticesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class NoticesCommentsServiceImpl implements NoticesCommentsService {
    private final NoticesCommentsRepository noticesCommentsRepository;
    private final NoticesRepository noticesRepository;


    @Override
    public Long register(NoticesCommentsDTO noticeCommentsDTO, Members members) {
        log.info("service / noticeCommentsDTO: " + noticeCommentsDTO);
        NoticeComments noticeComments = dtoToEntity(noticeCommentsDTO);
        noticeComments.setNotices(noticesRepository.findById(noticeCommentsDTO.getNoticeNum()).orElse(null));
        noticeComments.setMembers(members);
        NoticeComments savedComments = noticesCommentsRepository.save(noticeComments);

        return savedComments.getNoticeCommentNum();
    }

    @Override
    public NoticesCommentsDTO read(Long noticeCommentNum) {
        NoticeComments noticeComments = noticesCommentsRepository.findById(noticeCommentNum).get();
        NoticesCommentsDTO noticesCommentsDTO = entityToDto(noticeComments);
        return noticesCommentsDTO;
    }

    @Override
    public void modify(NoticesCommentsDTO noticesCommentsDTO) {
        NoticeComments noticeComments = noticesCommentsRepository.findById(noticesCommentsDTO.getNoticeCommentNum()).get();
        noticeComments.setContent(noticesCommentsDTO.getContent());
        noticesCommentsRepository.save(noticeComments);
    }

    @Override
    public void remove(Long noticeCommentNum) {
        noticesCommentsRepository.deleteById(noticeCommentNum);
    }

    @Override
    public PageResponseDTO<NoticesCommentsDTO> getListOfNotices(Long noticeNum, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("noticeCommentNum");
        Page<NoticeComments> result=noticesCommentsRepository.listOfNotices(noticeNum, pageable);
        List<NoticesCommentsDTO> dtoList = result.getContent().stream()
                .map(noticeComments -> entityToDto(noticeComments))
                .collect(Collectors.toList());
        return PageResponseDTO.<NoticesCommentsDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
