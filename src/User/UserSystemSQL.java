package User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Account.CheckingAccount;
import Account.SavingAccount;
import Common.Common;

public class UserSystemSQL {

	
	public static String CreateSavingAccount(SavingAccount newAccount, Connection con) throws SQLException {
		Statement stmt=con.createStatement();
		String sql = "INSERT INTO `account` "
				+ "(`AccountID`,`Username`,`Type`,`CurrencyType`,`CurrentBalance`,`CreateTime`,`LastTime`,`BeginMoney`) "
				+ "VALUES (" 
				+ '"' 
				+ newAccount.getUUID()
				+ '"' 
				+ ", " 
				+ '"' 
				+ newAccount.getCustomerName()
				+ '"' 
				+ ", " 
				+ '"'
				+ newAccount.getType()
				+ '"'
				+ ", " 
				+ '"'
				+ newAccount.getCurrencyType()
				+ '"'
				+ ", "  
				+ newAccount.getCurrentBalance()
				+ ", "
				+ '"' 
				+ newAccount.getOpenTime()
				+ '"' 
				+ ", "
				+ '"' 
				+ newAccount.getOpenTime()
				+ '"' 
				+ ", " 
				+ newAccount.getCurrentBalance()
				+ ")";
		stmt.executeUpdate(sql);
		return Common.Success;
	}

	public static String CreateCheckingAccount(CheckingAccount newAccount, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		Statement stmt=con.createStatement();
		String sql = "INSERT INTO `account` "
				+ "(`AccountID`,`Username`,`Type`,`CurrencyType`,`CurrentBalance`,`CreateTime`,`LastTime`,`BeginMoney`) "
				+ "VALUES (" 
				+ '"' 
				+ newAccount.getUUID()
				+ '"' 
				+ ", " 
				+ '"' 
				+ newAccount.getCustomerName()
				+ '"' 
				+ ", " 
				+ '"'
				+ newAccount.getType()
				+ '"'
				+ ", " 
				+ '"'
				+ newAccount.getCurrencyType()
				+ '"'
				+ ", "  
				+ newAccount.getCurrentBalance()
				+ ", "
				+ '"' 
				+ newAccount.getOpenTime()
				+ '"' 
				+ ", "
				+ '"' 
				+ newAccount.getOpenTime()
				+ '"' 
				+ ", " 
				+ newAccount.getCurrentBalance()
				+ ")";
		stmt.executeUpdate(sql);
		return Common.Success;
	}
	
	public static ResultSet ViewAccounts(String username, Connection con) throws SQLException {
		Statement stmt=con.createStatement();
		String sql = "SELECT * FROM account WHERE username = "
					+ "'"
					+ username
					+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	
}
