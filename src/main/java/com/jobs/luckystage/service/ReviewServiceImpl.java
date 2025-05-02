package com.jobs.luckystage.service;

import java.util.List;
import java.util.stream.Collectors;

import com.jobs.luckystage.domain.Concerts;
import com.jobs.luckystage.domain.Reviews;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.dto.ReviewCommentDTO;
import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.repository.ConcertRepository;
import com.jobs.luckystage.repository.MemberRepository;
import com.jobs.luckystage.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ConcertRepository concertRepository;

    @Override
    public PageResponseDTO<ReviewDTO> getAllReviews(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("reviewNum");
        Page<Reviews> searchList = reviewRepository.searchAll(pageRequestDTO.getType(), pageRequestDTO.getKeyword(), pageable);
        List<ReviewDTO> dtoList = searchList.stream().map(reviews -> {
            ReviewDTO dto = entityToDto(reviews);
            dto.setConcertTitle(reviews.getConcerts().getTitle());
            dto.setUsername(reviews.getMembers() != null ? reviews.getMembers().getUsername() : "Deleted User");
            dto.setNickname(reviews.getMembers() != null ? reviews.getMembers().getNickname() : "Deleted User");
            dto.setConcertFilename(reviews.getConcerts().getPosterFileName());
            log.info(dto);
            return dto;
        }).collect(Collectors.toList());

        return PageResponseDTO.<ReviewDTO>withAll().dtoList(dtoList).pageRequestDTO(pageRequestDTO).total((int)searchList.getTotalElements()).build();
    }

    @Override
    public List<ReviewDTO> getAllReviewsByConcertNum(long concertNum) {
        return reviewRepository.findAllByConcerts_ConcertNum(concertNum).stream().map(reviews -> {
            ReviewDTO dto = entityToDto(reviews);
            dto.setConcertTitle(reviews.getConcerts().getTitle());
            dto.setUsername(reviews.getMembers() != null ? reviews.getMembers().getUsername() : "Deleted User");
            dto.setNickname(reviews.getMembers() != null ? reviews.getMembers().getNickname() : "Deleted User");
            dto.setConcertFilename(reviews.getConcerts().getPosterFileName());
            return dto;
        }).collect(Collectors.toList());
    }

    private Object getField(Object obj, String fieldName) {
        try {
            java.lang.reflect.Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            throw new RuntimeException("필드 접근 실패: " + fieldName, e);
        }
    }

    @Override
    public void saveReview(ReviewDTO reviewDTO) {
        Reviews review = dtoToEntity(reviewDTO);
        review.setMembers(memberRepository.findByUsername(reviewDTO.getUsername()));
        Concerts concert = concertRepository.findById(reviewDTO.getConcertNum()).get();
        review.setConcerts(concert);
        reviewRepository.save(review);

        concert.setRating(reviewRepository.avgRatingByConcerts_concertNum(reviewDTO.getConcertNum()));
        concertRepository.save(concert);
    }

    @Override
    public void deleteReview(Long reviewNum) {
        reviewRepository.deleteById(reviewNum);
    }

    @Override
    public ReviewDTO getReview(Long reviewNum) {
        return null;
    }

    @Override
    public void addComment(ReviewCommentDTO commentDTO) {}

    @Override
    public void deleteComment(Long commentId) {}
}
