package StockSystem;

import java.util.ArrayList;

public interface StockSystemFunctions {
	public ArrayList<Stock> viewAllStocks() throws Exception;

	public ArrayList<Stock> getStock(String stockid) throws Exception;

	public String changeStockPrice(String stockid, double price) throws Exception;

	public String addStock(String stockid, String stockname, double price) throws Exception;

	public String buyStock(String stockid, String accountid, int num) throws Exception;

	public String sellStock(String stockid, String accountid, int num) throws Exception;

	public ArrayList<Accountstock> viewAccountStocks(String accountid) throws Exception;

	public ArrayList<StockTrans> viewAccountStocksTrans(String accountid) throws Exception;
	
	public Double AccountBuyMoney(String accountid) throws Exception;
	
	public Double AccountSellMoney(String accountid) throws Exception;
	
	public Double AccountStockMoney(String accountid) throws Exception;

}
