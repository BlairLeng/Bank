package TransactionSystem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Transaction {
	
	private LocalDateTime transferTime;
	private UUID senderUUID;
	private String senderUUIDstring;
	private UUID receiverUUID;
	private String receiverUUIDstring;
	private double money;
	private UUID transid;
	private String transidstring;
	private String transname;
	
	public Transaction(UUID suid,UUID ruid,double money,String transname) {
		this.transferTime=LocalDateTime.now();
		this.senderUUID=suid;
		this.senderUUIDstring=this.senderUUID.toString();
		this.receiverUUID=ruid;
		this.receiverUUIDstring=this.receiverUUID.toString();
		this.money=money;
		this.transname=transname;
		this.transid=UUID.randomUUID();
		this.transidstring=this.transid.toString();
	}
	
	public Transaction(LocalDateTime transtime,double money,String transname,String transid,String suid,String ruid) {
		this.transferTime=transtime;
		this.money=money;
		this.transidstring=transid;
		this.receiverUUIDstring=ruid;
		this.senderUUIDstring=suid;
		this.transname=transname;
		this.transid=UUID.fromString(this.transidstring);
		this.senderUUID=UUID.fromString(this.senderUUIDstring);
		this.receiverUUID=UUID.fromString(this.receiverUUIDstring);
	}
	
	public String getTransTime() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = transferTime.format(myFormatObj);
	    return formattedDate;
	}
	
	public String getsendername() {
		return null;
	}
	
	public String getreceivername() {
		return null;
	}
	
	public String getsenderUUID() {
		return this.senderUUIDstring;
	}
	
	public String getreceiverUUID() {
		return this.receiverUUIDstring;
	}
	
	public double getmoney() {
		return this.money;
	}
	
	public String gettransUUID() {
		return this.transidstring;
	}
	
	public String gettransname() {
		return this.transname;
	}
}
