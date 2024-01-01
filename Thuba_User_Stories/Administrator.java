import java.util.List;

import auction.User;

public class Administrator {
    private String username;
    private String password;

    public void addAuctionRecord(AuctionHistoryService service, AuctionRecord record) {
        service.addAuctionRecord(this, record);
    }

    public void updateAuctionRecord(AuctionHistoryService service, int index, AuctionRecord updatedRecord) {
        service.updateAuctionRecord(this, index, updatedRecord);
    }

    public void deleteAuctionRecord(AuctionHistoryService service, int index) {
        service.deleteAuctionRecord(this, index);
    }

    public void addUser(UserManagementService service, User user) {
        service.addUser(this, user);
    }

    public void updateUser(UserManagementService service, int index, User updatedUser) {
        service.updateUser(this, index, updatedUser);
    }

    public void deleteUser(UserManagementService service, int index) {
        service.deleteUser(this, index);
    }

    public List<User> viewUsers(UserManagementService service) {
        return service.viewUsers(this);
    }
}