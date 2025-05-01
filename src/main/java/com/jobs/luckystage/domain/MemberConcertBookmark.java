package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"members_username", "concerts_concertNum"})})
public class MemberConcertBookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_username")
    private Members members;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concerts_concertNum")
    private Concerts concerts;

}
