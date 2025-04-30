package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.NoticeComments;
import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.dto.NoticesComentsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.repository.NoticesCommentsRepository;
import com.jobs.luckystage.repository.NoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticesCommentsServiceImpl implements NoticesCommentsService {
    private final NoticesCommentsRepository noticesCommentsRepository;
    private final NoticesRepository noticesRepository;


    @Override
    public Long register(NoticesComentsDTO noticeCommentsDTO, Members members) {
        NoticeComments noticeComments = dtoToEntity(noticeCommentsDTO);
        Notices notices = noticesRepository.findById(noticeCommentsDTO.getNoticeNum()).get();
        noticeComments.setNotices(notices);
        noticeComments.setMembers(members);

        return noticesCommentsRepository.save(noticeComments).getNoticeCommentNum();
    }

    @Override
    public NoticesComentsDTO read(Long noticeCommentNum) {
        NoticeComments noticeComments = noticesCommentsRepository.findById(noticeCommentNum).get();
        NoticesComentsDTO noticesComentsDTO = entityToDto(noticeComments);
        return noticesComentsDTO;
    }

    @Override
    public void modify(NoticesComentsDTO noticesComentsDTO) {
        NoticeComments noticeComments = noticesCommentsRepository.findById(noticesComentsDTO.getNoticeCommentNum()).get();
        noticeComments.setContent(noticesComentsDTO.getContent());
        noticesCommentsRepository.save(noticeComments);
    }

    @Override
    public void remove(Long noticeCommentNum) {
        noticesCommentsRepository.deleteById(noticeCommentNum);
    }

    @Override
    public PageResponseDTO<NoticesComentsDTO> getListOfNotices(Long noticeNum, PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("noticeCommentNum");
//        Pageable pageable = PageRequestDTO.getPageable(pageRequestDTO.getPage(), pageRequestDTO.getSize(), "noticeNum");


        Page<NoticeComments> result=noticesCommentsRepository.listOfNotices(noticeNum, pageable);

        List<NoticesComentsDTO> dtoList=result.getContent().stream()
                .map(noticeComments -> entityToDto(noticeComments))
                .collect(Collectors.toList());
        return PageResponseDTO.<NoticesComentsDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int)result.getTotalElements())
                .build();
    }
}
