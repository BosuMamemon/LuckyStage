package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.ConcertImages;
import com.jobs.luckystage.domain.Concerts;
import com.jobs.luckystage.dto.ConcertDTO;
import com.jobs.luckystage.repository.ConcertImageRepository;
import com.jobs.luckystage.repository.ConcertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository concertRepository;
    private final ConcertImageRepository concertImageRepository;

    @Override
    public List<ConcertDTO> list() {
        List<Concerts> entityList = concertRepository.findAll();

        List<ConcertDTO> dtoList = entityList.stream().map(entity -> {
            return entityToDto(entity);
        }).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public ConcertDTO findById(long concertNum) {
        Concerts entity = concertRepository.findById(concertNum).orElse(null);
        ConcertImages concertImageEntity = concertImageRepository.findByConcerts_ConcertNum(concertNum).orElse(null);
        List<String> filenames = List.of(concertImageEntity.getFilename().split("-"));
        ConcertDTO dto = entityToDto(entity);
        dto.setFilenames(filenames);
        return dto;
    }
}
