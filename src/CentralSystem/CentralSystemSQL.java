package CentralSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import AccountSystem.AccountSystemSQL;

public class CentralSystemSQL {
	
	public static ResultSet getAllAccounts(Connection con) throws SQLException {
		Statement stmt = con.createStatement();
		String sql = "SELECT * FROM account";
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}
	
//	public static String UpdateMoney(ArrayList<String> AccountIDList, HashMap<String, Integer> hash, Connection con) {
//		String sql = "UPDATE `account` "
//				+ "SET account.CurrentBalance = "
//				+ totalMoney
//				+ " "
//				+ "WHERE account.AccountID = "
//				+ '"'
//				+ t.getreceiverUUID()
//				+ '"';
//		Boolean rs1 = stmt.execute(sql);
//		return null;
//	}
	
}
