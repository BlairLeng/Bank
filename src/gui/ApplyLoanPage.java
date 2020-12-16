package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import AccountSystem.AccountSystem;
import Common.Common;
import Database.DatabaseConnection;

public class ApplyLoanPage {

	private JFrame frmLoanApplication;
	private JTextField moneyField;
	private AccountPage accountPage;
	private JTextArea collateralField;
	private JTextField timeField;
	private JTextField loannameField;
	private JTextField interestsField;
	private JTextArea loanreasonField;
	private JLabel moneytoreturnLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection con = DatabaseConnection.getConnection();
					ApplyLoanPage window = new ApplyLoanPage(new AccountPage("Bill","Customer",new AccountSystem(con),"0c936cc0-864b-4c53-afa6-5f6e7edc94bc"));
					window.frmLoanApplication.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ApplyLoanPage(AccountPage accountPage) {
		this.accountPage = accountPage;
		initialize();
		frmLoanApplication.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLoanApplication = new JFrame();
		frmLoanApplication.setTitle("Loan Application");
		frmLoanApplication.setResizable(false);
		frmLoanApplication.setBounds(100, 100, 450, 575);
		frmLoanApplication.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmLoanApplication.getContentPane().setLayout(null);
		
		int windowWidth = frmLoanApplication.getWidth();
		int windowHeight = frmLoanApplication.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frmLoanApplication.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel loannameLabel = new JLabel("Loan Name: ");
		loannameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loannameLabel.setBounds(25, 20, 100, 25);
		frmLoanApplication.getContentPane().add(loannameLabel);
		
		JLabel moneyLabel = new JLabel("Money:");
		moneyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		moneyLabel.setBounds(25, 55, 100, 25);
		frmLoanApplication.getContentPane().add(moneyLabel);
		
		JLabel timeLabel = new JLabel("Time Period: ");
		timeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		timeLabel.setBounds(25, 90, 100, 25);
		frmLoanApplication.getContentPane().add(timeLabel);
		
		JLabel dayLabel = new JLabel("Day(s)");
		dayLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		dayLabel.setBounds(375, 90, 50, 25);
		frmLoanApplication.getContentPane().add(dayLabel);
		
		JLabel interestLabel = new JLabel("Month Interest Rate:");
		interestLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		interestLabel.setBounds(25, 125, 150, 25);
		frmLoanApplication.getContentPane().add(interestLabel);
		
		moneytoreturnLabel = new JLabel("0");
		moneytoreturnLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		moneytoreturnLabel.setBounds(215, 160, 200, 25);
		frmLoanApplication.getContentPane().add(moneytoreturnLabel);
		
		JLabel loanreasonLabel = new JLabel("Loan Reason:");
		loanreasonLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loanreasonLabel.setBounds(25, 185, 200, 25);
		frmLoanApplication.getContentPane().add(loanreasonLabel);
		
		JLabel collateralLabel = new JLabel("Collateral Description: ");
		collateralLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		collateralLabel.setBounds(25, 325, 200, 25);
		frmLoanApplication.getContentPane().add(collateralLabel);
		
		
		loannameField = new JTextField();
		loannameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		loannameField.setColumns(10);
		loannameField.setBounds(130, 20, 285, 25);
		frmLoanApplication.getContentPane().add(loannameField);
		
		moneyField = new JTextField();
		moneyField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		moneyField.setBounds(130, 55, 285, 25);
		frmLoanApplication.getContentPane().add(moneyField);
		moneyField.setColumns(10);
		moneyField.setDocument(new JTextFieldFilter(JTextFieldFilter.DOUBLE));
		
		timeField = new JTextField();
		timeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeField.setColumns(10);
		timeField.setBounds(130, 90, 240, 25);
		timeField.setDocument(new JTextFieldFilter(JTextFieldFilter.INTEGER));
		frmLoanApplication.getContentPane().add(timeField);
		
		interestsField = new JTextField();
		interestsField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		interestsField.setColumns(10);
		interestsField.setBounds(175, 125, 240, 25);
		interestsField.setDocument(new JTextFieldFilter(JTextFieldFilter.DOUBLE));
		frmLoanApplication.getContentPane().add(interestsField);
		
		JScrollPane collateralScrollPane = new JScrollPane();
		collateralScrollPane.setBounds(100, 360, 250, 100);
		frmLoanApplication.getContentPane().add(collateralScrollPane);
		
		collateralField = new JTextArea();
		collateralScrollPane.setViewportView(collateralField);
		collateralField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		collateralField.setColumns(10);
		collateralField.setLineWrap(true);
		
		JScrollPane loanreasonScrollPane = new JScrollPane();
		loanreasonScrollPane.setBounds(100, 220, 250, 100);
		frmLoanApplication.getContentPane().add(loanreasonScrollPane);
		
		loanreasonField = new JTextArea();
		loanreasonField.setLineWrap(true);
		loanreasonField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		loanreasonField.setColumns(10);
		loanreasonScrollPane.setViewportView(loanreasonField);
		
		
		
		JButton moneytoreturnButton = new JButton("Money To Return:");
		moneytoreturnButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		moneytoreturnButton.setBounds(24, 160, 150, 25);
		moneytoreturnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickreturnbutton();
			}
		});
		frmLoanApplication.getContentPane().add(moneytoreturnButton);
		
		JButton applyButton = new JButton("Apply");
		applyButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		applyButton.setBounds(100, 480, 90, 25);
		applyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickapplybutton();
			}
		});
		frmLoanApplication.getContentPane().add(applyButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		cancelButton.setBounds(260, 480, 90, 25);
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				closeThis();
			}
		});
		frmLoanApplication.getContentPane().add(cancelButton);
		
		
		
		

	}
	private void clickapplybutton(){
		String loannameString = loannameField.getText();
		String moneyString = moneyField.getText();
		String timeString = timeField.getText();
		String intereString = interestsField.getText();
		String reasonString = loanreasonField.getText();
		String collateralString = collateralField.getText();
		if(moneyString.length() == 0) {
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Please Type in Money", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(timeString.length() == 0) {
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Please Type in Time Period", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(intereString.length() == 0){
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Please Type in Interests Rate", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(Double.valueOf(intereString) <= 1.001){
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Interests Rate must greater than 1.001", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(reasonString.length() >= 100){
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Loan Reason is too long", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(collateralString.length() >= 100){
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Collateral Description is too long", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else{
			Double money = Double.valueOf(moneyString);
			Double interests = Double.valueOf(intereString);
			Integer time = Integer.valueOf(timeString);
			String result = new String();
			result = this.accountPage.ApplyLoan(money, time, interests, loannameString, reasonString, collateralString);
			if(result.equals(Common.Success)) {
				JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), result, "Loan Application Info",JOptionPane.WARNING_MESSAGE); 
				this.accountPage.refreshInfo();
				closeThis();
			}else {
				JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), result, "Loan Application Info",JOptionPane.WARNING_MESSAGE); 
			}
		}
	}
	private void clickreturnbutton() {
		String moneyString = moneyField.getText();
		String timeString = timeField.getText();
		String intereString = interestsField.getText();
		if(moneyString.length() == 0) {
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Please Type in Money", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(timeString.length() == 0) {
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Please Type in Time Period", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(intereString.length() == 0){
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Please Type in Interests Rate", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else if(Double.valueOf(intereString) <= 1.001){
			JOptionPane.showMessageDialog(frmLoanApplication.getContentPane(), "Interests Rate must greater than 1.001", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else{
			Double money = Double.valueOf(moneyString);
			Double interests = Double.valueOf(intereString);
			Integer time = Integer.valueOf(timeString);
			double MoneyOwed = money * Math.pow(interests, time);
			moneytoreturnLabel.setText(String.format("%.2f", MoneyOwed));
		}
	}
	private void closeThis() {
		frmLoanApplication.dispose();
	}
}
