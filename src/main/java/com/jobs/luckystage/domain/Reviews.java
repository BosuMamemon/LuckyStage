package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Reviews extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    private Members members;
    @ColumnDefault("0")
    private double rating;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concerts_concertNum")
    private Concerts concerts;
    @OneToMany(mappedBy = "reviews", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ReviewImages> reviewImages = new HashSet<>();

    public void addImage(String uuid, String fileName){
        ReviewImages reviewImage = ReviewImages.builder()
                .uuid(uuid)
                .filename(fileName)
                .ord(this.reviewImages.size())
                .reviews(this)
                .build();
        this.reviewImages.add(reviewImage);
    }
}
