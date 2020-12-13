package User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import CentralSystem.Transaction;

public class ManagerSystemSQL {
	
	public static ArrayList<ResultSet> allaccounts(Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		ArrayList<ResultSet> aal=new ArrayList<ResultSet>();
		String sql="SELECT * FROM account";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()) {
			aal.add(rs);
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
