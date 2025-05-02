package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeComments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeCommentNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members members;
    private LocalDateTime regDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_num")
    private Notices notices;
}
