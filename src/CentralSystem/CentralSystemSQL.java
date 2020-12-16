package CentralSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import AccountSystem.AccountSystemSQL;
import Common.Common;

public class CentralSystemSQL {
	
	public static ResultSet getAllAccounts(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
	public static String UpdateMoney(ArrayList<String> AccountIDList, HashMap<String, Integer> hash, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		for (int i = 0; i < AccountIDList.size(); i++) {	
			String tempAccId = AccountIDList.get(i);
			double currentMoney = AccountSystemSQL.getMoney(tempAccId, con);
			double totalMoney = currentMoney + currentMoney * Math.pow(Common.SavingInterestRate, hash.get(tempAccId));
			String sql = "UPDATE `account` "
					+ "SET account.CurrentBalance = "
					+ totalMoney
					+ " "
					+ "WHERE account.AccountID = "
					+ '"'
					+ tempAccId
					+ '"';
			stmt.executeUpdate(sql);
		}
		return Common.Success;
	}
	
	public static String UpdateLastTime(String ldt, Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "UPDATE `account` "
				+ "SET account.LastTime = "
				+ '"'
				+ ldt
				+ '"'
				+ " "
				+ "WHERE"
				+ " "
				+ "AccountID != '1'";
		stmt.execute(sql);
//		ResultSet rs = stmt.executeQuery(sql);
		return Common.Success;
	}
}
