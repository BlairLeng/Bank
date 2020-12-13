package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Account.SavingAccount;
import Database.DatabaseTables;
import LoginSystem.LoginSystem;
import User.User;
import Config.DatabaseConfig;
import Common.Common;

public class DatabaseConnection {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		SavingAccount newUser = new SavingAccount("zhang", 1000);
//
//		String sql = DatabaseTables.accountTable;
//		stmt.executeUpdate(sql);
//		String sql = "INSERT INTO `account` "
//				+ "(`AccountID`,`Username`,`Type`,`CurrentBalance`,`CreateTime`,`LastTime`,`BeginMoney`) "
//				+ "VALUES (" 
//				+ '"' 
//				+ newUser.getUUID()
//				+ '"' 
//				+ ", " 
//				+ '"' 
//				+ newUser.getCustomerName()
//				+ '"' 
//				+ ", " 
//				+ '"'
//				+ newUser.getType()
//				+ '"'
//				+ ", "  
//				+ newUser.getCurrentBalance()
//				+ ", "
//				+ '"' 
//				+ newUser.getOpenTime()
//				+ '"' 
//				+ ", "
//				+ '"' 
//				+ newUser.getCurrentTime()
//				+ '"' 
//				+ ", " 
//				+ newUser.getCurrentBalance()
//				+ ")";
//		System.out.println(sql);
//		stmt.executeUpdate(sql);
//		LoginSystem ls = new LoginSystem();
//		ls.SignupNewUser("li", "123", "user");
	}
	
	public static Connection getConnection() throws Exception{
		try {
			String driver = DatabaseConfig.driver;
			String url = DatabaseConfig.url;
			String username = DatabaseConfig.username;
			String password = DatabaseConfig.password;
			Class.forName(driver);
			
			Connection conn = DriverManager.getConnection(url,username,password);
			System.out.println(Common.Success);
			return conn;
		}
		catch(Exception e){
			System.out.println(Common.Failed);
			System.out.println(e);
		}
		return null;
	}
}
