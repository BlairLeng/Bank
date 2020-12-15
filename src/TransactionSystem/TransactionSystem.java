package TransactionSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import AccountSystem.AccountSystemSQL;


public class TransactionSystem implements TransactionSystemFunctions{
	public Connection conn;
	
	public TransactionSystem(Connection conn) {
		this.conn=conn;
	}
	
	@Override
	public ArrayList<Transaction> SaveAccountTrans(String accountid) throws Exception{
		ArrayList<Transaction> al=new ArrayList<Transaction>();
		ResultSet rs=TransactionSystemSQL.viewsaveaccounttrans(accountid, conn);
		while(rs.next()) {
			LocalDate ld=rs.getDate("Datetime").toLocalDate();
			LocalTime lt=rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt=LocalDateTime.of(ld, lt);
			Transaction t=new Transaction(
					ldt, 
					rs.getDouble("Money"), 
					rs.getString("TransName"), 
					rs.getString("TransID"), 
					rs.getString("SenderID"), 
					rs.getString("ReceiverID")
					);
			al.add(t);
		}
		return al;
	}
	
	@Override
	public ArrayList<Transaction> CheckAccountTrans(String accountid) throws Exception{
		ArrayList<Transaction> al=new ArrayList<Transaction>();
		ResultSet rs=TransactionSystemSQL.viewcheckaccounttrans(accountid, conn);
		while(rs.next()) {
			LocalDate ld=rs.getDate("Datetime").toLocalDate();
			LocalTime lt=rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt=LocalDateTime.of(ld, lt);
			Transaction t=new Transaction(
					ldt, 
					rs.getDouble("Money"), 
					rs.getString("TransName"), 
					rs.getString("TransID"), 
					rs.getString("SenderID"), 
					rs.getString("ReceiverID")
					);
			al.add(t);
		}
		return al;
	}
	
	@Override
	public ArrayList<Transaction> AccountTrans(String accountid) throws Exception{
		ArrayList<Transaction> al=new ArrayList<Transaction>();
		ResultSet rs=TransactionSystemSQL.viewaccounttrans(accountid, conn);
		while(rs.next()) {
			String sid=rs.getString("SenderID");
			String rid=rs.getString("ReceiverID");
			ResultSet rs1=AccountSystemSQL.QueryAccountInformation(sid, conn);
			ResultSet rs2=AccountSystemSQL.QueryAccountInformation(rid, conn);
			rs1.next();
			rs2.next();
			LocalDate ld=rs.getDate("Datetime").toLocalDate();
			LocalTime lt=rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt=LocalDateTime.of(ld, lt);
			Transaction t=new Transaction(
					ldt, 
					rs.getDouble("Money"), 
					rs.getString("TransName"), 
					rs.getString("TransID"), 
					sid, 
					rid,
					rs1.getString("Username"),
					rs2.getString("Username")
					);
			al.add(t);
		}
		return al;
	}
}
