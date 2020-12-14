package Account;
import java.util.UUID;

import Common.Common;
import TransactionSystem.Transaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Account implements GeneralAccountFunctions{
	private LocalDateTime dateTime;
	private String name;
	private double money;
	private UUID uuid;
	private String uuidString;
	private String type;
	private String currencyType;
	
	public Account(String name, double money, String currencyType){
		this.name = name;
		this.money = money;
		this.dateTime = LocalDateTime.now();
		this.uuid = UUID.randomUUID();
		this.uuidString = this.uuid.toString();
		this.currencyType = currencyType;
	}
	
	public Account(String AccountID, String type, double balance, LocalDateTime dateTime, String currencyType, String username) {
		this.uuidString = AccountID;
		this.type = type;
		this.money = balance;
		this.dateTime = dateTime;
		this.currencyType = currencyType;
		this.name = username;
	}
	
	public Account(LocalDateTime dateTime,String name,double money,String uuid,String type,String currencytype) {
		this.name=name;
		this.money=money;
		this.dateTime=dateTime;
		this.uuidString=uuid;
		this.type=type;
		this.currencyType=currencytype;
	}
	
	protected String setType(String type) {
		return this.type = type;
	}
	
	public String getCurrencyType() {
		return this.currencyType;
	}
	
	public String getOpenTime() {
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = dateTime.format(myFormatObj);
	    return formattedDate;
	}
	
	public String getCurrentTime() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = LocalDateTime.now().format(myFormatObj);
	    return formattedDate;
	}
	
	public String getCustomerName() {
		return this.name;
	}
	
	public String getUUID() {
		return this.uuidString;
	}

	public String getType() {
		return this.type;
	}
	
	public double getCurrentBalance() {
		return this.money;
	}
	
	public String RequestLoan() {
		return Common.Success;
	}
	
	public String getTransactionHistory() {
		return Common.Success;
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
	
	public String toString() {
		return "\n" + this.uuidString + "\n" + this.getType() + "\n" + this.getCurrentBalance() + "\n" + this.getOpenTime() + "\n";
	}
	
}
