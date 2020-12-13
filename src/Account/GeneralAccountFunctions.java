package Account;

import TransactionSystem.Transaction;

public interface GeneralAccountFunctions {
	
	public String RequestLoan();
	
	public String getTransactionHistory();
	
	public double getCurrentBalance();
	
	public String Deposit(double money);
	
	public String Withdraw(double money);
}
