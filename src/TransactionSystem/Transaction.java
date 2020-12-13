package TransactionSystem;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class Transaction {
	
	private LocalDateTime transferTime;
	private String sender;
	private String receiver;
	private UUID senderUUID;
	private UUID receiverUUID;
	private double money;
	
	public Transaction(LocalDateTime transferTime, String sender, String receiver, UUID senderUUID, UUID receiverUUID,double money) {
		this.transferTime = transferTime;
		this.sender = sender;
		this.receiver = receiver;
		this.senderUUID = senderUUID;
		this.receiverUUID = receiverUUID;
		this.money=money;
	}
	
	public Transaction(Date transferTime,String sender,String receiver,String senderUUID,String receiverUUID,double money) {
		Instant instant=transferTime.toInstant();
		ZoneId zoneId = ZoneId.systemDefault();
		this.transferTime=instant.atZone(zoneId).toLocalDateTime();
		this.sender=sender;
		this.receiver=receiver;
		this.receiverUUID=UUID.fromString(receiverUUID);
		this.senderUUID=UUID.fromString(senderUUID);
		this.money=money;
	}
	
}
