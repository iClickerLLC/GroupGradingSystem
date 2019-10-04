
public class Login {
	
	private int id;
	private String username;
	private String password;

	public Login(int id, String username, String password) {
		
		this.id = id;
		this.username = username;
		this.password = password;
		
	}
	
	public int getID() {
		return this.id;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void resetPassword(String password) {
		// ToDo: Match current password to reset
		this.password = password;
	}
	
}
