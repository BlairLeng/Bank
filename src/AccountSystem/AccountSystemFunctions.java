package AccountSystem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccountSystemFunctions<Account> {
	
	// queryAccount
	public Account QueryAccountInformation(String AccountID) throws SQLException;
	// make loan
	public String RequestLoan(String AccountID, double money, double interestRate);
	// make transaction
	public String MakeTransaction(String senderID, String receiverID, double money, String transName) throws SQLException;
	// deposit
	public String Deposit(String AccountID, double money) throws SQLException;
	// withdraw
	public String Withdraw(String AccountID, double money) throws SQLException;
	// open and close fee
	
	// saving account link to security
	
	// transaction to security
	
	
}
