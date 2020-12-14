package StockSystem;

public class Accountstock {
	private String AccountID;
	private String StockID;
	private int amount;

	public Accountstock(String accountid, String stockid, int amount) {
		this.amount = amount;
		this.StockID = stockid;
		this.amount = amount;
	}

	public String getaccountid() {
		return this.AccountID;
	}

	public String getstockid() {
		return this.StockID;
	}

	public int getamount() {
		return this.amount;
	}
}
