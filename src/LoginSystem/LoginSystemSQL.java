package LoginSystem;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Common.Common;
import Database.DatabaseConnection;
import User.User;

public class LoginSystemSQL {
	public static String SignupNewUser(User newUser, Connection con) throws Exception{		
		Statement stmt=con.createStatement();
		String sql = "INSERT INTO `user` "
				+ "(`Username`,`Password`,`Type`) "
				+ "VALUES (" 
				+ '"' 
				+ newUser.getUsername() 
				+ '"' 
				+ ", " 
				+ '"'
				+ newUser.getPassword().toString()  
				+ '"'
				+ ", " 
				+ '"' 
				+ newUser.getType() 
				+ '"' 
				+ ")";
		stmt.executeUpdate(sql);
		return Common.Success;
	}
	
	public static String checkUser(String username, Connection con) throws Exception {
		Statement stmt=con.createStatement();
		String sql = "SELECT * FROM user WHERE username = "
					+ "'"
					+ username
					+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			return Common.Success;			
		}
		return Common.Failed;
	}
	
	public static String checkPasswordUser(String username, String password, Connection con) throws Exception{
		
		Statement stmt=con.createStatement();
		String sql = "SELECT * FROM user WHERE username = "
					+ "'"
					+ username
					+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			if (rs.getString("Username").equals(username) 
					&& rs.getString("Password").equals(password)
					&& rs.getString("Type").equals("user")) {
				return Common.Success;
			}
			return Common.Failed;
		}
		else {
			return Common.Failed;
		}
	}
	
	public static String checkPasswordManager(String username, String password, Connection con) throws Exception{
		
		Statement stmt=con.createStatement();
		String sql = "SELECT * FROM user WHERE username = "
					+ "'"
					+ username
					+ "'";
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			if (rs.getString("Username").equals(username) 
					&& rs.getString("Password").equals(password)
					&& rs.getString("Type").equals("manager")) {
				return Common.Success;
			}
			return Common.Failed;
		}
		else {
			return Common.Failed;
		}
	}
}