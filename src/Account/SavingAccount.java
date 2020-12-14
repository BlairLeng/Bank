package Account;

import java.time.LocalDateTime;
import java.util.UUID;

public class SavingAccount extends Account{

	public SavingAccount(String name, double money, String currencyType) {
		super(name, money, currencyType);
		// TODO Auto-generated constructor stub
		this.setType("SavingAccount");
	}

	public SavingAccount(String AccountID, String type, double money, LocalDateTime datetime, String currencyType) {
		super(AccountID, type, money, datetime, currencyType);
	}
	
	public SavingAccount(LocalDateTime dateTime,String name,double money,String uuid,String type,String currencytype) {
		super(dateTime, name, money, uuid, type,currencytype);
	}
}
