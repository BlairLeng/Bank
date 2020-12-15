package CentralSystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;

import Account.*;
import Common.Common;

public class CentralSystem implements CentralSystemFunctions{

	public Connection con;
	
	public CentralSystem(Connection con) {
		this.con = con;
	}
	
	@Override
	public String UpdateSavingAccounts(LocalDateTime ldt) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = CentralSystemSQL.getAllAccounts(con);
		ArrayList<String> AccountIDList = new ArrayList<String>();
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		
		//calculate days difference
		while (rs.next()) {
			int difference = 0;
			String accid = rs.getString("AccountID");
			LocalDate ld = rs.getDate("LastTime").toLocalDate();
			LocalTime lt = rs.getTime("LastTime").toLocalTime();
			LocalDateTime lastTimeldt = LocalDateTime.of(ld, lt);
			difference = (int) ChronoUnit.DAYS.between(lastTimeldt, ldt);
			if (rs.getDouble("CurrentBalance") > Common.HighBalance) {				
				hash.put(accid, difference);
				AccountIDList.add(accid);
			}
		}
		
		//update money
		
		//update lasttime
		
		return null;
	}
	
}
