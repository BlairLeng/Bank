package User;

import java.sql.Connection;

import java.util.ArrayList;

import Account.*;
import User.UserSystemSQL; 
import Common.Common;

public class UserSystem implements UserSystemFunctions{
	
	public Connection con;
	
	public UserSystem(Connection con) {
		this.con = con;
	}

	@Override
	public String CreateSavingAccount(String username, double money) throws Exception {
		// TODO Auto-generated method stub
		SavingAccount newAccount = new SavingAccount(username, money);
		UserSystemSQL.CreateSavingAccount(newAccount, con);
		return Common.Success;
	}

	@Override
	public String CreateCheckingAccount(String username, double money) throws Exception {
		// TODO Auto-generated method stub
		CheckingAccount newAccount = new CheckingAccount(username, money);
		UserSystemSQL.CreateCheckingAccount(newAccount, con);
		return Common.Success;
	}

	@Override
	public ArrayList<Account> ViewAccounts(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
