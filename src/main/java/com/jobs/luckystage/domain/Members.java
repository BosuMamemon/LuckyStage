package com.jobs.luckystage.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString(exclude = "members")
public class Members {
    @Id
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String nickname;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String tel;
    private String email;
    private String role;
}
