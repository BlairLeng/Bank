package LoginSystem;
import java.util.HashMap;

import Common.Common;
import Account.Account;


public class LoginSystem {
	
	private HashMap<String, String> CustomerAccountsPasswords;
	private HashMap<String, String> ManagerAccountsPasswords;
	
	public LoginSystem(HashMap<String, String> CustomerAccountsPasswords,HashMap<String, String> ManagerAccountsPasswords) {
		this.CustomerAccountsPasswords = CustomerAccountsPasswords;
		this.ManagerAccountsPasswords = ManagerAccountsPasswords;
	}
	
	public String LoginAsCustomer(String username, String password) {
		if (CustomerAccountsPasswords.get(username) == password) {
			return Common.Success;			
		}
		if (!CustomerAccountsPasswords.containsKey(username)) {
			return Common.UsernameNotFound;
		}
		if (CustomerAccountsPasswords.get(username) != password) {
			return Common.InvalidPassword;
		}
		return Common.Failed;
	}
	
	public String LoginAsManager(String username, String password) {
		if (ManagerAccountsPasswords.get(username) == password) {
			return Common.Success;			
		}
		if (!ManagerAccountsPasswords.containsKey(username)) {
			return Common.UsernameNotFound;
		}
		if (ManagerAccountsPasswords.get(username) != password) {
			return Common.InvalidPassword;
		}
		return Common.Failed;
	}
	
	
}
