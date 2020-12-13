package Account;

import java.time.LocalDateTime;

public class CheckingAccount extends Account{

	public CheckingAccount(String name, double money) {
		super(name, money);
		// TODO Auto-generated constructor stub
		this.setType("CheckingAccount");
	}
	
}
