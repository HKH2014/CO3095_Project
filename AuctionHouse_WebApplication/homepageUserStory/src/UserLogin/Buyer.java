package UserLogin;

public class Buyer {
		private LoginService loginService;
		
		public Buyer(LoginService loginService) {
			this.loginService = loginService;
		}
		
		public boolean login(String username, String password) {
			return loginService.login(username, password);
		}
}
