package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("/random-quote")
    public String randomQuote(Model model) {
        String[] quotes = {
                "랜덤 명언 1. -저자1-",
                "랜덤 명언 2. -저자2-",
                "랜덤 명언 3. -저자3-",
                "랜덤 명언 4. -저자4-"
        };
        int randInt = (int) (Math.random() * quotes.length);
        model.addAttribute("randomQuote", quotes[randInt]);
        return "quote";
    }
}
