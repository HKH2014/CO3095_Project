import java.util.List;
import auction.*;

public class Administrator {
    private String username;
    private String password;

    public Administrator(String string, String string2) {
    }

    public void addAuctionRecord(AuctionHistoryService service, AuctionRecord record) {
        service.addAuctionRecord(this, record);
    }

    public void updateAuctionRecord(AuctionHistoryService service, int index, AuctionRecord updatedRecord) {
        service.updateAuctionRecord(this, index, updatedRecord);
    }

    public void addUser(UserManagementService service, User user1) {
        service.addUser(this, user1);
    }

    public List<User> viewUsers(UserManagementService service) {
        return service.viewUsers(this);
    }

    public void addUser(UserManagementService service, Seller user2) {
    }

    public void updateUser(UserManagementService service, int i, Seller updatedUser) {
    }

    public void deleteUser(UserManagementService service, int i) {
    }

	public void deleteAuctionRecord(AuctionHistoryService service, int i) {
		// TODO Auto-generated method stub
		
	}
}