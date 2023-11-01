package com.example.auctionhouse_webapplication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddListingController {

    @GetMapping("/AddListing")
    public String Volunteer(Model model) {
// the volunteer page
        return "VolunteerPage";
    }
}
