package Database;

public class DatabaseTables {
	public static final String userTable = "CREATE TABLE IF NOT EXISTS User(\r\n" + 
			"  Username varchar(50) NOT NULL,\r\n" + 
			"  Password varchar(50) NOT NULL,\r\n" + 
			"  Type varchar(20) NOT NULL,\r\n" + 
			"  PRIMARY KEY ( Username )\r\n" + 
			")";
}
