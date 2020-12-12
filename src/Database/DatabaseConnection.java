package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


import Config.DatabaseConfig;
import Common.Common;

public class DatabaseConnection {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		Statement stmt=con.createStatement();
		
		/*
		Example here 
		
		String sql = "CREATE TABLE REGISTRATION " +
                "(id INTEGER not NULL, " +
                " first VARCHAR(255), " + 
                " last VARCHAR(255), " + 
                " age INTEGER, " + 
                " PRIMARY KEY ( id ))"; 
		stmt.executeUpdate(sql);


		 */
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
