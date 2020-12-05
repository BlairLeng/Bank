package LoginSystem;
import java.util.HashMap;

import Common.Common;
import Account.Account;


public class LoginSystem implements LoginFunctions{
	
	private HashMap<String, String> Users;
	
	public LoginSystem
	(
			HashMap<String, String> Users
	) {
		this.Users = Users;
	}

	@Override
	public String SignupNewUser(String username, String password) {
		// TODO Auto-generated method stub
		if (Users.containsKey(username)) {
			return Common.UsernameAlreadyExists;
		}
		if (!Users.containsKey(username)) {
			Users.put(username, password);
			return Common.Success;
		}
		return Common.Failed;
	}

	@Override
	public String SignupNewSavingAccount(String username, double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String SignupNewCheckingAccount(String username, double money) {
		// TODO Auto-generated method stub
		return null;
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
		return null;
	}

	
	
	
}
