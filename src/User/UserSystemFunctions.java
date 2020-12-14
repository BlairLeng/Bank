package User;

import Account.Account;
import java.util.ArrayList;

public interface UserSystemFunctions {
	public String CreateSavingAccount(String username, double money, String currencyType) throws Exception;
	
	public String CreateCheckingAccount(String username, double money, String currencyType) throws Exception;
	
	public ArrayList<Account> ViewAccounts(String username) throws Exception;
}
