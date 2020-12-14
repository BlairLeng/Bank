package User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;


import Common.Common;
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
	
	public static ResultSet getdaytrans(Connection conn,LocalDate date) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM trans WHERE trans.Datetime > "
				+"'"
				+date
				+"' AND trans.Datetime < "
				+"'"
				+date.plusDays(1)
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	//return all loans
	public static ResultSet allloans(Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM loan";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	//return loans which is not apporoved
	public static ResultSet getualoans(Connection conn) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM loan WHERE Status = -1";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	//approve loan
	public static String approveloan(Connection conn,String loanid) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="UPDATE loan SET Status = 0 WHERE loan.LoanID = "
				+"'"
				+loanid
				+"'";
		stmt.executeUpdate(sql);
		return Common.Success;
	}
	
	//return certain day loans
	public static ResultSet getdayloans(Connection conn,LocalDate date) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM loan WHERE loan.BeginDatetime > "
				+"'"
				+date
				+"' AND loan.BeginDatetime < "
				+"'"
				+date.plusDays(1)
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	//return loans that finish on certain day
	public static ResultSet getdayreloans(Connection conn,LocalDate date) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM loan WHERE loan.EndDatetime > "
				+"'"
				+date
				+"' AND loan.EndDatetime < "
				+"'"
				+date.plusDays(1)
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}

}
