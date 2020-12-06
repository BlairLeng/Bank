package User;

import java.util.ArrayList;

import Account.*;

public class UserSystem implements UserSystemFunctions{
	
	private ArrayList<String> AccountList;
	
	public UserSystem(ArrayList<String> AccountList) {
		this.AccountList = AccountList;
	}

	@Override
	public String CreateSavingAccount(String username, double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String CreateCheckingAccount(String username, double money) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Account> ViewAccounts(String username) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	
}
