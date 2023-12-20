package com.example.auctionhouse_webapplication.service;
import com.example.auctionhouse_webapplication.dto.SubscriptionDto;
import com.example.auctionhouse_webapplication.model.Subscription;
import com.example.auctionhouse_webapplication.repo.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    @Autowired
    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void subscribe(SubscriptionDto subscriptionDto) {
        // Tworzenie nowego obiektu subskrypcji
        Subscription subscription = new Subscription();
        subscription.setUserId(subscriptionDto.getUserId());
        subscription.setAuctionId(subscriptionDto.getAuctionId());

        // Zapisanie subskrypcji w bazie danych
        subscriptionRepository.save(subscription);
    }
}