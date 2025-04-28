package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FAQs extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long faqNum;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    private int readcount;

    public void change(String title,String content){
        this.title=title;
        this.content=content;
    }
    public void changeReadcount(){
        this.readcount=this.readcount+1;
    }


}
