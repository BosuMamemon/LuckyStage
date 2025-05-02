package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString(exclude = {"boardImages"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
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
    private int readcount;
    @OneToMany(mappedBy = "boards", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BoardComments> boardComments;
    @OneToMany(mappedBy = "boards", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @BatchSize(size=20)
    private Set<BoardImages> boardImages=new HashSet<>();

    public void addImage(String uuid, String filename){
        BoardImages image = BoardImages.builder()
                .uuid(uuid)
                .filename(filename)
                .boards(this)
                .ord(boardImages.size())
                .build();
        boardImages.add(image);
    }

    public void removeImage(){
        boardImages.forEach(boardImage ->
                boardImage.changeBoards(null));
        this.boardImages.clear();

    }

    public void updateReadcount() {readcount = readcount + 1; }

    public void change(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
