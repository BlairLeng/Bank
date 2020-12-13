package LoanSystem;

import java.util.UUID;
import java.time.LocalDateTime;

public class Loan {
	private UUID loanID;
	private String AccountID;
	private String LoanName;
	private String LoanReason;
	private LocalDateTime beginTime;
	private LocalDateTime endTime;
	private double moneyLoaned;
	private double moneyReturned;
	private double moneyOwed;
	private double interestRate;
	private int Status;
	
	public Loan(String AccountID, double moneyLoaned, double moneyOwed, int daysLoaned, double interestRate, String loanName, String loanReason) {
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
	}
	
}
