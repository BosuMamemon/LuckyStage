package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.FAQs;
import com.jobs.luckystage.dto.FAQsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.repository.FAQsRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Log4j2
public class FAQsServiceImpl implements FAQsService {
    @Autowired
    private FAQsRepository faqsRepository;

    @Override
    public void registerFAQs(FAQsDTO faqsDTO) {
        FAQs faqsEntity = dtoToEntity(faqsDTO);
        faqsRepository.save(faqsEntity);
    }

    @Override
    public FAQsDTO readFAQs(Long faqNum) {
        log.info("11111111111111111111111111111read FAQs by faqNum:{}", faqNum);
        FAQs faqs =  faqsRepository.findById(faqNum).get();
        faqs.changeReadcount();
        faqsRepository.save(faqs);
        FAQsDTO faqsDTO = entityToDto(faqs);
        return faqsDTO;
    }

    @Override
    public void deleteFAQs(Long faqNum) {
        faqsRepository.deleteById(faqNum);
    }

    @Override


    public void updateFAQs(FAQsDTO faqsDTO) {
        log.info("1eeeeeeeeeeeeeeeeeeeeeeeeeeee", faqsDTO);

        FAQs faqs = faqsRepository.findById(faqsDTO.getFaqNum()).get();
        faqs.change(faqsDTO.getTitle(),faqsDTO.getContent());
        faqsRepository.save(faqs);
    }


    @Override
    public PageResponseDTO<FAQsDTO> list(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("faqNum");
        Page<FAQs> result = faqsRepository.searchAll(
                pageRequestDTO.getTypes(),
                pageRequestDTO.getKeyword(),
                pageable
        );
        List<FAQsDTO> dtoList=result.getContent().stream()
                .map(faqs -> entityToDto(faqs))
                .collect(Collectors.toList());

        return PageResponseDTO.<FAQsDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements())
                .build();

     }
}
