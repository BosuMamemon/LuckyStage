package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Reviews extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ColumnDefault("0")
    private int hitcount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members members;
    @ColumnDefault("0")
    private double rating;
    @OneToMany(mappedBy = "reviews", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewImages> reviewImages;
    @OneToMany(mappedBy = "reviews", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReviewComments> reviewComments;

    public void addImage(String uuid, String fileName){
        ReviewImages reviewImage = ReviewImages.builder()
                .uuid(uuid)
                .filename(fileName)
                .reviews(this)
                .ord(reviewImages.size())
                .build();
        reviewImages.add(reviewImage);
    }
}
