package com.example.auctionhouse_webapplication.controller;

import com.example.auctionhouse_webapplication.dto.SubscriptionDto;
import com.example.auctionhouse_webapplication.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestBody SubscriptionDto subscriptionDto) {
        subscriptionService.subscribe(subscriptionDto);
        return ResponseEntity.ok("Subscribed to auction end notifications");
    }
}
