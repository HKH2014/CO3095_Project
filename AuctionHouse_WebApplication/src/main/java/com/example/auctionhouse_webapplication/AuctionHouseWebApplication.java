package com.example.auctionhouse_webapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity(proxyTargetClass = true)
@EnableScheduling
public class AuctionHouseWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuctionHouseWebApplication.class, args);
    }

}
