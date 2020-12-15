package CentralSystem;

import java.sql.SQLException;
import java.time.LocalDateTime;

public interface CentralSystemFunctions {

	public String UpdateSavingAccounts(LocalDateTime ldt) throws SQLException;
	
}
