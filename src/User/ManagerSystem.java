package User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;

import Account.Account;
import Account.CheckingAccount;
import Account.SavingAccount;
import Common.Common;
import TransactionSystem.Transaction;
import Database.DatabaseConnection;
import Database.DatabaseTables;
import LoanSystem.Loan;

public class ManagerSystem implements ManagerSystemFunctions {

	public Connection conn;

	public ManagerSystem(Connection conn) {
		this.conn = conn;
	}

	public LocalDateTime getlocaldatetime(ResultSet rs, String s) throws Exception {
		LocalDate ld = rs.getDate(s).toLocalDate();
		LocalTime lt = rs.getTime(s).toLocalTime();
		LocalDateTime ldt = LocalDateTime.of(ld, lt);
		return ldt;
	}

	@Override
	public ArrayList<Account> Allaccounts() throws Exception {
		ArrayList<Account> al = new ArrayList<Account>();
		ResultSet rs = ManagerSystemSQL.allaccounts(conn);
		while (rs.next()) {
			if (rs.getString("Type").equals("SavingAccount")) {
				LocalDateTime ldt = getlocaldatetime(rs, "CreateTime");
				SavingAccount sa = new SavingAccount(ldt, rs.getString("Username"), rs.getDouble("currentbalance"),
						rs.getString("AccountID"), rs.getString("Type"), rs.getString("CurrencyType"));
				al.add(sa);
			}
			if (rs.getString("Type").equals("CheckingAccount")) {
				LocalDateTime ldt = getlocaldatetime(rs, "CreateTime");
				CheckingAccount ca = new CheckingAccount(ldt, rs.getString("Username"), rs.getDouble("currentbalance"),
						rs.getString("AccountID"), rs.getString("Type"), rs.getString("CurrencyType"));
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
			LocalDateTime ldt = getlocaldatetime(rs, "Datetime");
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
			LocalDateTime ldt = getlocaldatetime(rs, "Datetime");
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
			LocalDateTime ldt = getlocaldatetime(rs, "Datetime");
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
				totalservicemoney = 0, totalrepaymoney = 0;
		int totaltranstime = 0, totalwithdrawtime = 0, totaldeposittime = 0, totalloantime = 0, totalrepaytime = 0;
		while (rs.next()) {
			double money = rs.getDouble("Money");
			switch (rs.getString("TransName")) {
			case Common.TransName_Withdraw:
				totalwithdrawmoney += money;
				totalwithdrawtime++;
				break;
			case Common.TransName_Deposit:
				totaldepositmoney += money;
				totaldeposittime++;
				break;
			case Common.TransName_trans:
				totaltransmoney += money;
				totaltranstime++;
				break;
			case Common.TransName_Loan:
				totalloanmoney += money;
				totalloantime++;
				break;
			case Common.TransName_Repay:
				totalrepaymoney += money;
				totalrepaytime++;
				break;
			case Common.TransName_ServiceFee:
				totalservicemoney += money;
				break;

			}
		}
		al.add("Total transaction money:" + String.valueOf(totaltransmoney));
		al.add("Total transaction time:" + String.valueOf(totaltranstime));
		al.add("Total withdraw money:" + String.valueOf(totalwithdrawmoney));
		al.add("Total withdraw time:" + String.valueOf(totalwithdrawtime));
		al.add("Total deposit money:" + String.valueOf(totaldepositmoney));
		al.add("Total deposit time:" + String.valueOf(totaldeposittime));
		al.add("Total loan money:" + String.valueOf(totalloanmoney));
		al.add("Total loan time:" + String.valueOf(totalloantime));
		al.add("Total repay money:" + String.valueOf(totalrepaymoney));
		al.add("Total repay time:" + String.valueOf(totalrepaytime));
		al.add("Total service fee:" + String.valueOf(totalservicemoney));

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
			LocalDateTime bldt = getlocaldatetime(rs, "BeginDatetime");
			LocalDateTime eldt = getlocaldatetime(rs, "EndDatetime");
			Loan loan = new Loan(rs.getString("AccountID"), rs.getString("LoanName"), rs.getString("LoanReason"),
					rs.getString("Collateral"), bldt, eldt, rs.getDouble("MoneyLoaned"), rs.getDouble("MoneyReturned"),
					rs.getDouble("MoneyOwed"), rs.getDouble("InterestRate"), rs.getInt("Status"));
			al.add(loan);
		}
		return al;
	}

	@Override
	public ArrayList<Loan> UnapprovedLoan() throws Exception {
		ArrayList<Loan> al = new ArrayList<Loan>();
		ResultSet rs = ManagerSystemSQL.getualoans(conn);
		while (rs.next()) {
			LocalDateTime bldt = getlocaldatetime(rs, "BeginDatetime");
			LocalDateTime eldt = getlocaldatetime(rs, "EndDatetime");
			Loan loan = new Loan(rs.getString("AccountID"), rs.getString("LoanName"), rs.getString("LoanReason"),
					rs.getString("Collateral"), bldt, eldt, rs.getDouble("MoneyLoaned"), rs.getDouble("MoneyReturned"),
					rs.getDouble("MoneyOwed"), rs.getDouble("InterestRate"), rs.getInt("Status"));
			al.add(loan);
		}
		return al;
	}

	/*
	 * public static void main(String[] args) throws Exception { Connection conn =
	 * DatabaseConnection.getConnection(); Statement stmt = conn.createStatement();
	 * ManagerSystem ms = new ManagerSystem(conn); String str = "2020-12-14";
	 * DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	 * LocalDate localDate = LocalDate.parse(str, formatter);
	 * System.out.print(ms.GetDayReport(localDate)); }
	 */

}
