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
import javax.swing.JTextField;

import Common.Common;
import Database.DatabaseConnection;
import LoanSystem.LoanSystem;

public class RepayLoanPage {

	private JFrame frame;
	private JTextField moneyField;
	private LoanInfoPage loanInfoPage;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection con = DatabaseConnection.getConnection();
					LoanSystem loanSystem = new LoanSystem(con);
					LoanInfoPage loanInfoPage = new LoanInfoPage("Customer", "09df7199-dfbd-4b7f-9e44-fe7b6da7fe9f", loanSystem);
					RepayLoanPage window = new RepayLoanPage(loanInfoPage);
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
	public RepayLoanPage(LoanInfoPage loanInfoPage) {
		this.loanInfoPage = loanInfoPage;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Repay Loan");
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
		
		JLabel repayLabel = new JLabel("Repay Amount: ");
		repayLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
		repayLabel.setBounds(20, 20, 150, 25);
		frame.getContentPane().add(repayLabel);

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
	private void clickconfirmbutton() {
		String moneyString = moneyField.getText();
		if(moneyString.length() == 0) {
			JOptionPane.showMessageDialog(frame.getContentPane(), "Please Type in Money", "Warning",JOptionPane.WARNING_MESSAGE); 
		}else {
			Double money = Double.valueOf(moneyString);
			String result = new String();
			result = this.loanInfoPage.repay(money.doubleValue());
			if(result.equals(Common.Success)) {
				JOptionPane.showMessageDialog(frame.getContentPane(), result, "Opration Info",JOptionPane.WARNING_MESSAGE); 
				this.loanInfoPage.refreshloanlist();
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
