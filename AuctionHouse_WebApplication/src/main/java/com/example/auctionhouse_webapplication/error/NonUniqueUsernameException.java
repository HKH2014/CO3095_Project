package com.example.auctionhouse_webapplication.error;

public class NonUniqueUsernameException extends RuntimeException {

    public NonUniqueUsernameException() {
        super("Provided username already exist in the system.");
    }

}
