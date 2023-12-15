package com.example.auctionhouse_webapplication.dto;

public class RegistrationDtoWithRoles extends RegistrationDto {

    private Boolean isAdmin;
    private Boolean isSeller;

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(final Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(final Boolean isSeller) {
        this.isSeller = isSeller;
    }
}
