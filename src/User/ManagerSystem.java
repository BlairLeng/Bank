package User;

import java.sql.Connection;
import java.util.ArrayList;

import Account.CheckingAccount;
import Account.SavingAccount;

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
}
