package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Boards extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long boardNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne
    private Members members;
    @OneToMany(mappedBy = "boards", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardComments> boardComments;
    @OneToMany(mappedBy = "boards", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardImages> boardImages;
    private int readcount;

    public void updateReadcount() {readcount = readcount + 1; }

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
