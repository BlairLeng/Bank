package User;

import Account.Account;
import java.util.ArrayList;

public interface UserSystemFunctions {
	public String CreateSavingAccount(String username, double money);
	
	public String CreateCheckingAccount(String username, double money);
	
	public ArrayList<Account> ViewAccounts(String username);
}
