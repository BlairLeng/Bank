package LoanSystem;

import java.sql.SQLException;
import java.util.ArrayList;

public interface LoanSystemFunctions {
	
	public ArrayList<Loan> getAccountLoans(String AccountID) throws SQLException;
	
	public String Repayment(String AccountID, String LoanID, double money) throws SQLException;
	
	public Boolean getAccountHasLoans(String AccountID) throws SQLException;
	
	public Loan getLoanInformation(String LoanID); 
}
