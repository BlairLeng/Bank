package CentralSystem;

import java.util.UUID;
import java.time.LocalDateTime;

public class Loan {
	private UUID loanID;
	private String AccountID;
	private LocalDateTime beginTime;
	private LocalDateTime endTime;
	private double moneyLoaned;
	private double moneyReturned;
	private double interestRate;
	private int Status;
	
	public Loan(String AccountID, double moneyLoaned, int daysLoaned) {
		this.AccountID = AccountID;
		this.moneyLoaned = moneyLoaned;
		this.beginTime = LocalDateTime.now();
		this.endTime = this.beginTime.plusDays(daysLoaned);
		this.loanID = UUID.randomUUID();
		this.moneyReturned = 0;
		this.Status = 0;
	}
	
}
