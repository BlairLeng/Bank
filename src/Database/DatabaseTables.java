package Database;

public class DatabaseTables {
	public static final String userTable = "CREATE TABLE IF NOT EXISTS User(\r\n" + 
			"  Username varchar(50) NOT NULL,\r\n" + 
			"  Password varchar(50) NOT NULL,\r\n" + 
			"  Type varchar(20) NOT NULL,\r\n" + 
			"  PRIMARY KEY ( Username )\r\n" + 
			")";
	
	public static final String accountTable = "CREATE TABLE IF NOT EXISTS Account  (\r\n" + 
			"  AccountID varchar(100) NOT NULL,\r\n" + 
			"  Username varchar(50) NOT NULL,\r\n" + 
			"  Type varchar(20) NOT NULL,\r\n" + 
			"  CurrentBalance DOUBLE PRECISION(20, 4) NOT NULL,\r\n" + 
			"  CreateTime datetime(0) NOT NULL,\r\n" + 
			"  LastTime datetime(0) NOT NULL,\r\n" + 
			"  BeginMoney DOUBLE PRECISION(20, 4) NOT NULL,\r\n" +
			"  PRIMARY KEY (AccountID),\r\n" + 
			"  CONSTRAINT Username FOREIGN KEY (Username) REFERENCES user(Username)\r\n" + 
			");";
}
	