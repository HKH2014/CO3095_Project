package com.example.auctionhouse_webapplication.repo;



import com.example.auctionhouse_webapplication.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}