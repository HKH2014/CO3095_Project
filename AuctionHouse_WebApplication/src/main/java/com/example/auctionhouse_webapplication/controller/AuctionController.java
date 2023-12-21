package com.example.auctionhouse_webapplication.controller;

import jakarta.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.auctionhouse_webapplication.dto.AuctionDto;
import com.example.auctionhouse_webapplication.dto.BidDto;
import com.example.auctionhouse_webapplication.dto.NewAuctionDto;
import com.example.auctionhouse_webapplication.service.AuctionService;
import java.security.Principal;
import java.util.List;

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
    @GetMapping("/{auctionId}/set-max-bid")
    public String prepareForMaxBid(@PathVariable("auctionId") Long auctionId, Model model) {
        BidDto bidDto = new BidDto();
        bidDto.setAuctionId(auctionId);
        model.addAttribute("bid", bidDto);
        return "auctions_make_bet";
    }

    @PreAuthorize("hasAuthority('BUYER')")
    @PostMapping("/set-max-bid")
    public String setMaxBid(@ModelAttribute BidDto bidDto, Principal principal) {
        auctionService.setMaxBid(bidDto, principal.getName());
        return "redirect:/auctions";
    }
}
