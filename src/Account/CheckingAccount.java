package Account;

import java.time.LocalDateTime;
import java.util.UUID;

public class CheckingAccount extends Account{

	public CheckingAccount(String name, double money) {
		super(name, money);
		// TODO Auto-generated constructor stub
		this.setType("CheckingAccount");
	}
	
	public CheckingAccount(String AccountID, String type, double money, LocalDateTime datetime) {
		super(AccountID, type, money, datetime);
	}
	
	public CheckingAccount(LocalDateTime dateTime,String name,double money,String uuid,String type,String currencytype) {
		super(dateTime, name, money, uuid, type,currencytype);
	}
	
}
