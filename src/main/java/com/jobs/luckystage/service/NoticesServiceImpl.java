package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Notices;
import com.jobs.luckystage.dto.NoticesDTO;
import com.jobs.luckystage.dto.NoticesPageRequestDTO;
import com.jobs.luckystage.dto.NoticesPageResponseDTO;
import com.jobs.luckystage.repository.NoticesCommentsRepository;
import com.jobs.luckystage.repository.NoticesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoticesServiceImpl implements NoticesService {
//    private final NoticesCommentsRepository noticesCommentsRepository;
    private final NoticesRepository noticesRepository;

    @Override
    public void registerNotices(NoticesDTO noticesDTO) {

    }

    @Override
    public NoticesDTO readNotices(Long notice_num) {
        return null;
    }

    @Override
    public void updateNotices(NoticesDTO noticesDTO) {

    }

    @Override
    public void deleteNotices(Long notice_num) {

    }

    @Override
    public NoticesPageResponseDTO<NoticesDTO> list(NoticesPageRequestDTO noticesPageRequestDTO) {
        return null;
    }


}