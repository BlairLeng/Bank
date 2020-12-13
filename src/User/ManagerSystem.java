package User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import Account.Account;
import Account.CheckingAccount;
import Account.SavingAccount;
import Database.DatabaseConnection;
import TransactionSystem.Transaction;

public class ManagerSystem implements ManagerSystemFunctions{
	
	public Connection conn;
	
	public ManagerSystem(Connection conn) {
		this.conn=conn;
	}
	
	
	@Override
	public ArrayList<Transaction> Alltransactions() throws Exception{
		ArrayList<Transaction> al=new ArrayList<Transaction>();
		al=ManagerSystemSQL.alltransactions(conn);
		return al;
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
	
	public static void main(String[] args)throws Exception {
		Connection conn=DatabaseConnection.getConnection();
		ManagerSystem ms=new ManagerSystem(conn);
		System.out.print(ms.Allaccounts());
	}


	@Override
	public ArrayList<CheckingAccount> Allcheckaccounts() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<SavingAccount> Allsaveaccounts() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
