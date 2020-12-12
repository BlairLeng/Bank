package LoginSystem;

import java.util.ArrayList;

public interface LoginFunctions {
	
	public String SignupNewUser(String username, String password, String type) throws Exception;
	
	public String LoginAsUser(String username, String password) throws Exception;
	
	public String LoginAsManager(String username, String password) throws Exception;

}
