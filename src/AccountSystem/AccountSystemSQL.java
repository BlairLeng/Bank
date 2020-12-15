package AccountSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Account.Account;
import Common.Common;
import LoanSystem.Loan;
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
	
	public static String getAccountType(String AccountID, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AccountID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()){			
			String AccountType = rs.getString("Type");
			return AccountType;
		}
		return Common.QueryFailed;
	}
	
	public static String checkCurrency(String AID1, String AID2, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AID1
				+ "'";
		ResultSet rs1 = stmt.executeQuery(sql);
		String CurrencyType = "";
		if (rs1.next()) {
			CurrencyType = rs1.getString("CurrencyType");
		}
		sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AID2
				+ "'";
		ResultSet rs2 = stmt.executeQuery(sql);
		if (rs2.next()) {
			if (CurrencyType.equals(rs2.getString("CurrencyType"))) {
				return Common.Success;
			}
		}
		
		return Common.TypeDifference;
	}
	
	public static String checkMoney(String AccountID, double money, Connection con) throws SQLException{
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AccountID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			double temp = rs.getDouble("CurrentBalance");
			if (money <= temp) {
				return Common.Success;
			}
		}
		return Common.NotEnoughMoney;
	}
	
	public static String checkAccountCurrencyType(String AccountID, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AccountID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()){			
			String AccountCurrencyType = rs.getString("CurrencyType");
			return AccountCurrencyType;
		}
		return Common.QueryFailed;
	}
	
	public static String TakeServiceFee(Transaction t, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO `trans` "
				+ "(`TransID`,`TransName`,`SenderID`,`ReceiverID`,`Money`,`Datetime`) "
				+ "VALUES (" 
				+ '"' 
				+ t.gettransUUID() 
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
				+ t.getTransTime()
				+ '"' 
				+ ")";
		int rs = stmt.executeUpdate(sql);
		double totalMoney = getMoney(t.getsenderUUID(),con) - t.getmoney();
		sql = "UPDATE `account` "
				+ "SET account.CurrentBalance = "
				+ totalMoney
				+ " "
				+ "WHERE account.AccountID = "
				+ '"'
				+ t.getreceiverUUID()
				+ '"';
		Boolean rs1 = stmt.execute(sql);
		return Common.Success;
	}
	
	public static String giveMoney(String AccountID, double moneyLoaned, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		double totalMoney = getMoney(AccountID, con) + moneyLoaned;
		String sql = "UPDATE `account` "
				+ "SET account.CurrentBalance = "
				+ totalMoney 
				+ " "
				+ "WHERE account.AccountID = "
				+ '"'
				+ AccountID
				+ '"';
		Boolean rs1 = stmt.execute(sql);
		return Common.Success;
	}
	
	public static double getMoney(String AccountID, Connection con) throws SQLException {
		double money = 0;
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account WHERE AccountID = "
				+ "'"
				+ AccountID
				+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			money = rs.getDouble("CurrentBalance");
		}
		return money;
	}
	
	public static String MakeTransaction(Transaction t, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO `trans` "
				+ "(`TransID`,`TransName`,`SenderID`,`ReceiverID`,`Money`,`Datetime`) "
				+ "VALUES (" 
				+ '"' 
				+ t.gettransUUID() 
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
				+ t.getTransTime()
				+ '"' 
				+ ")";
		int rs = stmt.executeUpdate(sql);
		double receiverMoney = getMoney(t.getreceiverUUID(),con) + t.getmoney();
		double senderMoney = getMoney(t.getsenderUUID(),con) - t.getmoney();
		sql = "UPDATE account "
				+ "SET CurrentBalance = "
				+ receiverMoney
				+ " "
				+ "WHERE AccountID = "
				+ '"'
				+ t.getreceiverUUID()
				+ '"';
		Boolean rs1 = stmt.execute(sql);
		sql = "UPDATE account "
				+ "SET CurrentBalance = "
				+ senderMoney
				+ " "
				+ "WHERE AccountID = "
				+ '"'
				+ t.getsenderUUID()
				+ '"';
		Boolean rs2 = stmt.execute(sql);
		return Common.Success;
	}
	
	public static String Deposit(Transaction t, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO `trans` "
				+ "(`TransID`,`TransName`,`SenderID`,`ReceiverID`,`Money`,`Datetime`) "
				+ "VALUES (" 
				+ '"' 
				+ t.gettransUUID()
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
				+ t.getTransTime() 
				+ '"' 
				+ ")";
		int rs = stmt.executeUpdate(sql);
		double totalmoney = getMoney(t.getreceiverUUID(),con) + t.getmoney();
		sql = "UPDATE account "
				+ "SET CurrentBalance = "
				+ totalmoney
				+ " "
				+ "WHERE AccountID = "
				+ '"'
				+ t.getreceiverUUID()
				+ '"'; 
		Boolean rs1 = stmt.execute(sql);
		return Common.Success;
	}
	
	public static String Withdraw(Transaction t, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO `trans` "
				+ "(`TransID`,`TransName`,`SenderID`,`ReceiverID`,`Money`,`Datetime`) "
				+ "VALUES (" 
				+ '"' 
				+ t.gettransUUID() 
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
				+ t.getTransTime()
				+ '"' 
				+ ")";
		int rs = stmt.executeUpdate(sql);
		double totalMoney = getMoney(t.getreceiverUUID(),con) - t.getmoney();
		sql = "UPDATE `account` "
				+ "SET account.CurrentBalance = "
				+ totalMoney
				+ " "
				+ "WHERE account.AccountID = "
				+ '"'
				+ t.getreceiverUUID()
				+ '"';
		Boolean rs1 = stmt.execute(sql);
		return Common.Success;
	}
	
	public static String RequestLoan(Loan loan, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "INSERT INTO `loan` "
				+ "(`LoanID`,`LoanName`,`LoanReason`,`Collateral`,`AccountID`,`BeginDatetime`,`EndDatetime`,`MoneyOwed`,`MoneyLoaned`,`MoneyReturned`,`InterestRate`,`Status`) "
				+ "VALUES (" 
				+ '"' 
				+ loan.getID()
				+ '"' 
				+ ", " 
				+ '"'
				+ loan.getLoanName()  
				+ '"'
				+ ", " 
				+ '"' 
				+ loan.getLoanReason()
				+ '"' 
				+ ", " 
				+ '"' 
				+ loan.getCollateral()
				+ '"' 
				+ ", "  
				+ '"'
				+ loan.getAccountID()
				+ '"'
				+ ", " 
				+ '"' 
				+ loan.getBeginDate()
				+ '"' 
				+ ", " 
				+ '"' 
				+ loan.getEndDate()
				+ '"' 
				+ ", " 
				+ loan.getMoneyOwed()
				+ ", " 
				+ loan.getMoneyLoaned()
				+ ", " 
				+ loan.getMoneyReturned()
				+ ", " 
				+ loan.getInterestRate()
				+ ", " 
				+ loan.getStatus()
				+ ")";
		int rs = stmt.executeUpdate(sql);
		return Common.Success;
	}
	
}
