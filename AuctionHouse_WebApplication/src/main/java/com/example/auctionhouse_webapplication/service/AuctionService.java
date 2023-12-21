package com.example.auctionhouse_webapplication.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.auctionhouse_webapplication.dto.AuctionDto;
import com.example.auctionhouse_webapplication.dto.BidDto;
import com.example.auctionhouse_webapplication.dto.NewAuctionDto;
import com.example.auctionhouse_webapplication.error.EntityNotFoundException;
import com.example.auctionhouse_webapplication.error.InvalidAuctionParametersException;
import com.example.auctionhouse_webapplication.error.InvalidBidException;
import com.example.auctionhouse_webapplication.model.Auction;
import com.example.auctionhouse_webapplication.model.Bid;
import com.example.auctionhouse_webapplication.model.User;
import com.example.auctionhouse_webapplication.repo.AuctionRepository;
import com.example.auctionhouse_webapplication.repo.BidRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserService userService;
    private final BidRepository bidRepository;

    public AuctionService(AuctionRepository auctionRepository, UserService userService, BidRepository bidRepository) {
        this.auctionRepository = auctionRepository;
        this.userService = userService;
        this.bidRepository = bidRepository;
    }

    @Transactional(readOnly = true)
    public List<AuctionDto> getAuctions() {
        return auctionRepository.findAll().stream().map(this::mapToDto).toList();
    }

    @Transactional
    public void createNewAuction(NewAuctionDto newAuctionDto, String username) {
        final User user = userService.getUserOrThrow(username);
        Auction auction = new Auction();
        BeanUtils.copyProperties(newAuctionDto, auction);
        auction.setEndDate(getParsedEndDate(newAuctionDto));
        auction.setSeller(user);

        if (auction.getEndDate().isBefore(LocalDate.now())) {
            throw new InvalidAuctionParametersException("End date cannot be in the past");
        }

        auctionRepository.save(auction);
    }

    private LocalDate getParsedEndDate(final NewAuctionDto newAuctionDto) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(newAuctionDto.getEndDate(), formatter);
    }

    private AuctionDto mapToDto(Auction auction) {
        AuctionDto dto = new AuctionDto();
        dto.setId(auction.getId());
        dto.setName(auction.getName());
        dto.setDescription(auction.getDescription());
        dto.setEndDate(auction.getEndDate().toString());
        dto.setMinBet(auction.getMinBet());
        dto.setCurrentPrice(auction.getCurrentPrice());
        dto.setSellerName(auction.getSeller().getUsername());
        return dto;
    }

    @Transactional
    public void setMaxBid(BidDto bidDto, String username) {
        final Auction auction = getAuctionOrThrow(bidDto.getAuctionId());
        final User user = userService.getUserOrThrow(username);
        final Bid bid = mapToBid(bidDto, auction, user);

        bidRepository.save(bid);
    }

    private Bid mapToBid(final BidDto bidDto, final Auction auction, final User user) {
        Bid bid = new Bid();
        bid.setMaxBid(bidDto.getMaxBid());
        bid.setCurrentBid(calculateCurrentBid(auction, bidDto.getMaxBid()));
        bid.setAuction(auction);
        bid.setBidder(user);
        return bid;
    }

    private double calculateCurrentBid(final Auction auction, final double maxBid) {
        final double auctionCurrentPrice = auction.getCurrentPrice();
        if (auctionCurrentPrice > maxBid) {
            return maxBid;
        }
        return Math.min(auctionCurrentPrice + auction.getMinBet(), maxBid);
    }

    private Auction getAuctionOrThrow(final Long auctionId) {
        return auctionRepository.findById(auctionId)
            .orElseThrow(() -> new EntityNotFoundException("Auction not found"));
    }
}