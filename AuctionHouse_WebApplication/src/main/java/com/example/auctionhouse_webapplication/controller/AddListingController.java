package com.example.auctionhouse_webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddListingController {
    @GetMapping("/AddListingsPage")
    public String addList(Model model) {
        return "AddListingPage";
    }
}
