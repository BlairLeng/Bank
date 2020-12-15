package LoanSystem;

import java.util.ArrayList;

public interface LoanSystemFunctions {
	
	public ArrayList<Loan> getAccountLoans(String AccountID);
	
	public String Repayment(String AccountID, String LoanID, double money);
	
	public Loan getLoanInformation(String LoanID); 
}
