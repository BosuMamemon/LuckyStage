package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.ConcertImages;
import com.jobs.luckystage.domain.Concerts;
import com.jobs.luckystage.domain.MemberConcertBookmark;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.dto.ConcertDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.repository.ConcertImageRepository;
import com.jobs.luckystage.repository.ConcertRepository;
import com.jobs.luckystage.repository.MemberConcertBookmarkRepository;
import com.jobs.luckystage.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ConcertServiceImpl implements ConcertService {
    private final ConcertRepository concertRepository;
    private final ConcertImageRepository concertImageRepository;
    private final MemberRepository memberRepository;
    private final MemberConcertBookmarkRepository memberConcertBookmarkRepository;

    @Override
    public List<ConcertDTO> list(PageRequestDTO pageRequestDTO) {
        List<Concerts> entityList = concertRepository.searchAll(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword()
        );
        List<ConcertDTO> dtoList = entityList.stream().map(entity -> entityToDto(entity)).collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public ConcertDTO findById(long concertNum) {
        Concerts entity = concertRepository.findById(concertNum).orElse(null);
        entity.updateHitcount();
        concertRepository.save(entity);
        ConcertImages concertImageEntity = concertImageRepository.findByConcerts_ConcertNum(concertNum).orElse(null);
        List<String> filenames = List.of(concertImageEntity.getFilename().split("-"));
        ConcertDTO dto = entityToDto(entity);
        dto.setFilenames(filenames);
        return dto;
    }

    @Override
    public void bookmark(long concertNum, String username) {
        Concerts concert = concertRepository.findById(concertNum).orElse(null);
        Members member = memberRepository.findByUsername(username);
        MemberConcertBookmark memberConcertBookmark = MemberConcertBookmark.builder().concerts(concert).members(member).build();
        memberConcertBookmarkRepository.save(memberConcertBookmark);
    }

    @Override
    public List<ConcertDTO> findBookmark(String username) {
        List<MemberConcertBookmark> memberConcertBookmarks = memberConcertBookmarkRepository.findAllByMembers_Username(username);
        List<Concerts> concertsList = new ArrayList<>();
        for(MemberConcertBookmark memberConcertBookmark : memberConcertBookmarks) {
            concertsList.add(memberConcertBookmark.getConcerts());
        }
        return concertsList.stream().map(concerts -> entityToDto(concerts)).collect(Collectors.toList());
    }

    @Override
    public void deleteBookmark(long concertNum, String username) {
        memberConcertBookmarkRepository.deleteByConcerts_ConcertNumAndMembers_Username(concertNum, username);
    }
}
