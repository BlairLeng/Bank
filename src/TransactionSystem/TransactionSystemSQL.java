package TransactionSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionSystemSQL {
	public static ResultSet viewsaveaccounttrans(String accountid,Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM trans WHERE SenderID = "
				+"'"
				+accountid
				+"'"
				+"OR ReceiverID="
				+"'"
				+accountid
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public static ResultSet viewcheckaccounttrans(String accountid,Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM trans WHERE SenderID = "
				+"'"
				+accountid
				+"'"
				+"OR ReceiverID="
				+"'"
				+accountid
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public static ResultSet viewaccounttrans(String accountid,Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM trans WHERE SenderID = "
				+"'"
				+accountid
				+"'"
				+"OR ReceiverID="
				+"'"
				+accountid
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
}
