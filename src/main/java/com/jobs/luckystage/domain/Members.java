package com.jobs.luckystage.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Members {
    @Id
    @Column(unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String tel;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "consert_pick")
    private Set<Concerts> concerts;
}
