package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import AccountSystem.AccountSystem;
import Common.Common;
import Database.DatabaseConnection;

import javax.swing.JButton;


public class DepositWithdrew {

	private JFrame frame;
	private AccountPage accountPage;
	private String type;
	private String uuid;
	private JTextField moneyField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection con = DatabaseConnection.getConnection();
					DepositWithdrew window = new DepositWithdrew(new AccountPage("Bill","Customer",new AccountSystem(con),"0c936cc0-864b-4c53-afa6-5f6e7edc94bc"),"Deposit","0c936cc0-864b-4c53-afa6-5f6e7edc94bc");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DepositWithdrew(AccountPage accountPage,String type,String uuid) {
		this.accountPage = accountPage;
		this.type = type;
		this.uuid = uuid;
		if(this.type.equals("Deposit")) {
			initialize_asDeposit();
			frame.setVisible(true);
		}else if(this.type.equals("Withdrew")) {
			initialize_asWithdrew();
			frame.setVisible(true);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize_asDeposit() {
		frame = new JFrame();
		frame.setTitle("Deposit");
		frame.setBounds(100, 100, 350, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		int windowWidth = frame.getWidth();
		int windowHeight = frame.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel userJLabel = new JLabel("Depoisit Amount: ");
		userJLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		userJLabel.setBounds(20, 20, 150, 25);
		frame.getContentPane().add(userJLabel);

		moneyField = new JTextField();
		moneyField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		moneyField.setBounds(40, 60, 250, 25);
		frame.getContentPane().add(moneyField);
		moneyField.setColumns(10);
		moneyField.setDocument(new JTextFieldFilter(JTextFieldFilter.DOUBLE));
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		confirmButton.setBounds(110, 120, 90, 25);
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickconfirmbutton();
			}
		});
		frame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeThis();
			}
		});
		cancelButton.setBounds(210, 120, 90, 25);
		
		frame.getContentPane().add(cancelButton);
	}
	private void initialize_asWithdrew() {
		frame = new JFrame();
		frame.setTitle("Withdrew");
		frame.setBounds(100, 100, 350, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int windowWidth = frame.getWidth();
		int windowHeight = frame.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel userJLabel = new JLabel("Withdrew Amount: ");
		userJLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		userJLabel.setBounds(20, 20, 150, 25);
		frame.getContentPane().add(userJLabel);

		moneyField = new JTextField();
		moneyField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		moneyField.setBounds(40, 60, 250, 25);
		frame.getContentPane().add(moneyField);
		moneyField.setColumns(10);
		moneyField.setDocument(new JTextFieldFilter(JTextFieldFilter.DOUBLE));
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		confirmButton.setBounds(110, 120, 90, 25);
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickconfirmbutton();
			}
		});
		frame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cancelButton.setBounds(210, 120, 90, 25);
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeThis();
			}
		});
		frame.getContentPane().add(cancelButton);
	}
	private void clickconfirmbutton() {
		String moneyString = moneyField.getText();
		if(moneyString.length() == 0) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Please Type in Money", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else {
			Double money = Double.valueOf(moneyString);
			String result = new String();
			if(this.type.equals("Deposit")) {
				result = this.accountPage.Deposit(this.uuid, money.doubleValue());
			}else if(this.type.equals("Withdrew")) {
				result = this.accountPage.Withdrew(this.uuid, money.doubleValue());
			}
			if(result.equals(Common.Success)) {
				JOptionPane.showMessageDialog(frame.getContentPane(), result, "Opration Info",JOptionPane.WARNING_MESSAGE); 
				this.accountPage.refreshInfo();
				closeThis();
			}else {
				JOptionPane.showMessageDialog(frame.getContentPane(), result, "Opration Info",JOptionPane.WARNING_MESSAGE); 
			}
		}
	}
	private void closeThis() {
		frame.dispose();
	}
}
