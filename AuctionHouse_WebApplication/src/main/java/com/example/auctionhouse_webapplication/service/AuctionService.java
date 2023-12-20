package com.example.auctionhouse_webapplication.service;

import com.example.auctionhouse_webapplication.dto.BidDto;
import com.example.auctionhouse_webapplication.error.EntityNotFoundException;
import com.example.auctionhouse_webapplication.error.InvalidBidException;
import com.example.auctionhouse_webapplication.model.Bid;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.auctionhouse_webapplication.dto.AuctionDto;
import com.example.auctionhouse_webapplication.dto.NewAuctionDto;
import com.example.auctionhouse_webapplication.error.InvalidAuctionParametersException;
import com.example.auctionhouse_webapplication.model.Auction;
import com.example.auctionhouse_webapplication.model.User;
import com.example.auctionhouse_webapplication.repo.AuctionRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final UserService userService;

    public AuctionService(AuctionRepository auctionRepository, UserService userService) {
        this.auctionRepository = auctionRepository;
        this.userService = userService;
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
    public void setMaxBid(int auctionId, BidDto bidDto) {
        Auction auction = auctionRepository.findById(auctionId)
                .orElseThrow(() -> new EntityNotFoundException("Auction not found"));

        // Sprawdź, czy nowa oferta jest wyższa od aktualnej maksymalnej oferty
        if (bidDto.getAmount() > auction.getMaxBid()) {
            // Jeśli tak, ustaw nową ofertę jako maksymalną
            auction.setMaxBid(bidDto.getBid());
        } else {
            // Jeśli nie, rzuć wyjątek
            throw new InvalidBidException("Bid amount must be higher than the current max bid");
        }

        // Zapisz zmiany w aukcji
        auctionRepository.save(auction);
    }
}