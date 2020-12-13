package User;

import java.time.LocalDate;
import java.util.ArrayList;

import Account.Account;
import LoanSystem.Loan;
import TransactionSystem.Transaction;

public interface ManagerSystemFunctions {
	
	public ArrayList<Account> Allaccounts() throws Exception;
	
	public ArrayList<Transaction> Alltrans() throws Exception;
	
	public ArrayList<Transaction> Usertrans(String username) throws Exception;
	
	public ArrayList<Transaction> GetDaytrans(LocalDate date) throws Exception;
	
	public String ApproveLoan(String loanid) throws Exception;
	
	public ArrayList<Loan> Allloans() throws Exception;
	
	public ArrayList<Loan> UnapprovedLoan() throws Exception;

	public ArrayList<String> GetDayReport(LocalDate date) throws Exception;
}
