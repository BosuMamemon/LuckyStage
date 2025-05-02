package com.jobs.luckystage.service;

import com.jobs.luckystage.domain.MemberConcertBookmark;
import com.jobs.luckystage.domain.Members;
import com.jobs.luckystage.domain.Reviews;
import com.jobs.luckystage.repository.MemberConcertBookmarkRepository;
import com.jobs.luckystage.repository.MemberRepository;
import com.jobs.luckystage.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final MemberConcertBookmarkRepository memberConcertBookmarkRepository;

    // 회원 탈퇴 (리뷰 작성자는 null로 유지)
    public void deleteMember(String username) {
        // 1. 해당 회원이 쓴 리뷰들 가져오기
        List<Reviews> reviews = reviewRepository.findAllByMembersUsername(username);

        // 2. 각 리뷰의 members를 null로 설정
        for (Reviews review : reviews) {
            review.setMembers(null);
        }

//        2-1. 북마크 삭제
        memberConcertBookmarkRepository.deleteAllByMembers_Username(username);

        // 3. 수정된 리뷰 저장
        reviewRepository.saveAll(reviews);

        // 4. 회원 삭제
        Members member = memberRepository.findById(username)
                .orElseThrow(() -> new RuntimeException("회원 없음"));
        memberRepository.delete(member);
    }

    public List<Long> getBookmarkConcertNum(String username) {
        List<Long> concertNums = new ArrayList<>();
        List<MemberConcertBookmark> bookmarks = memberConcertBookmarkRepository.findAllByMembers_Username(username);
        for(MemberConcertBookmark bookmark : bookmarks) {
            concertNums.add(bookmark.getConcerts().getConcertNum());
        }
        return concertNums;
    }

    public Page<Members> getAllMembers(Pageable pageable) {
        return memberRepository.findAll(pageable);
    }
    public void deleteMemberByUsername(String username) {
        memberRepository.deleteById(username);
    }
    public Members findByUsername(String username) {
        return memberRepository.findById(username).orElseThrow(() -> new RuntimeException("회원 없음"));
    }
}
