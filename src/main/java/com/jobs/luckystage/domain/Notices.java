package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notices extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noticeNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @ColumnDefault("0")
    private int hitcount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Members members;

    @OneToMany(mappedBy = "notices",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.ALL},
            orphanRemoval = true)
    @Builder.Default
    @BatchSize(size = 20)
    private Set<NoticeImages> imagesSet=new HashSet<>();



    @OneToMany(mappedBy = "notices", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<NoticeComments> noticeComments;

    public void addImage(String uuid, String fileName) {
        NoticeImages image=NoticeImages.builder()
                .uuid(uuid)
                .filename(fileName)
                .notices(this)
                .ord(imagesSet.size())
                .build();
        imagesSet.add(image);
    }
    public void clearImages() {
        imagesSet.forEach(noticesImage -> noticesImage.changeNotices(null));
        this.imagesSet.clear();
    }
    public void updateHitcount(){ hitcount = hitcount +1; }
    public void change(String title, String content){
        this.title = title;
        this.content = content;
    }


}
