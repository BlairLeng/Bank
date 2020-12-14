package StockSystem;

import java.util.UUID;

public class Stock {
	private UUID StockID;
	private String StockidString;
	private String StockName;
	private double Price;

	public Stock(String name, double price) {
		this.StockName = name;
		this.Price = price;
		this.StockID = UUID.randomUUID();
		this.StockidString = this.StockID.toString();
	}

	public Stock(String stockid, String name, double price) {
		this.StockidString = stockid;
		this.StockName = name;
		this.Price = price;
	}

	public String getStockID() {
		return this.StockidString;
	}

	public String getStockName() {
		return this.StockName;
	}

	public double getPrice() {
		return this.Price;
	}
}
