package LoanSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Common.Common;

public class LoanSystemSQL {
	
	public static ResultSet getAccountLoans(String AccountID, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM loan WHERE AccountID = "
				+ "'"
				+ AccountID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public static String Repayment(String AccountID, String LoanID, double money, Connection con) throws SQLException {
		
		Statement stmt = con.createStatement();
		String sql = "UPDATE `loan`"
				+ "SET loan.MoneyReturned = loan.MoneyReturned + "
				+ money
				+ " "
				+ "WHERE loan.LoanID = "
				+ '"'
				+ LoanID
				+ '"';
		return Common.Success;
	}

}
