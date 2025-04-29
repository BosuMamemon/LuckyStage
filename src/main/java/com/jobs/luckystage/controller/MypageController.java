package com.jobs.luckystage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mypage")
public class MypageController {

    @GetMapping("/mypage")
    public void mypagePage(){
    }

    @GetMapping("/reservation")
    public void reservationPage(){
    }

    @GetMapping("/pick")
    public void pickPage(){

    }
}
