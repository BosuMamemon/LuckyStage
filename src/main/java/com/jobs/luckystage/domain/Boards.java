package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Set;

@Getter
@Setter
@ToString
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
