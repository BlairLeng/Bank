package LoginSystem;

public interface LoginFunctions {
	
	public String SignupNewUser(String username, String password);
	
	public String SignupNewSavingAccount(String username, double money);
	
	public String SignupNewCheckingAccount(String username, double money);
	
	public String LoginAsUser(String username, String password);
	
	public String LoginAsManager(String username, String password);

}
