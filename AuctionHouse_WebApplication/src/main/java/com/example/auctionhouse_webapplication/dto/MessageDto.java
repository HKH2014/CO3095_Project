package com.example.auctionhouse_webapplication.dto;

public class MessageDto {
    private String senderId;
    private String recipientId;
    private String messageContent;

    // Konstruktor, gettery i settery
    public MessageDto() {}

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(String recipientId) {
        this.recipientId = recipientId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
