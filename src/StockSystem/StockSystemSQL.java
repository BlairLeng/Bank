package StockSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Common.Common;

public class StockSystemSQL {
	public static ResultSet viewallstocks(Connection conn)throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM stocks";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public static ResultSet getstock(Connection conn,String stockid) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM stocks WHERE StockID = "
				+"'"
				+stockid
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public static String changestockprice(Connection conn,String stockid, double price) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="UPDATE stocks SET Price = "
				+String.valueOf(price)
				+"WHERE StockID = "
				+"'"
				+stockid
				+"'";
		stmt.executeUpdate(sql);
		return Common.Success;
	}
	
	public static String addstock(Connection conn,String stockid,String stockname,double price) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="INSERT INTO stocks (StockID,StockName,Price) VALUES ("
				+'"'
				+stockid
				+'"'
				+","
				+'"'
				+stockname
				+'"'
				+","
				+price
				+")";
		stmt.executeUpdate(sql);
		return Common.Success;			
	}
	
	public static String buystock(Connection conn,StockTrans st) throws SQLException{
		Statement stmt1=conn.createStatement();
		Statement stmt2=conn.createStatement();
		Statement stmt3=conn.createStatement();
		Statement stmt4=conn.createStatement();
		Statement stmt5=conn.createStatement();
		Statement stmt6=conn.createStatement();
		String sql1="INSERT INTO stocktrans(TransID,TransName,StockID,AccountID,Price,Datetime,Amount) VALUES ("
				+'"'
				+st.gettransid()
				+'"'
				+","
				+'"'
				+st.gettransname()
				+'"'
				+","
				+'"'
				+st.getstockid()
				+'"'
				+","
				+'"'
				+st.getaccountid()
				+'"'
				+","
				+st.getprice()
				+","
				+'"'
				+st.gettime()
				+'"'
				+","
				+st.getamount()
				+")";
		String sql2="SELECT Amount FROM accountstock WHERE StockID = "
				+"'"
				+st.getstockid()
				+"' AND AccountID = "
				+"'"
				+st.getaccountid()
				+"'";
		String sql3="INSERT INTO accountstock(AccountID,StockID,Amount) VALUES ("
				+'"'
				+st.getaccountid()
				+'"'
				+","
				+'"'
				+st.getstockid()
				+'"'
				+","
				+st.getamount()
				+")";
		String sql5="SELECT CurrentBalance FROM account WHERE AccountID = "
				+"'"
				+st.getaccountid()
				+"'";
		ResultSet rs1=stmt1.executeQuery(sql5);
		rs1.next();
		if(rs1.getDouble("CurrentBalance") >= st.getamount() * st.getprice()) {
			String sql6="UPDATE account SET CurrentBalance = "
					+String.valueOf(rs1.getDouble("CurrentBalance")-st.getamount()*st.getprice())
					+" WHERE AccountID = "
					+"'"
					+st.getaccountid()
					+"'";
			stmt6.executeUpdate(sql6);
			stmt2.executeUpdate(sql1);
			ResultSet rs3=stmt3.executeQuery(sql2);
			if(rs3.next()) {
				String sql4="UPDATE accountstock SET Amount = "
						+String.valueOf(st.getamount() + rs3.getInt("Amount"))
						+" WHERE StockID = "
						+"'"
						+st.getstockid()
						+"' AND AccountID = "
						+"'"
						+st.getaccountid()
						+"'";
				stmt4.executeUpdate(sql4);
				return Common.Success;
			}else {
				stmt5.executeUpdate(sql3);
				return Common.Success;
			}
		}else
			return Common.NotEnoughMoney;
	}
	
	public static String sellstock(Connection conn,StockTrans st) throws SQLException{
		Statement stmt1=conn.createStatement();
		Statement stmt2=conn.createStatement();
		Statement stmt3=conn.createStatement();
		Statement stmt4=conn.createStatement();
		Statement stmt5=conn.createStatement();
		Statement stmt6=conn.createStatement();
		String sql1="INSERT INTO stocktrans(TransID,TransName,StockID,AccountID,Price,Datetime,Amount) VALUES ("
				+'"'
				+st.gettransid()
				+'"'
				+","
				+'"'
				+st.gettransname()
				+'"'
				+","
				+'"'
				+st.getstockid()
				+'"'
				+","
				+'"'
				+st.getaccountid()
				+'"'
				+","
				+st.getprice()
				+","
				+'"'
				+st.gettime()
				+'"'
				+","
				+st.getamount()
				+")";
		String sql2="SELECT Amount FROM accountstock WHERE StockID = "
				+"'"
				+st.getstockid()
				+"' AND AccountID = "
				+"'"
				+st.getaccountid()
				+"'";
		String sql3="SELECT CurrentBalance FROM account WHERE AccountID = "
				+"'"
				+st.getaccountid()
				+"'";
		ResultSet rs1=stmt1.executeQuery(sql2);
		rs1.next();
		if(rs1.getInt("Amount")>=st.getamount()) {
			stmt2.executeUpdate(sql1);
			ResultSet rs2=stmt3.executeQuery(sql3);
			rs2.next();
			String sql4="UPDATE account SET CurrentBalance = "
				+String.valueOf(st.getamount()*st.getprice()+rs2.getDouble("CurrentBalance"))
				+" WHERE AccountID = "
				+"'"
				+st.getaccountid()
				+"'";
			stmt4.executeUpdate(sql4);
			String sql5="UPDATE accountstock SET Amount = "
					+String.valueOf(rs1.getInt("Amount")-st.getamount())
					+" WHERE StockID = "
					+"'"
					+st.getstockid()
					+"' AND AccountID = "
					+"'"
					+st.getaccountid()
					+"'";
			stmt5.executeUpdate(sql5);
			String sql6="DELETE FROM accountstock WHERE Amount = 0";
			stmt6.executeUpdate(sql6);
			return Common.Success;
		}else
			return Common.NotEnoughStocks;
	}
	
	public static ResultSet viewaccountstocks(Connection conn,String accountid) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM accountstock WHERE AccountID = "
				+"'"
				+accountid
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public static ResultSet viewaccountstockstrans(Connection conn,String accountid) throws SQLException{
		Statement stmt=conn.createStatement();
		String sql="SELECT * FROM stocktrans WHERE AccountID = "
				+"'"
				+accountid
				+"'";
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
}
