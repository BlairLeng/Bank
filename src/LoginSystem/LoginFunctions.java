package LoginSystem;

import java.util.ArrayList;

public interface LoginFunctions {
	
	public String SignupNewUser(String username, String password, String type);
	
	public String LoginAsUser(String username, String password);
	
	public String LoginAsManager(String username, String password);

}
