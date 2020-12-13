package User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;

import Account.Account;
import Account.CheckingAccount;
import Account.SavingAccount;
import TransactionSystem.Transaction;
import Database.DatabaseConnection;
import Database.DatabaseTables;
import LoanSystem.Loan;

public class ManagerSystem implements ManagerSystemFunctions {

	public Connection conn;

	public ManagerSystem(Connection conn) {
		this.conn = conn;
	}

	@Override
	public ArrayList<Account> Allaccounts() throws Exception {
		ArrayList<Account> al = new ArrayList<Account>();
		ResultSet rs = ManagerSystemSQL.allaccounts(conn);
		while (rs.next()) {
			if (rs.getString("Type").equals("SavingAccount")) {
				LocalDate ld = rs.getDate("CreateTime").toLocalDate();
				LocalTime lt = rs.getTime("CreateTime").toLocalTime();
				LocalDateTime ldt = LocalDateTime.of(ld, lt);
				SavingAccount sa = new SavingAccount(ldt, rs.getString("Username"), rs.getDouble("currentbalance"),
						rs.getString("AccountID"), rs.getString("Type"));
				al.add(sa);
			}
			if (rs.getString("Type").equals("CheckingAccount")) {
				LocalDate ld = rs.getDate("CreateTime").toLocalDate();
				LocalTime lt = rs.getTime("CreateTime").toLocalTime();
				LocalDateTime ldt = LocalDateTime.of(ld, lt);
				CheckingAccount ca = new CheckingAccount(ldt, rs.getString("Username"), rs.getDouble("currentbalance"),
						rs.getString("AccountID"), rs.getString("Type"));
				al.add(ca);
			}

		}
		return al;
	}

	@Override
	public ArrayList<Transaction> Alltrans() throws Exception {
		ArrayList<Transaction> al = new ArrayList<Transaction>();
		ResultSet rs = ManagerSystemSQL.alltrans(conn);
		while (rs.next()) {
			LocalDate ld = rs.getDate("Datetime").toLocalDate();
			LocalTime lt = rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt = LocalDateTime.of(ld, lt);
			Transaction t = new Transaction(ldt, rs.getDouble("Money"), rs.getString("TransName"),
					rs.getString("TransID"), rs.getString("SenderID"), rs.getString("ReceiverID"));
			al.add(t);
		}
		return al;
	}

	@Override
	public ArrayList<Transaction> Usertrans(String username) throws Exception {
		ArrayList<Transaction> al = new ArrayList<Transaction>();
		ResultSet rs = ManagerSystemSQL.checkusertrans(conn, username);
		while (rs.next()) {
			LocalDate ld = rs.getDate("Datetime").toLocalDate();
			LocalTime lt = rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt = LocalDateTime.of(ld, lt);
			Transaction t = new Transaction(ldt, rs.getDouble("Money"), rs.getString("TransName"),
					rs.getString("TransID"), rs.getString("SenderID"), rs.getString("ReceiverID"));
			al.add(t);
		}
		return al;
	}

	@Override
	public ArrayList<Transaction> GetDaytrans(LocalDate date) throws Exception {
		ArrayList<Transaction> al = new ArrayList<Transaction>();
		ResultSet rs = ManagerSystemSQL.getdaytrans(conn, date);
		while (rs.next()) {
			LocalDate ld = rs.getDate("Datetime").toLocalDate();
			LocalTime lt = rs.getTime("Datetime").toLocalTime();
			LocalDateTime ldt = LocalDateTime.of(ld, lt);
			Transaction t = new Transaction(ldt, rs.getDouble("Money"), rs.getString("TransName"),
					rs.getString("TransID"), rs.getString("SenderID"), rs.getString("ReceiverID"));
			al.add(t);
		}
		return al;
	}

	@Override
	public ArrayList<String> GetDayReport(LocalDate date) throws Exception {
		ArrayList<String> al = new ArrayList<String>();
		ResultSet rs = ManagerSystemSQL.getdaytrans(conn, date);
		al.add("Transaction report:");
		double totaltransmoney = 0, totalwithdrawmoney = 0, totaldepositmoney = 0, totalloanmoney = 0,
				totalrecallmoney = 0;
		int totaltranstime = 0, totalwithdrawtime = 0, totaldeposittime = 0, totalloantime = 0, totalrecalltime = 0;
		while (rs.next()) {
			if (rs.getString("SenderID").equals(rs.getString("ReceiverID"))) {
				double money = rs.getDouble("Money");
				if (money < 0) {
					totalwithdrawmoney -= money;
					totalwithdrawtime++;
				} else {
					totaldepositmoney += money;
					totaldeposittime++;
				}
			} else {
				totaltransmoney += rs.getDouble("Money");
				totaltranstime++;
			}

			if (rs.getString("SenderID") == null) {
				totalloanmoney += rs.getDouble("Money");
				totalloantime++;
			}

			if (rs.getString("ReceiverID") == null) {
				totalrecallmoney += rs.getDouble("Money");
				totalrecalltime++;
			}
		}
		al.add("Total transaction money:" + String.valueOf(totaltransmoney));
		al.add("Total transaction time:" + String.valueOf(totaltranstime));
		al.add("Total withdraw money:" + String.valueOf(totalwithdrawmoney));
		al.add("Total withdraw time:" + String.valueOf(totalwithdrawtime));
		al.add("Total deposit money:" + String.valueOf(totaldepositmoney));
		al.add("Total deposit time:" + String.valueOf(totaldeposittime));
		al.add("Total loan money:"+String.valueOf(totalloanmoney));
		al.add("Total loan time:"+String.valueOf(totalloantime));
		al.add("Total recall money:"+String.valueOf(totalrecallmoney));
		al.add("Total recall time:"+String.valueOf(totalrecalltime));

		// get loan data
		/*
		 * ResultSet lrs = ManagerSystemSQL.getdayloans(conn, date);
		 * al.add("Loan report:"); double totalloanmoney = 0,totalrecallmoney=0; int
		 * totalloantime = 0; while (lrs.next()) {
		 * totalloanmoney+=lrs.getDouble("MoneyLoaned"); totalloantime++; }
		 * al.add("Total loan money:"+String.valueOf(totalloanmoney));
		 * al.add("Total loan time:"+String.valueOf(totalloantime));
		 * 
		 * //get recall loan data ResultSet rrs=ManagerSystemSQL.getdayreloans(conn,
		 * date); while(rrs.next()) {
		 * 
		 * } al.add("Total recall loan money:"+String.valueOf(totalrecallmoney));
		 */

		return al;
	}

	@Override
	public String ApproveLoan(String loanid) throws Exception {
		return ManagerSystemSQL.approveloan(conn, loanid);
	}

	@Override
	public ArrayList<Loan> Allloans() throws Exception {
		ArrayList<Loan> al = new ArrayList<Loan>();
		ResultSet rs = ManagerSystemSQL.allloans(conn);
		while (rs.next()) {

		}
		return al;
	}

	@Override
	public ArrayList<Loan> UnapprovedLoan() throws Exception {
		ArrayList<Loan> al = new ArrayList<Loan>();
		ResultSet rs = ManagerSystemSQL.getualoans(conn);
		while (rs.next()) {

		}
		return al;
	}
	/*
	 * public static void main(String[] args)throws Exception { Connection
	 * conn=DatabaseConnection.getConnection(); Statement
	 * stmt=conn.createStatement(); ManagerSystem ms=new ManagerSystem(conn);
	 * System.out.print(ms.Usertrans("111").get(1).gettransname()); }
	 */

}
