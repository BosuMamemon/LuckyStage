package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Entity
public class Boards extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ColumnDefault("0")
    private int hitcount;
    @ManyToOne
    private Members members;
    @OneToMany(mappedBy = "boards", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardComments> boardComments;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date regDate;
    @OneToMany(mappedBy = "boards", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardImages> boardImages;
}
