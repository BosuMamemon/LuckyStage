package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Concerts;
import com.jobs.luckystage.dto.ConcertDTO;

import java.util.List;

public interface ConcertService {
    List<ConcertDTO> list();
    ConcertDTO findById(long concertNum);

    default ConcertDTO entityToDto(Concerts entity) {
        ConcertDTO dto = ConcertDTO.builder()
//                .concertImage(entity.getConcertImage())
                .posterFileName(entity.getPosterFileName())
                .title(entity.getTitle())
                .concertNum(entity.getConcertNum())
                .startDate(entity.getStartDate())
                .ageRate(entity.getAgeRate())
                .paymentLink(entity.getPaymentLink())
                .performanceTime(entity.getPerformanceTime())
                .endDate(entity.getEndDate())
                .location(entity.getLocation())
                .build();
        return dto;
    }

    default Concerts dtoToEntity(ConcertDTO dto) {
        Concerts entity = Concerts.builder()
//                .concertImage(dto.getConcertImage())
                .posterFileName(dto.getPosterFileName())
                .ageRate(dto.getAgeRate())
                .concertNum(dto.getConcertNum())
                .endDate(dto.getEndDate())
                .location(dto.getLocation())
                .paymentLink(dto.getPaymentLink())
                .performanceTime(dto.getPerformanceTime())
                .startDate(dto.getStartDate())
                .title(dto.getTitle())
                .build();
        return entity;
    }
}
