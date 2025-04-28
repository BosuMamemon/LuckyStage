package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.FAQs;
import com.jobs.luckystage.dto.FAQsDTO;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;

public interface FAQsService {
    void registerFAQs(FAQsDTO faqsDTO);
    FAQsDTO readFAQs(Long faqNum);
    void deleteFAQs(Long faqNum);
    void updateFAQs(FAQsDTO faqsDTO);
    PageResponseDTO<FAQsDTO> list(PageRequestDTO pageRequestDTO);

    default FAQs dtoToEntity(FAQsDTO dto) {
        FAQs faqsEntity = FAQs.builder()
                .faqNum(dto.getFaqNum())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        return faqsEntity;
    }
    default FAQsDTO entityToDto(FAQs faqs) {
        FAQsDTO faqsDTO = FAQsDTO.builder()
                .faqNum(faqs.getFaqNum())
                .title(faqs.getTitle())
                .content(faqs.getContent())
                .regDate(faqs.getRegDate())
                .readcount(faqs.getReadcount())
                .build();
        return faqsDTO;
    }
}
