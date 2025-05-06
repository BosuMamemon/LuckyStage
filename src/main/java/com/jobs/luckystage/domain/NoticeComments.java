package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class NoticeComments extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeCommentNum;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members members;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="notice_num")
    private Notices notices;
}
