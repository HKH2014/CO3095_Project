package com.example.auctionhouse_webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CreateBuyerController {
    @GetMapping("/ViewListings")
    public String viewList(Model model) {
        return "ViewListings";
    }
}