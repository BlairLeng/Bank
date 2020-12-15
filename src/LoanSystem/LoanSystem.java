package LoanSystem;

import java.sql.Connection;
import java.util.ArrayList;

public class LoanSystem implements LoanSystemFunctions {
	public Connection con;
	
	public LoanSystem(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Loan> getAccountLoans(String AccountID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String Repayment(String AccountID, String LoanID, double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan getLoanInformation(String LoanID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
