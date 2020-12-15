package LoanSystem;

import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Loan {
	private UUID loanID;
	private String AccountID;
	private String LoanName;
	private String LoanReason;
	private String Collateral;
	private LocalDateTime beginTime;
	private LocalDateTime endTime;
	private double moneyLoaned;
	private double moneyReturned;
	private double moneyOwed;
	private double interestRate;
	private int Status;

	public Loan(String AccountID, double moneyOwed, int daysLoaned, double interestRate,
			String loanName, String loanReason, String collateral) {
		this.AccountID = AccountID;
		this.moneyLoaned = moneyLoaned * Math.pow(interestRate, daysLoaned);
		this.interestRate = interestRate;
		this.moneyOwed = moneyOwed;
		this.LoanName = loanName;
		this.LoanReason = loanReason;
		this.beginTime = LocalDateTime.now();
		this.endTime = this.beginTime.plusDays(daysLoaned);
		this.loanID = UUID.randomUUID();
		this.moneyReturned = 0;
		this.Status = 0;
		this.Collateral = collateral;
	}

	public Loan(String AccountID, String LoanName, String LoanReason, String Collateral, LocalDateTime beginTime,
			LocalDateTime endTime, double moneyLoaned, double moneyReturned, double moneyOwed, double interestRate,
			int Status) {
		this.AccountID = AccountID;
		this.LoanName = LoanName;
		this.LoanReason = LoanReason;
		this.Collateral = Collateral;
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.moneyLoaned = moneyLoaned;
		this.moneyReturned = moneyReturned;
		this.moneyOwed = moneyOwed;
		this.interestRate = interestRate;
		this.Status = Status;
	}
	
	public String getID() {
		return this.loanID.toString();
	}
	
	public String getLoanName() {
		return this.LoanName;
	}
	
	public String getLoanReason() {
		return this.LoanReason;
	}
	
	public String getCollateral() {
		return this.Collateral;
	}
	
	public String getAccountID() {
		return this.AccountID;
	}
	
	public String getBeginDate() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = this.beginTime.format(myFormatObj);
	    return formattedDate;
	}
	
	public String getEndDate() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = this.endTime.format(myFormatObj);
	    return formattedDate;
	}
	
	public double getMoneyOwed() {
		return this.moneyOwed;
	}
	
	public double getMoneyLoaned() {
		return this.moneyLoaned;
	}
	
	public double getMoneyReturned() {
		return this.moneyReturned;
	}
	
	public double getInterestRate() {
		return this.interestRate;
	}
	
	public int getStatus() {
		return this.Status;
	}
}
