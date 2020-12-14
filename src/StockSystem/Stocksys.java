package StockSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import Common.Common;
import Database.DatabaseConnection;
import Database.DatabaseTables;

public class Stocksys implements StockSystemFunctions {

	public Connection conn;

	public Stocksys(Connection conn) {
		this.conn = conn;
	}

	@Override
	public ArrayList<Stock> viewAllStocks() throws Exception {
		ArrayList<Stock> al = new ArrayList<Stock>();
		ResultSet rs = StockSystemSQL.viewallstocks(conn);
		while (rs.next()) {
			Stock s = new Stock(rs.getString("StockID"), rs.getString("StockName"), rs.getDouble("Price"));
			al.add(s);
		}
		return al;
	}

	@Override
	public ArrayList<Stock> getStock(String stockid) throws Exception {
		ArrayList<Stock> al = new ArrayList<Stock>();
		ResultSet rs = StockSystemSQL.getstock(conn, stockid);
		while (rs.next()) {
			Stock s = new Stock(rs.getString("StockID"), rs.getString("StockName"), rs.getDouble("Price"));
			al.add(s);
		}
		return al;
	}

	@Override
	public String changeStockPrice(String stockid, double price) throws Exception {
		return StockSystemSQL.changestockprice(conn, stockid, price);
	}

	@Override
	public String addStock(String stockid, String stockname, double price) throws Exception {
		return StockSystemSQL.addstock(conn, stockid, stockname, price);
	}

	@Override
	public String buyStock(String stockid, String accountid, int num) throws Exception {
		ResultSet rs = StockSystemSQL.getstock(conn, stockid);
		rs.next();
		StockTrans st = new StockTrans(Common.StockTrans_Buy, stockid, accountid, rs.getInt("Price"), num);
		return StockSystemSQL.buystock(conn, st);
	}

	@Override
	public String sellStock(String stockid, String accountid, int num) throws Exception {
		ResultSet rs = StockSystemSQL.getstock(conn, stockid);
		rs.next();
		StockTrans st = new StockTrans(Common.StockTrans_Sell, stockid, accountid, rs.getInt("Price"), num);
		return StockSystemSQL.sellstock(conn, st);
	}

	@Override
	public ArrayList<Accountstock> viewAccountStocks(String accountid) throws Exception {
		ArrayList<Accountstock> al = new ArrayList<Accountstock>();
		ResultSet rs = StockSystemSQL.viewaccountstocks(conn, accountid);
		while (rs.next()) {
			Accountstock s = new Accountstock(rs.getString("AccountID"), rs.getString("StockID"), rs.getInt("Amount"));
			al.add(s);
		}
		return al;
	}

	@Override
	public ArrayList<StockTrans> viewAccountStocksTrans(String accountid) throws Exception {
		ArrayList<StockTrans> al = new ArrayList<StockTrans>();
		ResultSet rs = StockSystemSQL.viewaccountstockstrans(conn, accountid);
		while (rs.next()) {
			LocalDate ld = rs.getDate("Datetime").toLocalDate();
			LocalTime lt = rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt = LocalDateTime.of(ld, lt);
			StockTrans st = new StockTrans(rs.getString("TransID"), rs.getString("TransName"), rs.getString("StockID"),
					rs.getString("AccountID"), rs.getDouble("Price"), ldt, rs.getInt("Amount"));
			al.add(st);
		}
		return al;
	}

}
