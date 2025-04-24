package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConcertImages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String filename;
    @ColumnDefault("0")
    private int ord;
    @ManyToOne(fetch = FetchType.LAZY)
    private Concerts concerts;
}
