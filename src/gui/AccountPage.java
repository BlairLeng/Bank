package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import Account.Account;

import java.sql.Connection;
import java.util.ArrayList;

import AccountSystem.AccountSystem;
import Database.DatabaseConnection;
import User.ManagerSystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountPage {

	private JFrame frmAccount;
	private String username;
	private AccountSystem accountSystem;
	private ManagerSystem managerSystem;
	private String type;
	private String uuid;
	private JLabel idField;
	private JLabel typeField;
	private JLabel currencyField;
	private JLabel balanceField;
	private JLabel timeField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection con = DatabaseConnection.getConnection();
					AccountPage window = new AccountPage("Bill","Customer",new AccountSystem(con),"0c936cc0-864b-4c53-afa6-5f6e7edc94bc");
					window.frmAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AccountPage(String name, String type,AccountSystem accountSystem,String uuid) {
		this.username = name;
		this.type = type;
		this.uuid = uuid;
		this.accountSystem = accountSystem;
		this.managerSystem = null;
		if(type.equals("Customer")) {
			initialize_asCustomer();
		}
		else if(type.equals("Manager")) {
			initialize_asManager();
		}
		refreshInfo();
		frmAccount.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize_asCustomer() {
		frmAccount = new JFrame();
		frmAccount.setTitle("Account");
		frmAccount.setResizable(false);
		frmAccount.setBounds(100, 100, 610, 320);
		frmAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAccount.getContentPane().setLayout(null);
		
		int windowWidth = frmAccount.getWidth();
		int windowHeight = frmAccount.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frmAccount.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel idLabel = new JLabel("Account ID: ");
		idLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		idLabel.setBounds(20, 64, 120, 25);
		frmAccount.getContentPane().add(idLabel);
		
		JLabel typeLabel = new JLabel("Account Type: ");
		typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		typeLabel.setBounds(20, 108, 120, 25);
		frmAccount.getContentPane().add(typeLabel);
		
		JLabel currencyLabel = new JLabel("Currency Type: ");
		currencyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		currencyLabel.setBounds(20, 152, 120, 25);
		frmAccount.getContentPane().add(currencyLabel);
		
		JLabel balanceLabel = new JLabel("Current Balance:");
		balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		balanceLabel.setBounds(20, 196, 120, 25);
		frmAccount.getContentPane().add(balanceLabel);
		
		JLabel timeLabel = new JLabel("Create Time: ");
		timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		timeLabel.setBounds(20, 240, 120, 25);
		frmAccount.getContentPane().add(timeLabel);
		
		JLabel userLabel = new JLabel("User Name: ");
		userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		userLabel.setBounds(20, 20, 120, 25);
		frmAccount.getContentPane().add(userLabel);
		
		JLabel userField = new JLabel("Bill");
		userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userField.setBounds(145, 20, 280, 25);
		frmAccount.getContentPane().add(userField);
		
		idField = new JLabel("0c936cc0-864b-4c53-afa6-5f6e7edc94bc");
		idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		idField.setBounds(145, 64, 280, 25);
		frmAccount.getContentPane().add(idField);
		
		typeField = new JLabel("SavingAccount");
		typeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		typeField.setBounds(145, 108, 280, 25);
		frmAccount.getContentPane().add(typeField);
		
		currencyField = new JLabel("USD");
		currencyField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		currencyField.setBounds(145, 152, 280, 25);
		frmAccount.getContentPane().add(currencyField);
		
		balanceField = new JLabel("700.00");
		balanceField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		balanceField.setBounds(145, 196, 280, 25);
		frmAccount.getContentPane().add(balanceField);
		
		timeField = new JLabel("2020-12-13 17:32:02");
		timeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeField.setBounds(145, 240, 280, 25);
		frmAccount.getContentPane().add(timeField);
		
		JButton saveButton = new JButton("Deposit");
		saveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickDepositbutton();
			}
		});
		saveButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		saveButton.setBounds(435, 20, 150, 25);
		frmAccount.getContentPane().add(saveButton);
		
		JButton withdrewButton = new JButton("Withdrew");
		withdrewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickWithdrewbutton();
			}
		});
		withdrewButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		withdrewButton.setBounds(435, 64, 150, 25);
		frmAccount.getContentPane().add(withdrewButton);
		
		JButton sendButton = new JButton("Send Money");
		sendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickSendbutton();
			}
		});
		sendButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		sendButton.setBounds(435, 108, 150, 25);
		frmAccount.getContentPane().add(sendButton);
		
		JButton transButton = new JButton("Trans History");
		transButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTransbutton();
			}
		});
		transButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		transButton.setBounds(435, 152, 150, 25);
		frmAccount.getContentPane().add(transButton);
		
		JButton applyloanButton = new JButton("Apply Loan");
		applyloanButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickApplyLoanbutton();
			}
		});
		applyloanButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		applyloanButton.setBounds(434, 196, 150, 25);
		frmAccount.getContentPane().add(applyloanButton);
		
		JButton loaninfoButton = new JButton("Loan Info");
		loaninfoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickLoanInfobutton();
			}
		});
		loaninfoButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		loaninfoButton.setBounds(435, 240, 150, 25);
		frmAccount.getContentPane().add(loaninfoButton);
	}
	private void initialize_asManager() {
		frmAccount = new JFrame();
		frmAccount.setTitle("Account");
		frmAccount.setResizable(false);
		frmAccount.setBounds(100, 100, 610, 320);
		frmAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAccount.getContentPane().setLayout(null);
		
		int windowWidth = frmAccount.getWidth();
		int windowHeight = frmAccount.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frmAccount.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel idLabel = new JLabel("Account ID: ");
		idLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		idLabel.setBounds(20, 64, 120, 25);
		frmAccount.getContentPane().add(idLabel);
		
		JLabel typeLabel = new JLabel("Account Type: ");
		typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		typeLabel.setBounds(20, 108, 120, 25);
		frmAccount.getContentPane().add(typeLabel);
		
		JLabel currencyLabel = new JLabel("Currency Type: ");
		currencyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		currencyLabel.setBounds(20, 152, 120, 25);
		frmAccount.getContentPane().add(currencyLabel);
		
		JLabel balanceLabel = new JLabel("Current Balance:");
		balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		balanceLabel.setBounds(20, 196, 120, 25);
		frmAccount.getContentPane().add(balanceLabel);
		
		JLabel timeLabel = new JLabel("Create Time: ");
		timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		timeLabel.setBounds(20, 240, 120, 25);
		frmAccount.getContentPane().add(timeLabel);
		
		JLabel userLabel = new JLabel("User Name: ");
		userLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		userLabel.setBounds(20, 20, 120, 25);
		frmAccount.getContentPane().add(userLabel);
		
		JLabel userField = new JLabel("Bill");
		userField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		userField.setBounds(145, 20, 280, 25);
		frmAccount.getContentPane().add(userField);
		
		idField = new JLabel("0c936cc0-864b-4c53-afa6-5f6e7edc94bc");
		idField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		idField.setBounds(145, 64, 280, 25);
		frmAccount.getContentPane().add(idField);
		
		typeField = new JLabel("SavingAccount");
		typeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		typeField.setBounds(145, 108, 280, 25);
		frmAccount.getContentPane().add(typeField);
		
		currencyField = new JLabel("USD");
		currencyField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		currencyField.setBounds(145, 152, 280, 25);
		frmAccount.getContentPane().add(currencyField);
		
		balanceField = new JLabel("700.00");
		balanceField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		balanceField.setBounds(145, 196, 280, 25);
		frmAccount.getContentPane().add(balanceField);
		
		timeField = new JLabel("2020-12-13 17:32:02");
		timeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeField.setBounds(145, 240, 280, 25);
		frmAccount.getContentPane().add(timeField);
		
		JButton transButton = new JButton("Trans History");
		transButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickTransbutton();
			}
		});
		transButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		transButton.setBounds(435, 70, 150, 25);
		frmAccount.getContentPane().add(transButton);
		
		JButton loaninfoButton = new JButton("Loan Info");
		loaninfoButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickLoanInfobutton();
			}
		});
		loaninfoButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		loaninfoButton.setBounds(435, 145, 150, 25);
		frmAccount.getContentPane().add(loaninfoButton);
	}
	public void refreshInfo() {
		Account account = null;
		try {
			account = this.accountSystem.QueryAccountInformation(this.uuid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(account != null) {
			this.idField.setText(account.getUUID());
			this.typeField.setText(account.getType());
			this.balanceField.setText(String.valueOf(account.getCurrentBalance()));
			this.currencyField.setText(account.getCurrencyType());
			this.timeField.setText(account.getOpenTime());
		}
	}
	private void clickDepositbutton() {
		new DepositWithdrew(this,"Deposit",this.uuid);
	}
	private void clickWithdrewbutton() {
		new DepositWithdrew(this,"Withdrew",this.uuid);
	}
	private void clickSendbutton() {
	
	}
	private void clickTransbutton() {
	
	}
	private void clickApplyLoanbutton() {
	
	}
	private void clickLoanInfobutton() {
		
	}
	public String Deposit(String uuid,double money) {
		String resultString = new String();
		try {
			resultString = this.accountSystem.Deposit(uuid, money);
		}catch (Exception e) {
			return String.valueOf(e);
		}
		return resultString;
	}
	public String Withdrew(String uuid,double money) {
		String resultString = new String();
		try {
			resultString = this.accountSystem.Withdraw(uuid, money);
		}catch (Exception e) {
			return String.valueOf(e);
		}
		return resultString;
	}
}