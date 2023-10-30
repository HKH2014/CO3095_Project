package com.example.auctionhouse_webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// this is a test controller
@Controller
public class MainController {

    @RequestMapping("/greet")
    public String greetWorld(Model model) {
        model.addAttribute("name", "World");
        return "greeting";
    }
}