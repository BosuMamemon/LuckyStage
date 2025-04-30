package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NoticeImages implements Comparable<NoticeImages>{
    @Id
    private String uuid;
    private String filename;
    private int ord;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notices notices;


    @Override
    public int compareTo(NoticeImages other) { return this.ord - other.ord; }
    public void changeNotices(Notices notices){this.notices = notices;}

}
