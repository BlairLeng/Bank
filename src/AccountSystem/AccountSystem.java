package AccountSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

import Account.Account;
import Account.CheckingAccount;
import Account.SavingAccount;
import AccountSystem.AccountSystemSQL;
import TransactionSystem.Transaction;

public class AccountSystem implements AccountSystemFunctions{

	public Connection con;
	
	public AccountSystem(Connection con) {
		this.con = con;
	}
	
	@Override
	public Account QueryAccountInformation(String AccountID) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = AccountSystemSQL.QueryAccountInformation(AccountID, con);
		if (rs.next()) {
			if (rs.getString("Type").equals("SavingAccount")) {
				LocalDate ld = rs.getDate("CreateTime").toLocalDate();
				LocalTime lt = rs.getTime("CreateTime").toLocalTime();
				LocalDateTime ldt = LocalDateTime.of(ld, lt);
				SavingAccount sa = new SavingAccount(
						rs.getString("AccountID"),
						rs.getString("Type"),
						rs.getDouble("CurrentBalance"),
						ldt,
						rs.getString("CurrencyType")
						);
				return sa;
			}
			if (rs.getString("Type").equals("CheckingAccount")) {
				LocalDate ld = rs.getDate("CreateTime").toLocalDate();
				LocalTime lt = rs.getTime("CreateTime").toLocalTime();
				LocalDateTime ldt = LocalDateTime.of(ld, lt);
				CheckingAccount ca = new CheckingAccount(
						rs.getString("AccountID"),
						rs.getString("Type"),
						rs.getDouble("CurrentBalance"),
						ldt,
						rs.getString("CurrencyType")
						);
				return ca;
			}
		}
		return null;
	}

	@Override
	public String RequestLoan(String AccountID, double money, double interestRate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String MakeTransaction(String senderID, String receiverID, double money, String transName) throws SQLException {
		// TODO Auto-generated method stub
		Transaction t = new Transaction(LocalDateTime.now(), money, transName, UUID.randomUUID().toString(), senderID, receiverID);
		String result = AccountSystemSQL.MakeTransaction(t, con);
		return result;
	}

	@Override
	public String Deposit(String AccountID, double money) throws SQLException {
		// TODO Auto-generated method stub
		Transaction t = new Transaction(LocalDateTime.now(), money, "Deposit In", UUID.randomUUID().toString(), AccountID, AccountID);
		String result = AccountSystemSQL.Deposit(t, con);
		return result;
	}

	@Override
	public String Withdraw(String AccountID, double money) throws SQLException {
		Transaction t = new Transaction(LocalDateTime.now(), money, "Withdraw", UUID.randomUUID().toString(), AccountID, AccountID);
		String result = AccountSystemSQL.Withdraw(t, con);
		return result;
	}

}
