package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Getter //추가
@Setter //추가
@Entity
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //이거 디비에 공연정보가 아닌 회원정보가 쳐 넘어가길래 일단은 해놓았는데 삭제해도됨
    private long id;
    @Getter //getter,setter을 붙여야 값이 넘어가던데.. 이렇게 하는 방법 말고 안건들고 하는거있으면 지울게! ㅠㅠ
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "members_username") //추가
    private Members members;
    @Getter
    @Setter
    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime regDate;
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime selectedDate;
    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "concerts_concert_num") //추가
    private Concerts concerts;
    @Getter
    @Setter
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lotteryDate;

}
