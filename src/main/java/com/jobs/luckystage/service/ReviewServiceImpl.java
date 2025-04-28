package com.jobs.luckystage.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.jobs.luckystage.domain.Reviews;
import com.jobs.luckystage.dto.ReviewCommentDTO;
import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.dto.ReviewImageDTO;
import com.jobs.luckystage.repository.MemberRepository;
import com.jobs.luckystage.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream().map(reviews -> entityToDto(reviews)).collect(Collectors.toList());
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
        reviewRepository.save(review);
    }

    @Override
    public ReviewDTO getReview(Long reviewNum) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewNum) {}

    @Override
    public void addComment(ReviewCommentDTO commentDTO) {}

    @Override
    public void deleteComment(Long commentId) {}
}
