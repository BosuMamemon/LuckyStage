package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardImages implements Comparable<BoardImages>{
    @Id
    private String uuid;
    private String filename;
    private int ord;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="boardNum")
    private Boards boards;

    public int compareTo(BoardImages other) {
        return this.ord - other.ord;
    }
    public void changeBoards(Boards boards) { this.boards = boards; }
}



