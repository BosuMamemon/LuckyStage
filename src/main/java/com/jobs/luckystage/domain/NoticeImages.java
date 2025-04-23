package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NoticeImages implements Comparable<NoticeImages>{
    @Id
    private String uuid;
    private String filename;
    private int ord;

    @ManyToOne(fetch = FetchType.LAZY)
    private Notices notices;


    @Override
    public int compareTo(NoticeImages o) { return this.ord - o.ord; }
    public void changeNotices(Notices notices){this.notices = notices;}

}
