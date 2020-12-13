package User;

import java.sql.Connection;
import java.util.ArrayList;

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
	public ArrayList<CheckingAccount> Allcheckaccounts() throws Exception{
		ArrayList<CheckingAccount> al=new ArrayList<CheckingAccount>();
		al=ManagerSystemSQL.allcheckaccounts(conn);
		return al;
	}
	
	@Override
	public ArrayList<SavingAccount> Allsaveaccounts() throws Exception{
		ArrayList<SavingAccount> al=new ArrayList<SavingAccount>();
		al=ManagerSystemSQL.allsaveaccounts(conn);
		return al;
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
		al=ManagerSystemSQL.allaccounts(conn);
		return al;
	}
}
