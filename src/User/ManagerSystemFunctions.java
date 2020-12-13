package User;

import java.util.ArrayList;

import Account.Account;
import Account.CheckingAccount;
import Account.SavingAccount;
import CentralSystem.Transaction;

public interface ManagerSystemFunctions {
	public ArrayList<CheckingAccount> Allcheckaccounts() throws Exception;
	
	public ArrayList<SavingAccount> Allsaveaccounts() throws Exception;
	
	public ArrayList<Transaction> Alltransactions() throws Exception;
	
	public ArrayList<Account> Allaccounts() throws Exception;

}
