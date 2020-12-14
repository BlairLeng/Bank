package Account;

import java.time.LocalDateTime;
import java.util.UUID;

public class CheckingAccount extends Account{

	public CheckingAccount(String name, double money, String currencyType) {
		super(name, money, currencyType);
		// TODO Auto-generated constructor stub
		this.setType("CheckingAccount");
	}
	
	public CheckingAccount(String AccountID, String type, double money, LocalDateTime datetime, String currencyType) {
		super(AccountID, type, money, datetime, currencyType);
	}
	
	public CheckingAccount(LocalDateTime dateTime,String name,double money,String uuid,String type,String currencytype) {
		super(dateTime, name, money, uuid, type,currencytype);
	}
	
}
