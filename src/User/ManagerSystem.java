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
import java.util.HashMap;
import java.util.Iterator;

import Account.Account;
import Account.CheckingAccount;
import Account.SavingAccount;
import AccountSystem.AccountSystemSQL;
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
	
	public int getcurrencytype(String type) {
		if(type.equals(Common.CurrencyType_CNY))
			return 0;
		else if(type.equals(Common.CurrencyType_EUR))
			return 1;
		else 
			return 2;
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
			String sid = rs.getString("SenderID");
			String rid = rs.getString("ReceiverID");
			ResultSet rs1 = AccountSystemSQL.QueryAccountInformation(sid, conn);
			ResultSet rs2 = AccountSystemSQL.QueryAccountInformation(rid, conn);
			rs1.next();
			rs2.next();
			LocalDateTime ldt = getlocaldatetime(rs, "Datetime");
			Transaction t = new Transaction(ldt, rs.getDouble("Money"), rs.getString("TransName"),
					rs.getString("TransID"), sid, rid, rs1.getString("Username"), rs2.getString("Username"));
			al.add(t);
		}
		return al;
	}

	@Override
	public ArrayList<Transaction> Usertrans(String username) throws Exception {
		ArrayList<Transaction> al = new ArrayList<Transaction>();
		ResultSet rs = ManagerSystemSQL.checkusertrans(conn, username);
		while (rs.next()) {
			String sid = rs.getString("SenderID");
			String rid = rs.getString("ReceiverID");
			ResultSet rs1 = AccountSystemSQL.QueryAccountInformation(sid, conn);
			ResultSet rs2 = AccountSystemSQL.QueryAccountInformation(rid, conn);
			rs1.next();
			rs2.next();
			LocalDateTime ldt = getlocaldatetime(rs, "Datetime");
			Transaction t = new Transaction(ldt, rs.getDouble("Money"), rs.getString("TransName"),
					rs.getString("TransID"), sid, rid, rs1.getString("Username"), rs2.getString("Username"));
			al.add(t);
		}
		return al;
	}

	@Override
	public ArrayList<Transaction> GetDaytrans(LocalDate date) throws Exception {
		ArrayList<Transaction> al = new ArrayList<Transaction>();
		ResultSet rs = ManagerSystemSQL.getdaytrans(conn, date);
		while (rs.next()) {
			String sid = rs.getString("SenderID");
			String rid = rs.getString("ReceiverID");
			ResultSet rs1 = AccountSystemSQL.QueryAccountInformation(sid, conn);
			ResultSet rs2 = AccountSystemSQL.QueryAccountInformation(rid, conn);
			rs1.next();
			rs2.next();
			LocalDateTime ldt = getlocaldatetime(rs, "Datetime");
			Transaction t = new Transaction(ldt, rs.getDouble("Money"), rs.getString("TransName"),
					rs.getString("TransID"), sid, rid, rs1.getString("Username"), rs2.getString("Username"));
			al.add(t);
		}
		return al;
	}

	@Override
	public HashMap<String, Double[][]> GetDayReport(LocalDate date) throws Exception {
		HashMap<String, Double[][]> map=new HashMap<String,Double[][]>();
		Double [][] trans = new Double[3][2];
		Double [][] withdraw = new Double[3][2];
		Double [][] deposit = new Double[3][2];
		Double [][] loan = new Double[3][2];
		Double [][] service = new Double[3][2];
		Double [][] repay = new Double[3][2];
		for(int i=0;i<3;i++) {
			for(int j=0;j<2;j++) {
				trans[i][j]=0.0;
				withdraw[i][j]=0.0;
				deposit[i][j]=0.0;
				loan[i][j]=0.0;
				service[i][j]=0.0;
				repay[i][j]=0.0;
			}
		}
		ResultSet rs = ManagerSystemSQL.getdaytrans(conn, date);
		while(rs.next()) {
			double money = rs.getDouble("Money");
			String accounttype=AccountSystemSQL.getAccountType(rs.getString("SenderID"), conn);
			switch(rs.getString("Transname")){
			case Common.TransName_trans:
				trans[getcurrencytype(accounttype)][0]+=money;
				trans[getcurrencytype(accounttype)][1]++;
				break;
			case Common.TransName_Withdraw:
				withdraw[getcurrencytype(accounttype)][0]+=money;
				withdraw[getcurrencytype(accounttype)][1]++;
				break;
			case Common.TransName_Deposit:
				deposit[getcurrencytype(accounttype)][0]+=money;
				deposit[getcurrencytype(accounttype)][1]++;
				break;
			case Common.TransName_Loan:
				loan[getcurrencytype(accounttype)][0]+=money;
				loan[getcurrencytype(accounttype)][1]++;
				break;
			case Common.TransName_ServiceFee:
				service[getcurrencytype(accounttype)][0]+=money;
				service[getcurrencytype(accounttype)][1]++;
				break;
			case Common.TransName_Repay:
				repay[getcurrencytype(accounttype)][0]+=money;
				repay[getcurrencytype(accounttype)][1]++;
				break;
			}
		}
		map.put(Common.TransName_Deposit, trans);
		map.put(Common.TransName_Loan, loan);
		map.put(Common.TransName_Repay, repay);
		map.put(Common.TransName_ServiceFee, service);
		map.put(Common.TransName_trans, trans);
		map.put(Common.TransName_Withdraw, withdraw);
		
		return map;
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
