package com.example.auctionhouse_webapplication.controller;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.auctionhouse_webapplication.dto.AuctionDto;
import com.example.auctionhouse_webapplication.dto.NewAuctionDto;
import com.example.auctionhouse_webapplication.service.AuctionService;
import java.security.Principal;
import java.util.List;

@Controller
public class AuctionController {

    private AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping("/auctions")
    public String getAuctions(Model model) {
        final List<AuctionDto> auctions = auctionService.getAuctions();
        model.addAttribute("auctions", auctions);
        return "auctions_view";
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @GetMapping("/auctions/new")
    public String prepareNewAuctionView(Model model) {
        model.addAttribute("auction", new NewAuctionDto());
        return "auctions_new";
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @PostMapping("/auctions/new")
    public String createAuction(@Valid NewAuctionDto newAuctionDto, Principal principal) {
        auctionService.createNewAuction(newAuctionDto, principal.getName());
        return "redirect:/auctions";
    }

}
