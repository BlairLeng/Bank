package User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import TransactionSystem.Transaction;

public class ManagerSystemSQL {
	
	public static ResultSet allaccounts(Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM account";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}

	
	public static ResultSet alltrans(Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM trans";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public static ResultSet checkusertrans(Connection conn,String username) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM account,trans WHERE account.Username = "
				+"'"
				+username
				+"' AND (account.AccountID = trans.SenderID OR account.AccountID = trans.ReceiverID)";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}

}
