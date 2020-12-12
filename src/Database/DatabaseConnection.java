package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import Database.DatabaseTables;
import LoginSystem.LoginSystem;
import User.User;
import Config.DatabaseConfig;
import Common.Common;

public class DatabaseConnection {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		Connection con = getConnection();
//		Statement stmt=con.createStatement();
//		User testuser = new User("zhang","123","user");
//
//		String sql = DatabaseTables.userTable;
//		stmt.executeUpdate(sql);
//		sql = "INSERT INTO `user` "
//				+ "(`Username`,`Password`,`Type`) "
//				+ "VALUES (" + '"' + testuser.getUsername() + '"' +", " + testuser.getPassword()  + ", " + '"' + testuser.getType() + '"' + ")";
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
