package Account;
import java.util.UUID;

import CentralSystem.Transaction;
import Common.Common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account implements GeneralAccountFunctions{
	private LocalDateTime dateTime;
	private String name;
	private String password; 
	private double money;
	private UUID uuid;
	private Transaction[] transactionHistory;
	
	public Account(String name, double money, String password){
		this.name = name;
		this.money = money;
		this.password = password;
		this.dateTime = LocalDateTime.now();
		this.uuid = UUID.randomUUID();
	}
	
	public String getOpenTime() {
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
	    String formattedDate = dateTime.format(myFormatObj);
	    return formattedDate;
	}
	
	public String getCustomerName() {
		return this.name;
	}
	
	public String getUUID() {
		return this.uuid.toString();
	}
	
	public double getCurrentBalance() {
		return this.money;
	}
	
	public String RequestLoan() {
		return Common.Success;
	}
	
	public Transaction[] getTransactionHistory() {
		return this.transactionHistory;
	}
	
	public String Deposit(double money) {
		this.money += money;
		return Common.Success;
	}
	
	public String Withdraw(double money) {
		if (money > this.money) {
			return Common.NotEnoughMoney;
		}
		this.money -= money;
		return Common.Success;
	}
}
