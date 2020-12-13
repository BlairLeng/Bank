package User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Account.Account;
import Account.CheckingAccount;
import Account.SavingAccount;
import CentralSystem.Transaction;

public class ManagerSystemSQL {

	public static ArrayList<CheckingAccount> allcheckaccounts(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ArrayList<CheckingAccount> cal = new ArrayList<CheckingAccount>();
		String sql = "SELECT * FROM account WHERE Type='CheckingAccount'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			CheckingAccount ca = new CheckingAccount(rs.getDate("CreateTime"), rs.getString("Username"),
					rs.getDouble("CurrentBalance"), rs.getString("AccountID"), rs.getString("Type"));
			cal.add(ca);
		}
		return cal;
	}

	public static ArrayList<SavingAccount> allsaveaccounts(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ArrayList<SavingAccount> sal = new ArrayList<SavingAccount>();
		String sql = "SELECT * FROM account WHERE Type='SavingAccount'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			SavingAccount ca = new SavingAccount(rs.getDate("CreateTime"), rs.getString("Username"),
					rs.getDouble("CurrentBalance"), rs.getString("AccountID"), rs.getString("Type"));
			sal.add(ca);
		}
		return sal;
	}
	
	public static ArrayList<Account> allaccounts(Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		ArrayList<Account> aal=new ArrayList<Account>();
		String sql="SELECT * FROM account";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()) {
			CheckingAccount a=new CheckingAccount(rs.getDate("CreateTime"), rs.getString("Username"),
					rs.getDouble("CurrentBalance"), rs.getString("AccountID"), rs.getString("Type"));
			aal.add(a);
		}
		return aal;
	}

	public static ArrayList<Transaction> alltransactions(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		ArrayList<Transaction> tal = new ArrayList<Transaction>();
		String sql = "SELECT * FROM transaction";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			Transaction t = new Transaction(rs.getDate(""), rs.getString(""), rs.getString(""), rs.getString(""),
					rs.getString(""), rs.getDouble(""));
			tal.add(t);
		}
		return tal;
	}

}
