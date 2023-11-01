package com.example.auctionhouse_webapplication.model;

import jakarta.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;

// this is where a user can create a listing

public class AddListing {

    // will we need this for saving information in a database
    @Id
    @GeneratedValue

    public String name;

    public String Description;

    public String email;

    public int base_price;


    // do we need anything else??


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBase_price() {
        return base_price;
    }

    public void setBase_price(int base_price) {
        this.base_price = base_price;
    }
}
