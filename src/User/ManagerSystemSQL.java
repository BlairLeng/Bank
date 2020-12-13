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
