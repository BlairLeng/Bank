package User;

import java.util.ArrayList;

public class User {
	private String username;
	private String password;
	private String type;
//	private ArrayList<String> AccountIDs;
	
//	public User(String username, String password, String type, ArrayList<String> AccountIDs) {
	public User(String username, String password, String type) {
		this.username = username;
		this.password = password;
		this.type = type;
//		this.AccountIDs = AccountIDs;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public String getType() {
		return this.type;
	}
}
