package User;

import java.util.ArrayList;

import Account.Account;
import TransactionSystem.Transaction;

public interface ManagerSystemFunctions {
	
	public ArrayList<Transaction> Alltransactions() throws Exception;
	
	public ArrayList<Account> Allaccounts() throws Exception;

}
