package com.jobs.luckystage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instructions")
public class InstructionController {

    @GetMapping("/instruction")
    public String instructionPage() {
        return "instructions/instruction";
    }
}
