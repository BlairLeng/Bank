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
	private String sendername;
	private String receivername;
	
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
	
	public Transaction(LocalDateTime transtime,double money,String transname,String transids,String suids,String ruids) {
		this.transferTime=transtime;
		this.money=money;
		this.transidstring=transids;
		this.receiverUUIDstring=ruids;
		this.senderUUIDstring=suids;
		this.transname=transname;
		//this.transid= UUID.fromString(this.transidstring);
		//this.senderUUID= UUID.fromString(this.senderUUIDstring);
		//this.receiverUUID= UUID.fromString(this.receiverUUIDstring);
	}
	
	public Transaction(LocalDateTime transtime,double money,String transname,String transids,String suids,String ruids,String sendername,String receivername) {
		this.transferTime=transtime;
		this.money=money;
		this.transidstring=transids;
		this.receiverUUIDstring=ruids;
		this.senderUUIDstring=suids;
		this.transname=transname;
		this.sendername=sendername;
		this.receivername=receivername;
		//this.transid= UUID.fromString(this.transidstring);
		//this.senderUUID= UUID.fromString(this.senderUUIDstring);
		//this.receiverUUID= UUID.fromString(this.receiverUUIDstring);
	}
	
	public String getTransTime() {
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    String formattedDate = transferTime.format(myFormatObj);
	    return formattedDate;
	}
	
	public String getsendername() {
		return this.sendername;
	}
	
	public String getreceivername() {
		return this.receivername;
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
