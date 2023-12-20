package com.example.auctionhouse_webapplication.repo;



import com.example.auctionhouse_webapplication.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}