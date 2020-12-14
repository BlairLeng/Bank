package StockSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class StockTrans {
	private String TransID;
	private String TransName;
	private String StockID;
	private String AccountID;
	private double Price;
	private LocalDateTime dateTime;
	private int Amount;

	public StockTrans(String transname, String stockid, String accountid, double price, int amount) {
		this.TransID = UUID.randomUUID().toString();
		this.TransName = transname;
		this.StockID = stockid;
		this.AccountID = accountid;
		this.Price = price;
		this.dateTime = LocalDateTime.now();
		this.Amount = amount;
	}

	public StockTrans(String transid, String transname, String stockid, String accountid, double price,
			LocalDateTime datetime, int amount) {
		this.TransID = transid;
		this.TransName = transname;
		this.StockID = stockid;
		this.AccountID = accountid;
		this.Price = price;
		this.dateTime = datetime;
		this.Amount = amount;
	}

	public String gettransid() {
		return this.TransID;
	}

	public String gettransname() {
		return this.TransName;
	}

	public String getstockid() {
		return this.StockID;
	}

	public String getaccountid() {
		return this.AccountID;
	}

	public double getprice() {
		return this.Price;
	}

	public int getamount() {
		return this.Amount;
	}

	public String gettime() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedDate = this.dateTime.format(myFormatObj);
		return formattedDate;
	}
}
