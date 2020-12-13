package Account;

import java.sql.Date;
import java.time.LocalDateTime;

public class SavingAccount extends Account{

	public SavingAccount(String name, double money) {
		super(name, money);
		// TODO Auto-generated constructor stub
		this.setType("SavingAccount");
	}

	public SavingAccount(String AccountID, String type, double money, LocalDateTime datetime) {
		super(AccountID, type, money, datetime);
	}
	
	public SavingAccount(Date dateTime,String name,double money,String uuid,String type) {
		super(dateTime, name, money, uuid, type);
	}
}
