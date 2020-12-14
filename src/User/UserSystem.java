package User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import Account.*;
import User.UserSystemSQL; 
import Common.Common;

public class UserSystem implements UserSystemFunctions{
	
	public Connection con;
	
	public UserSystem(Connection con) {
		this.con = con;
	}

	@Override
	public String CreateSavingAccount(String username, double money, String currencyType) throws Exception {
		// TODO Auto-generated method stub
		SavingAccount newAccount = new SavingAccount(username, money, currencyType);
		UserSystemSQL.CreateSavingAccount(newAccount, con);
		return Common.Success;
	}

	@Override
	public String CreateCheckingAccount(String username, double money, String currencyType) throws Exception {
		// TODO Auto-generated method stub
		CheckingAccount newAccount = new CheckingAccount(username, money, currencyType);
		UserSystemSQL.CreateCheckingAccount(newAccount, con);
		return Common.Success;
	}

	@Override
	public ArrayList<Account> ViewAccounts(String username) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = UserSystemSQL.ViewAccounts(username, con);
		ArrayList<Account> curList = new ArrayList<Account>();
		while (rs.next()) {
			if (rs.getString("Type").equals("SavingAccount")) {
				LocalDate ld = rs.getDate("CreateTime").toLocalDate();
				LocalTime lt = rs.getTime("CreateTime").toLocalTime();
				LocalDateTime ldt = LocalDateTime.of(ld, lt);
				SavingAccount sa = new SavingAccount(
						rs.getString("AccountID"),
						rs.getString("Type"),
						rs.getDouble("CurrentBalance"),
						ldt,
						rs.getString("CurrencyType"),
						rs.getString("Username")
						);
				curList.add(sa);
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
						rs.getString("CurrencyType"),
						rs.getString("Username")
						);
				curList.add(ca);
			}
		}
		return curList;
	}

	
	
}
