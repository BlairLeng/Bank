package LoanSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

import AccountSystem.AccountSystemSQL;
import LoanSystem.LoanSystemSQL;
import TransactionSystem.Transaction;
import Common.Common;


public class LoanSystem implements LoanSystemFunctions {
	public Connection con;
	
	public LoanSystem(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Loan> getAccountLoans(String AccountID) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = LoanSystemSQL.getAccountLoans(AccountID, con);
		ArrayList<Loan> loanList = new ArrayList<Loan>();
		while (rs.next()) {
			LocalDate beginld = rs.getDate("BeginDatetime").toLocalDate();
			LocalTime beginlt = rs.getTime("BeginDatetime").toLocalTime();
			LocalDateTime beginldt = LocalDateTime.of(beginld, beginlt);
			LocalDate endld = rs.getDate("EndDatetime").toLocalDate();
			LocalTime endlt = rs.getTime("EndDatetime").toLocalTime();
			LocalDateTime endldt = LocalDateTime.of(endld, endlt);
			if (rs.getInt("Status") == 0 || rs.getInt("Status") == 1) {				
				Loan loan = new Loan(rs.getString("AccountID"), rs.getString("LoanName"), rs.getString("LoanReason"),
						rs.getString("Collateral"), beginldt, endldt, rs.getDouble("MoneyLoaned"), rs.getDouble("MoneyReturned"),
						rs.getDouble("MoneyOwed"), rs.getDouble("InterestRate"), rs.getInt("Status"), rs.getString("LoanID"));
				loanList.add(loan);
			}
		}
		return loanList;
	}

	@Override
	public String Repayment(String AccountID, String LoanID, double money) throws SQLException {
		// TODO Auto-generated method stub
		String result;
		result = AccountSystemSQL.checkAccountCurrencyType(AccountID, con);
		if (!result.equals(Common.CurrencyType_USD)) return Common.CurrencyTypeNotUSD;
		result = AccountSystemSQL.checkMoney(AccountID, money, con);
		if (result != Common.Success) return result;
		result = LoanSystemSQL.legalLoan(LoanID, con);
		if (result != Common.Success) return result;
		double maxMoney = LoanSystemSQL.maximumRepayment(LoanID, money, con);
		result = LoanSystemSQL.Repay(AccountID, LoanID, maxMoney, con);
		Transaction t = new Transaction(LocalDateTime.now(), money, Common.TransName_Repay, UUID.randomUUID().toString(), AccountID, AccountID);
		if (result != Common.Success) return result;
		result = AccountSystemSQL.MakeTransaction(t, con);
		// change status
		LoanSystemSQL.changeStatus(LoanID, con);
		return result;
	}

	@Override
	public Loan getLoanInformation(String LoanID) throws Exception {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public Boolean getAccountHasLoans(String AccountID) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = LoanSystemSQL.getAccountLoans(AccountID, con);
		while (rs.next()) {
			LocalDate beginld = rs.getDate("BeginDatetime").toLocalDate();
			LocalTime beginlt = rs.getTime("BeginDatetime").toLocalTime();
			LocalDateTime beginldt = LocalDateTime.of(beginld, beginlt);
			LocalDate endld = rs.getDate("EndDatetime").toLocalDate();
			LocalTime endlt = rs.getTime("EndDatetime").toLocalTime();
			LocalDateTime endldt = LocalDateTime.of(endld, endlt);
			if (rs.getInt("Status") == 0) {				
				return true;
			}
		}
		return false;
	}
	
	
}
