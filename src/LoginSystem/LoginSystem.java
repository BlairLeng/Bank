package LoginSystem;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import Common.Common;
import User.User;
import LoginSystem.LoginSystemSQL;
import Account.Account;


public class LoginSystem implements LoginFunctions{
	public Connection con;
	
	public LoginSystem
	(
		Connection con
	) {
		this.con = con;
	}
	

	@Override
	public String SignupNewUser(String username, String password, String type) throws Exception {
		// TODO Auto-generated method stub
		if (LoginSystemSQL.checkUser(username,con) == Common.Success) {
			System.out.println(Common.UsernameAlreadyExists);
			return Common.UsernameAlreadyExists;
		}
		
		if (LoginSystemSQL.checkUser(username,con) == Common.Failed) {
			User newUser = new User(username, password, type); 
			LoginSystemSQL.SignupNewUser(newUser, con);
			return Common.Success;
		}
		return Common.Failed;
	}

	@Override
	public String LoginAsUser(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		String result = LoginSystemSQL.checkPasswordUser(username, password, con);
		if (result == Common.Success) {
			return Common.Success;
		}
		else {
			return result; 
		}
	}

	@Override
	public String LoginAsManager(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		String result = LoginSystemSQL.checkPasswordManager(username, password, con);
		if (result == Common.Success) {
			return Common.Success;
		}
		else {
			return result; 
		}
	}
}
