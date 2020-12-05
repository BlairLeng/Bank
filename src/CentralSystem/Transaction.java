package CentralSystem;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
	
	private LocalDateTime transferTime;
	private String sender;
	private String receiver;
	private UUID senderUUID;
	private UUID receiverUUID;
	
	public Transaction(LocalDateTime transferTime, String sender, String receiver, UUID senderUUID, UUID receiverUUID) {
		this.transferTime = transferTime;
		this.sender = sender;
		this.receiver = receiver;
		this.senderUUID = senderUUID;
		this.receiverUUID = receiverUUID;
	}
	
}
