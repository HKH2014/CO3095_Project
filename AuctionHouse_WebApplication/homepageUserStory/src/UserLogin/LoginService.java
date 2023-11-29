package UserLogin;

public class LoginService {
		private User user;
		
		public LoginService(User user) {
			this.user = user;
		}
		
		public boolean login(String username, String password) {
			return user.getUsername().equals(username) && user.getPassword().equals(password);
		}
}
