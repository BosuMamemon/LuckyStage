package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.Reviews;
import com.jobs.luckystage.repository.MemberRepository;
import com.jobs.luckystage.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    // 회원 탈퇴 (리뷰 작성자는 null로 유지)
    public void deleteMember(String username) {
        // 1. 해당 회원이 쓴 리뷰들 가져오기
        List<Reviews> reviews = reviewRepository.findAllByMembersUsername(username);

        // 2. 각 리뷰의 members를 null로 설정
        for (Reviews review : reviews) {
            review.setMembers(null);
        }

        // 3. 수정된 리뷰 저장
        reviewRepository.saveAll(reviews);

        // 4. 회원 삭제
        Members member = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("회원 없음"));
        memberRepository.delete(member);
    }
}
