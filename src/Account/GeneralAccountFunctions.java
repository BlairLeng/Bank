package Account;

import CentralSystem.Transaction;

public interface GeneralAccountFunctions {
	
	public String RequestLoan();
	
	public Transaction[] getTransactionHistory();
	
	public double getCurrentBalance();
	
	public String Deposit(double money);
	
	public String Withdraw(double money);
}
