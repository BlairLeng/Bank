package User;

import java.util.ArrayList;

import Account.Account;
import TransactionSystem.Transaction;

public interface ManagerSystemFunctions {
	
	public ArrayList<Account> Allaccounts() throws Exception;
	
	public ArrayList<Transaction> Alltrans() throws Exception;
	
	public ArrayList<Transaction> Usertrans(String username) throws Exception;

}
