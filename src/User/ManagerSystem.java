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
import CentralSystem.Transaction;
import Database.DatabaseConnection;

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
		ArrayList<ResultSet> ral=new ArrayList<ResultSet>();
		ral=ManagerSystemSQL.allaccounts(conn);
		Iterator<ResultSet> iterator=ral.iterator();
		while(iterator.hasNext()) {
			ResultSet rs=iterator.next();
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
	
}
