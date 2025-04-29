package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReviewImages {
    @Id
    private String uuid;
    private String filename;
    @Builder.Default
    private int ord = 0;
    @ManyToOne(fetch = FetchType.LAZY)
    private Reviews reviews;
}
