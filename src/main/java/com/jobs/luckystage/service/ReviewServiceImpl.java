package com.jobs.luckystage.service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.jobs.luckystage.dto.ReviewCommentDTO;
import com.jobs.luckystage.dto.ReviewDTO;
import com.jobs.luckystage.dto.ReviewImageDTO;
import com.jobs.luckystage.repository.ReviewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(review -> {
                    ReviewDTO.ReviewDTOBuilder builder = ReviewDTO.builder();

                    builder.reviewNum((Long) getField(review, "reviewNum"));
                    builder.title((String) getField(review, "title"));
                    builder.content((String) getField(review, "content"));

                    Object members = getField(review, "members");
                    String username = (String) getField(members, "username");
                    builder.username(username);

                    Date regDate = (Date) getField(review, "regDate");
                    builder.regDate(regDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime());

                    // imageList 연결
                    Set<?> images = (Set<?>) getField(review, "reviewImages");
                    List<ReviewImageDTO> imageList = images.stream()
                            .map(img -> ReviewImageDTO.builder()
                                    .uuid((String) getField(img, "uuid"))
                                    .filename((String) getField(img, "filename"))
                                    .ord((Integer) getField(img, "ord"))
                                    .build())
                            .collect(Collectors.toList());
                    builder.imageList(imageList);

                    // commentList 연결
                    Set<?> comments = (Set<?>) getField(review, "reviewComments");
                    List<ReviewCommentDTO> commentList = comments.stream()
                            .map(c -> ReviewCommentDTO.builder()
                                    .reviewCommentNum((Long) getField(c, "reviewCommentNum"))
                                    .reviewNum((Long) getField(getField(c, "reviews"), "reviewNum"))
                                    .content((String) getField(c, "content"))
                                    .username((String) getField(getField(c, "members"), "username"))
                                    .regDate(((Date) getField(c, "regDate"))
                                            .toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime())
                                    .build())
                            .collect(Collectors.toList());
                    builder.commentList(commentList);

                    return builder.build();
                })
                .collect(Collectors.toList());
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
    public void saveReview(ReviewDTO reviewDTO) {}

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
