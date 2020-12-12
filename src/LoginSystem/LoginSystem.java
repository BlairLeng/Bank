package LoginSystem;
import java.util.ArrayList;
import java.util.HashMap;

import Common.Common;
import User.User;
import Account.Account;


public class LoginSystem implements LoginFunctions{
	
	private HashMap<String, String> Users;
	private ArrayList<User> UsersObjects;
	
	public LoginSystem
	(
			HashMap<String, String> Users,
			ArrayList<User> UsersObjects
	) {
		this.Users = Users;
		this.UsersObjects = UsersObjects;
	}

	@Override
	public String SignupNewUser(String username, String password, String type) {
		// TODO Auto-generated method stub
		if (Users.containsKey(username)) {
			return Common.UsernameAlreadyExists;
		}
		if (!Users.containsKey(username)) {
			Users.put(username, password);
			User newUser = new User(username, password, type); 
			UsersObjects.add(newUser);
			return Common.Success;
		}
		return Common.Failed;
	}

	@Override
	public String LoginAsUser(String username, String password) {
		// TODO Auto-generated method stub
		if (!Users.containsKey(username)) {
			return Common.UsernameNotFound;
		}
		if (Users.get(username) != password) {
			return Common.InvalidPassword;
		}
		if (Users.get(username) == password) {
			return Common.Success;
		}
		return Common.Failed;
	}

	@Override
	public String LoginAsManager(String username, String password) {
		// TODO Auto-generated method stub
		if (!Users.containsKey(username)) {
			return Common.UsernameNotFound;
		}
		if (Users.get(username) != password) {
			return Common.InvalidPassword;
		}
		if (Users.get(username) == password) {
			return Common.Success;
		}
		return Common.Failed;
	}
}
