package AccountSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Account.Account;
import Common.Common;
import TransactionSystem.Transaction;

public class AccountSystemSQL {

	public static ResultSet QueryAccountInformation(String AccountID, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account WHERE AccountID = "
					+ "'"
					+ AccountID
					+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public static String checkCurrency(String AID1, String AID2, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AID1
				+ "'";
		ResultSet rs1 = stmt.executeQuery(sql);
		sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AID2
				+ "'";
		ResultSet rs2 = stmt.executeQuery(sql);
		if (rs1.getString("CurrencyType").equals(rs2.getString("CurrencyType"))) {
			return Common.Success;
		}
		return Common.Failed;
	}
	
	public static String checkMoney(String AccountID, double money, Connection con) throws SQLException{
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AccountID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (money >= rs.getDouble("CurrentBalance")) {
			return Common.Success;
		}
		return Common.Failed;
	}
	
	public static String MakeTransaction(Transaction t, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO `trans` "
				+ "(`TransID`,`TransName`,`SenderID`,`ReceiverID`,`Money`,`Datetime`) "
				+ "VALUES (" 
				+ '"' 
				+ t.getTransTime() 
				+ '"' 
				+ ", " 
				+ '"'
				+ t.gettransname()  
				+ '"'
				+ ", " 
				+ '"' 
				+ t.getsenderUUID() 
				+ '"' 
				+ ", " 
				+ '"' 
				+ t.getreceiverUUID() 
				+ '"' 
				+ ", "  
				+ t.getmoney() 
				+ ", " 
				+ '"' 
				+ t.gettransUUID()
				+ '"' 
				+ ")";
		ResultSet rs = stmt.executeQuery(sql);
		return Common.Success;
	}
	
	public static String Deposit(Transaction t, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO `trans` "
				+ "(`TransID`,`TransName`,`SenderID`,`ReceiverID`,`Money`,`Datetime`) "
				+ "VALUES (" 
				+ '"' 
				+ t.getTransTime() 
				+ '"' 
				+ ", " 
				+ '"'
				+ t.gettransname()  
				+ '"'
				+ ", " 
				+ '"' 
				+ t.getsenderUUID() 
				+ '"' 
				+ ", " 
				+ '"' 
				+ t.getreceiverUUID() 
				+ '"' 
				+ ", "  
				+ t.getmoney() 
				+ ", " 
				+ '"' 
				+ t.gettransUUID()
				+ '"' 
				+ ")";
		ResultSet rs = stmt.executeQuery(sql);
		return Common.Success;
	}
	
	public static String Withdraw(Transaction t, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO `trans` "
				+ "(`TransID`,`TransName`,`SenderID`,`ReceiverID`,`Money`,`Datetime`) "
				+ "VALUES (" 
				+ '"' 
				+ t.getTransTime() 
				+ '"' 
				+ ", " 
				+ '"'
				+ t.gettransname()  
				+ '"'
				+ ", " 
				+ '"' 
				+ t.getsenderUUID() 
				+ '"' 
				+ ", " 
				+ '"' 
				+ t.getreceiverUUID() 
				+ '"' 
				+ ", "  
				+ t.getmoney() 
				+ ", " 
				+ '"' 
				+ t.gettransUUID()
				+ '"' 
				+ ")";
		ResultSet rs = stmt.executeQuery(sql);
		return Common.Success;
	}
	
}
