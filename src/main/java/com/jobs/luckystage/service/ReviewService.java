package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Reviews;
import com.jobs.luckystage.dto.PageRequestDTO;
import com.jobs.luckystage.dto.PageResponseDTO;
import com.jobs.luckystage.dto.ReviewCommentDTO;
import com.jobs.luckystage.dto.ReviewDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface ReviewService {
    void saveReview(ReviewDTO reviewDTO);
    PageResponseDTO<ReviewDTO> getAllReviews(PageRequestDTO pageRequestDTO);
    PageResponseDTO<ReviewDTO> getAllReviewsByUsername(PageRequestDTO pageRequestDTO, String username);
    List<ReviewDTO> getAllReviewsByConcertNum(long concertNum);
    ReviewDTO getReview(Long reviewNum);
    void deleteReview(Long reviewNum);
    void addComment(ReviewCommentDTO commentDTO);
    void deleteComment(Long commentId);

    default ReviewDTO entityToDto(Reviews entity) {
        ReviewDTO dto = ReviewDTO.builder()
                .regDate(entity.getRegDate())
                .rating(entity.getRating())
                .content(entity.getContent())
                .title(entity.getTitle())
                .modDate(entity.getModDate())
                .reviewNum(entity.getReviewNum())
//                .concertNum()
//                .imageList()
//                .username()
                .build();
        List<String> imageFilenameList =
                entity.getReviewImages()
                        .stream().sorted()
                        .map(reviewImage -> reviewImage.getUuid() + "_" + reviewImage.getFilename())
                        .collect(Collectors.toList());
        dto.setImageFilenameList(imageFilenameList);
        return dto;
    }

    default Reviews dtoToEntity(ReviewDTO dto) {
        Reviews entity = Reviews.builder()
                .rating(dto.getRating())
                .title(dto.getTitle())
                .content(dto.getContent())
//                .concerts()
//                .members()
                .build();

        if(dto.getImageFilenameList() != null) {
            dto.getImageFilenameList().forEach(filename -> {
                String[] arr = filename.split("_");
                entity.addImage(arr[0], arr[1]);
            });
        }

        return entity;
    }
}