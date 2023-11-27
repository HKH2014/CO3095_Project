package com.example.auctionhouse_webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BuyerLoginController {
    @RequestMapping("/userdetails")
    public String UserDetails (Model model){
        return "UserDetails";
    }
}
