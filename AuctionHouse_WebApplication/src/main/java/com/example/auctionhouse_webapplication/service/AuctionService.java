package com.example.auctionhouse_webapplication.service;

import com.example.auctionhouse_webapplication.repo.AuctionRepository;
import org.springframework.stereotype.Service;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }



}
