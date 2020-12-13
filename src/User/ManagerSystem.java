package User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import Account.Account;
import Account.CheckingAccount;
import Account.SavingAccount;
import TransactionSystem.Transaction;
import Database.DatabaseConnection;
import Database.DatabaseTables;

public class ManagerSystem implements ManagerSystemFunctions{
	
	public Connection conn;
	
	public ManagerSystem(Connection conn) {
		this.conn=conn;
	}
	
	
	@Override
	public ArrayList<Account> Allaccounts() throws Exception{
		ArrayList<Account> al=new ArrayList<Account>();
		ResultSet rs=ManagerSystemSQL.allaccounts(conn);
		while(rs.next()) {
			if (rs.getString("Type").equals("SavingAccount")) {
				LocalDate ld = rs.getDate("CreateTime").toLocalDate();
				LocalTime lt = rs.getTime("CreateTime").toLocalTime();
				LocalDateTime ldt = LocalDateTime.of(ld, lt);
				SavingAccount sa = new SavingAccount(
						ldt,
						rs.getString("Username"),
						rs.getDouble("currentbalance"),
						rs.getString("AccountID"),
						rs.getString("Type")
						);
				al.add(sa);
			}
			if (rs.getString("Type").equals("CheckingAccount")) {
				LocalDate ld = rs.getDate("CreateTime").toLocalDate();
				LocalTime lt = rs.getTime("CreateTime").toLocalTime();
				LocalDateTime ldt = LocalDateTime.of(ld, lt);
				CheckingAccount ca = new CheckingAccount(
						ldt,
						rs.getString("Username"),
						rs.getDouble("currentbalance"),
						rs.getString("AccountID"),
						rs.getString("Type")
						);
				al.add(ca);
			}
			
		}
		return al;
	}
	
	@Override
	public ArrayList<Transaction> Alltrans() throws Exception{
		ArrayList<Transaction> al=new ArrayList<Transaction>();
		ResultSet rs=ManagerSystemSQL.alltrans(conn);
		while(rs.next()) {
			LocalDate ld=rs.getDate("Datetime").toLocalDate();
			LocalTime lt=rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt=LocalDateTime.of(ld, lt);
			Transaction t=new Transaction(
					ldt, 
					rs.getDouble("Money"), 
					rs.getString("TransName"), 
					rs.getString("TransID"), 
					rs.getString("SenderID"), 
					rs.getString("ReceiverID")
					);
			al.add(t);
		}
		return al;
	}
	
	@Override
	public ArrayList<Transaction> Usertrans(String username) throws Exception{
		ArrayList<Transaction> al=new ArrayList<Transaction>();
		ResultSet rs=ManagerSystemSQL.checkusertrans(conn, username);
		while(rs.next()) {
			LocalDate ld=rs.getDate("Datetime").toLocalDate();
			LocalTime lt=rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt=LocalDateTime.of(ld, lt);
			Transaction t=new Transaction(
					ldt, 
					rs.getDouble("Money"), 
					rs.getString("TransName"), 
					rs.getString("TransID"), 
					rs.getString("SenderID"), 
					rs.getString("ReceiverID")
					);
			al.add(t);
		}
		return al;
	}
	
	
	/*
	public static void main(String[] args)throws Exception {
		Connection conn=DatabaseConnection.getConnection();
		Statement stmt=conn.createStatement();
		ManagerSystem ms=new ManagerSystem(conn);
		System.out.print(ms.Usertrans("111").get(1).gettransname());
	}
	*/
	
}
