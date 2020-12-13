package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

import Common.Common;
import Database.DatabaseConnection;
import User.UserSystem;

import javax.swing.JButton;

public class CreateAccountPage {

	private String username;
	private JFrame frmCreateAccount;
	private JTextField moneyField;
	private CustomerPage customerPage;
	JComboBox<String> typeBox;

	class JTextFieldFilter extends PlainDocument {
			public static final String DOUBLE = "0123456789.";
			protected String acceptedChars = null;
			protected boolean negativeAccepted = false;
			public JTextFieldFilter() {
				this(DOUBLE);
			}
	
			public JTextFieldFilter(String acceptedChars) {
				this.acceptedChars = acceptedChars;
			}
	
			// negativeAccepted: accept negative or not
			public void setNegativeAccepted(boolean negativeAccepted) {
				if (acceptedChars.equals(DOUBLE)) {
					this.negativeAccepted = negativeAccepted;
					acceptedChars += "-";
				}
			}
	
			public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
				for (int i = 0; i < str.length(); i++) {
					if (!acceptedChars.contains(str)) {
						return;
					}
				}
				// 判断当前输入的是否为".", 如果有了再判断前面有没有输入过
				if (acceptedChars.equals(DOUBLE) || (acceptedChars.equals(DOUBLE + "-") && negativeAccepted)) {
					if (str.contains(".")) {
						if (getText(0, getLength()).contains(".")) {
							return;
						}
					}
				}
				// 判断如果支持负数, 那么负号(-)必须在第一位
				if (negativeAccepted) {
					if (str.indexOf("-") != 0 || offset != 0) {
					return;
					}
				}
				super.insertString(offset, str, attr);
			}
		}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection connection = DatabaseConnection.getConnection();
					CustomerPage customerPage = new CustomerPage("Bill", new UserSystem(connection));
					CreateAccountPage window = new CreateAccountPage("Bill",customerPage);
					window.frmCreateAccount.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateAccountPage(String username, CustomerPage customerPage) {
		this.username = username;
		this.customerPage = customerPage;
		initialize();
		frmCreateAccount.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCreateAccount = new JFrame();
		frmCreateAccount.setTitle("Create Account");
		frmCreateAccount.setResizable(false);
		frmCreateAccount.setBounds(100, 100, 450, 300);
		frmCreateAccount.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCreateAccount.getContentPane().setLayout(null);
		
		int windowWidth = frmCreateAccount.getWidth();
		int windowHeight = frmCreateAccount.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frmCreateAccount.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel userJLabel = new JLabel("User Name: ");
		userJLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		userJLabel.setBounds(10, 10, 360, 25);
		frmCreateAccount.getContentPane().add(userJLabel);
		userJLabel.setText("User Name: " + this.username);
		
		typeBox = new JComboBox<>(new String[] {"Saving", "Checking"});
		typeBox.setFont(new Font("宋体", Font.PLAIN, 14));
		typeBox.setBounds(235, 70, 100, 25);
		frmCreateAccount.getContentPane().add(typeBox);
		
		JLabel typeLabel = new JLabel("Account Type:");
		typeLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		typeLabel.setBounds(95, 70, 100, 25);
		frmCreateAccount.getContentPane().add(typeLabel);
		
		JLabel moneyLabel = new JLabel("Money:");
		moneyLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		moneyLabel.setBounds(95, 120, 100, 25);
		frmCreateAccount.getContentPane().add(moneyLabel);
		
		moneyField = new JTextField();
		moneyField.setFont(new Font("宋体", Font.PLAIN, 14));
		moneyField.setBounds(235, 120, 100, 25);
		frmCreateAccount.getContentPane().add(moneyField);
		moneyField.setColumns(10);
		moneyField.setDocument(new JTextFieldFilter(JTextFieldFilter.DOUBLE));
		
		JButton createButton = new JButton("Create");
		createButton.setFont(new Font("宋体", Font.PLAIN, 14));
		createButton.setBounds(105, 190, 80, 25);
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickcreatebutton();
			}
		});
		frmCreateAccount.getContentPane().add(createButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("宋体", Font.PLAIN, 14));
		cancelButton.setBounds(245, 190, 80, 25);
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeThis();
			}
		});
		frmCreateAccount.getContentPane().add(cancelButton);
	}
	private void clickcreatebutton() {
		String accountType = (String)typeBox.getSelectedItem();
		String moneyString = moneyField.getText();
		if(moneyString.length() == 0) {
			JOptionPane.showMessageDialog(frmCreateAccount.getContentPane(), "Please Type in Initial Money", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else {
			Double money = Double.valueOf(moneyString);
			String result = this.customerPage.createnewaccount(accountType, money.doubleValue());
//			String string = "Account Type: " + accountTypeString + "\n" + "Money: " + String.valueOf(money);
//			System.out.print(accountTypeString);
//			System.out.print(money);
//			JOptionPane.showMessageDialog(frmCreateAccount.getContentPane(), string, "Create Account Info",JOptionPane.WARNING_MESSAGE); 
			if(result.equals(Common.Success)) {
				JOptionPane.showMessageDialog(frmCreateAccount.getContentPane(), "Create Success!", "Create Account Info",JOptionPane.WARNING_MESSAGE); 
				this.customerPage.refreshaccountlist();
				closeThis();
			}else {
				JOptionPane.showMessageDialog(frmCreateAccount.getContentPane(), result, "Create Account Info",JOptionPane.WARNING_MESSAGE); 
			}
		}
	}
	private void closeThis() {
		frmCreateAccount.dispose();
	}
}
