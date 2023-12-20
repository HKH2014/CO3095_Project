package com.example.auctionhouse_webapplication.controller;

import com.example.auctionhouse_webapplication.dto.BidDto;
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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/auctions")
public class AuctionController {

    private final AuctionService auctionService;

    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    @GetMapping
    public String getAuctions(Model model) {
        final List<AuctionDto> auctions = auctionService.getAuctions();
        model.addAttribute("auctions", auctions);
        return "auctions_view";
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @GetMapping("/new")
    public String prepareNewAuctionView(Model model) {
        model.addAttribute("auction", new NewAuctionDto());
        return "auctions_new";
    }

    @PreAuthorize("hasAuthority('SELLER')")
    @PostMapping("/new")
    public String createAuction(@Valid NewAuctionDto newAuctionDto, Principal principal) {
        auctionService.createNewAuction(newAuctionDto, principal.getName());
        return "redirect:/auctions";
    }

    @PreAuthorize("hasAuthority('BUYER')")
    @PostMapping("/{auctionId}/set-max-bid")
    public String setMaxBid(@PathVariable int auctionId, @ModelAttribute BidDto bidDto, Principal principal) {
        auctionService.setMaxBid(auctionId, bidDto);
        return "redirect:/auctions/" + auctionId;
    }
}
