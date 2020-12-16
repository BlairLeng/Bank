package LoanSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Common.Common;
import AccountSystem.AccountSystemSQL;

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
	
	public static String Repay(String AccountID, String LoanID, double money, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "UPDATE `loan`"
				+ "SET loan.MoneyReturned = loan.MoneyReturned + "
				+ money
				+ " "
				+ "WHERE loan.LoanID = "
				+ '"'
				+ LoanID
				+ '"';
		stmt.executeUpdate(sql);
		
		double totalMoney = AccountSystemSQL.getMoney(AccountID,con) - money;
		sql = "UPDATE `account` "
				+ "SET account.CurrentBalance = "
				+ totalMoney
				+ " "
				+ "WHERE account.AccountID = "
				+ '"'
				+ AccountID
				+ '"';
		stmt.execute(sql);
		return Common.Success;
	}
	
	public static String legalLoan(String LoanID, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM loan WHERE LoanID = "
				+ "'"
				+ LoanID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			if (rs.getInt("Status") == 0) return Common.Success;
			else return Common.NotValidLoanID;
		}
		return Common.QueryFailed;
	}
	
	public static double maximumRepayment(String LoanID, double money, Connection con) throws SQLException{
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM loan WHERE LoanID = "
				+ "'"
				+ LoanID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			double difference = rs.getDouble("MoneyOwed") - rs.getDouble("MoneyReturned");
			return Math.min(money, difference);
		}
		return money;
	}
	
	
	public static String getLoanAccountID(String LoanID, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM loan WHERE LoanID = "
				+ "'"
				+ LoanID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			return rs.getString("AccountID");
		}		
		return Common.QueryFailed;
	}
	
	public static double getLoanMoney(String LoanID, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM loan WHERE LoanID = "
				+ "'"
				+ LoanID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			return rs.getDouble("MoneyLoaned");
		}		
		return -1;
	}
	
	public static void changeStatus(String LoanID, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM loan WHERE LoanID = "
				+ "'"
				+ LoanID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			double moneyReturned = rs.getDouble("MoneyReturned");
			double moneyOwed = rs.getDouble("MoneyOwed");
			if (moneyReturned >= moneyOwed) {
				sql = "UPDATE `loan` "
						+ "SET loan.Status = "
						+ 1
						+ " "
						+ "WHERE loan.LoanID = "
						+ '"'
						+ LoanID
						+ '"';
				stmt.executeUpdate(sql);
				return;
			}
		}
		return;
	}

}
