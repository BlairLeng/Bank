package TransactionSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

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
}
