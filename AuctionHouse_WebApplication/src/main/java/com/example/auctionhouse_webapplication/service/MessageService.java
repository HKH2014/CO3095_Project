package com.example.auctionhouse_webapplication.service;

import com.example.auctionhouse_webapplication.dto.MessageDto;
import com.example.auctionhouse_webapplication.model.Message;
import com.example.auctionhouse_webapplication.repo.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void sendMessage(MessageDto messageDto) {
        // Tworzenie nowego obiektu wiadomości
        Message message = new Message();
        message.setSenderId(messageDto.getSenderId());
        message.setRecipientId(messageDto.getRecipientId());
        message.setMessageContent(messageDto.getMessageContent());

        // Zapisanie wiadomości w bazie danych
        messageRepository.save(message);
    }
}