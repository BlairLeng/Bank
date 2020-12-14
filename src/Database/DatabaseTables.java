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
	
	public static final String loanTable = "CREATE TABLE IF NOT EXISTS Loan  (\r\n" + 
			"  LoanID varchar(100) NOT NULL,\r\n" + 
			"  LoanName varchar(100),\r\n" +
			"  LoanReason varchar(100),\r\n" +
			"  AccountID varchar(100) NOT NULL,\r\n" + 
			"  BeginDatatime datetime(0) NOT NULL,\r\n" + 
			"  EndDatatime datetime(0) NOT NULL,\r\n" +
			"  MoneyOwed DOUBLE PRECISION(20, 4) NOT NULL,\r\n" + 
			"  MoneyLoaned DOUBLE PRECISION(20, 4) NOT NULL,\r\n" + 
			"  MoneyReturned DOUBLE PRECISION(20, 4) NOT NULL,\r\n" + 
			"  InterestRate DOUBLE PRECISION(20, 4) NOT NULL,\r\n" + 
			"  Status int(10) NOT NULL,\r\n" + 
			"  CONSTRAINT AccountID FOREIGN KEY (AccountID) REFERENCES Account(AccountID),\r\n" + 
			"  PRIMARY KEY (LoanID)\r\n" + 
			");";
	
	public static final String transactionTable = "CREATE TABLE IF NOT EXISTS Trans  (\r\n" + 
			"  TransID varchar(100) NOT NULL,\r\n" + 
			"  TransName varchar(100),\r\n" +
			"  SenderID varchar(100) NOT NULL,\r\n" + 
			"  ReceiverID varchar(100) NOT NULL,\r\n" + 
			"  Money DOUBLE PRECISION(20, 4) NOT NULL,\r\n" + 
			"  Datetime datetime(0) NOT NULL,\r\n" + 
			"  PRIMARY KEY (TransID),\r\n" + 
			"  CONSTRAINT ReceiverID FOREIGN KEY (ReceiverID) REFERENCES account(AccountID),\r\n" + 
			"  CONSTRAINT SenderID FOREIGN KEY (SenderID) REFERENCES account(AccountID)\r\n" +
			");";
	
	public static final String stockTable = "CREATE TABLE IF NOT EXISTS Stocks (\r\n"+
	        "  StockID varchar(100) NOT NULL,\r\n" + 
			"  StockName varchar(100) NOT NULL,\r\n" +
	        "  Price DOUBLE PRECISION(20, 4) NOT NULL,\r\n"+
			"  PRIMARY KEY (StockID)\r\n"+
	        "  );";
	
	public static final String stocktransTable = "CREATE TABLE IF NOT EXISTS Stocktrans (\r\n" +
			"  TransID varchar(100) NOT NULL,\r\n" + 
			"  TransName varchar(100) NOT NULL,\r\n" +
			"  StockID varchar(100) NOT NULL,\r\n" +
	        "  Price DOUBLE PRECISION(20, 4) NOT NULL,\r\n" +
			"  Amount int(10) NOT NULL,\r\n" +
			"  PRIMARY KEY (TransID),\r\n" +
			"  CONSTRAINT StockID FOREIGN KEY (StockID) REFERENCES stocks(StockID)\r\n" +
	        " );";
	
	public static final String accountstockTable = "CREATE TABLE IF NOT EXISTS Accountstock (\r\n" +
			"  AccountID varchar(100) NOT NULL,\r\n" + 
			"  StockID varchar(100) NOT NULL,\r\n" +
			"  Amount int(10) NOT NULL,\r\n" +
			"  PRIMARY KEY (AccountID,StockID),\r\n" +
			"  CONSTRAINT StockID FOREIGN KEY (StockID) REFERENCES stocks(StockID),\r\n" +
			"  CONSTRAINT AccountID FOREIGN KEY (AccountID) REFERENCES account(AccountID)\r\n" +
	        " );";
}
	