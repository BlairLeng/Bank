package TransactionSystem;

import java.util.ArrayList;

public interface TransactionSystemFunctions {
	public ArrayList<Transaction> SaveAccountTrans(String accountid) throws Exception;
	
	public ArrayList<Transaction> CheckAccountTrans(String accountid) throws Exception;
}
