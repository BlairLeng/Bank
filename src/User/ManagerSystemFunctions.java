package User;

import java.util.ArrayList;

import Account.CheckingAccount;
import Account.SavingAccount;

public interface ManagerSystemFunctions {
	public ArrayList<CheckingAccount> Allcheckaccounts() throws Exception;
	
	public ArrayList<SavingAccount> Allsaveaccounts() throws Exception;

}
