package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import AccountSystem.AccountSystem;
import Common.Common;
import Database.DatabaseConnection;
import javax.swing.JScrollPane;

public class SendPage {

	private JFrame frame;
	private JTextField moneyField;
	private AccountPage accountPage;
	private JTextArea reasonField;
	private JTextField receiverField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection con = DatabaseConnection.getConnection();
					SendPage window = new SendPage(new AccountPage("Bill","Customer",new AccountSystem(con),"0c936cc0-864b-4c53-afa6-5f6e7edc94bc"));
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SendPage(AccountPage accountPage) {
		this.accountPage = accountPage;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Make Transaction");
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int windowWidth = frame.getWidth();
		int windowHeight = frame.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel reveiveridLabel = new JLabel("Receiver ID: ");
		reveiveridLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		reveiveridLabel.setBounds(25, 25, 100, 25);
		frame.getContentPane().add(reveiveridLabel);
		
		JLabel reasonLabel = new JLabel("Transaction Reason:");
		reasonLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		reasonLabel.setBounds(26, 94, 150, 25);
		frame.getContentPane().add(reasonLabel);
		
		JLabel moneyLabel = new JLabel("Money:");
		moneyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		moneyLabel.setBounds(25, 60, 100, 25);
		frame.getContentPane().add(moneyLabel);
		
		receiverField = new JTextField();
		receiverField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		receiverField.setColumns(10);
		receiverField.setBounds(130, 25, 295, 25);
		frame.getContentPane().add(receiverField);
		
		moneyField = new JTextField();
		moneyField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		moneyField.setBounds(130, 60, 295, 25);
		frame.getContentPane().add(moneyField);
		moneyField.setColumns(10);
		moneyField.setDocument(new JTextFieldFilter(JTextFieldFilter.DOUBLE));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 130, 250, 75);
		frame.getContentPane().add(scrollPane);
		
		reasonField = new JTextArea();
		scrollPane.setViewportView(reasonField);
		reasonField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		reasonField.setColumns(10);
		reasonField.setLineWrap(true);
		
		JButton confirmButton = new JButton("Confirm");
		confirmButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		confirmButton.setBounds(105, 225, 90, 25);
		confirmButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickconfirmbutton();
			}
		});
		frame.getContentPane().add(confirmButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cancelButton.setBounds(245, 225, 90, 25);
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeThis();
			}
		});
		frame.getContentPane().add(cancelButton);

	}
	private void clickconfirmbutton() {
		String receiverString = receiverField.getText();
		String moneyString = moneyField.getText();
		String reasonString = reasonField.getText();
		if(receiverString.length() == 0) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Please Type in Receiver ID", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(moneyString.length() == 0) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Please Type in Money", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(reasonString.length() >= 100){
			JOptionPane.showMessageDialog(frame.getContentPane(), "Reason is too long", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(reasonString.equals(Common.TransName_Deposit)||
				reasonString.equals(Common.TransName_Loan)||
				reasonString.equals(Common.TransName_Repay)||
				reasonString.equals(Common.TransName_ServiceFee)||
				reasonString.equals(Common.TransName_trans)||
				reasonString.equals(Common.TransName_Withdraw)) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Reason is invalid", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else {
			Double money = Double.valueOf(moneyString);
			String result = new String();
			result = this.accountPage.Transaction(receiverString, money, reasonString);
			if(result.equals(Common.Success)) {
				JOptionPane.showMessageDialog(frame.getContentPane(), result, "Transaction Info",JOptionPane.WARNING_MESSAGE); 
				this.accountPage.refreshInfo();
				closeThis();
			}else {
				JOptionPane.showMessageDialog(frame.getContentPane(), result, "Transaction Info",JOptionPane.WARNING_MESSAGE); 
			}
		}
	}
	private void closeThis() {
		frame.dispose();
	}
}
