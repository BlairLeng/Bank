package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Account.Account;
import Common.Common;
import Database.DatabaseConnection;
import User.ManagerSystem;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class DailyReportPage {
	
	private ManagerSystem managerSystem;

	private JFrame frame;
	private JLabel depositField;
	private JLabel withdrawField;
	private JLabel servicefeeField;
	private JLabel transactionField;
	private JLabel loanField;
	private JLabel repayField;
	private JLabel deposittimeField;
	private JLabel withdrawtimeField;
	private JLabel servicefeetimeField;
	private JLabel transcationtimeField;
	private JLabel loantimeField;
	private JLabel repaytimeField;
	private JComboBox<String> currencyBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection connection = DatabaseConnection.getConnection();
					ManagerSystem managerSystem = new ManagerSystem(connection);
					DailyReportPage window = new DailyReportPage(managerSystem);
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
	public DailyReportPage(ManagerSystem managerSystem) {
		this.managerSystem = managerSystem;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Daily Report");
		frame.setResizable(false);
		frame.setBounds(100, 100, 610, 370);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int windowWidth = frame.getWidth();
		int windowHeight = frame.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		currencyBox = new JComboBox<>(new String[] {Common.CurrencyType_USD, Common.CurrencyType_CNY,Common.CurrencyType_EUR});
		currencyBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		currencyBox.setBounds(170, 20, 100, 25);
		currencyBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				refreshdata();
			}
		});
		frame.getContentPane().add(currencyBox);
		
		JLabel depositLabel = new JLabel("Total Deposit:");
		depositLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		depositLabel.setBounds(20, 64, 130, 25);
		frame.getContentPane().add(depositLabel);
		
		JLabel withdrawLabel = new JLabel("Total Withdraw: ");
		withdrawLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		withdrawLabel.setBounds(20, 108, 130, 25);
		frame.getContentPane().add(withdrawLabel);
		
		JLabel servicefeeLabel = new JLabel("Total Service Fee:");
		servicefeeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		servicefeeLabel.setBounds(20, 152, 130, 25);
		frame.getContentPane().add(servicefeeLabel);
		
		JLabel transactionLabel = new JLabel("Total Transaction: ");
		transactionLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		transactionLabel.setBounds(20, 196, 130, 25);
		frame.getContentPane().add(transactionLabel);
		
		JLabel loanLabel = new JLabel("Total Loan: ");
		loanLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loanLabel.setBounds(20, 240, 130, 25);
		frame.getContentPane().add(loanLabel);
		
		JLabel currencyLabel = new JLabel("Currenty Type: ");
		currencyLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		currencyLabel.setBounds(20, 20, 120, 25);
		frame.getContentPane().add(currencyLabel);
		
		JLabel repayLabel = new JLabel("Total Repay: ");
		repayLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
		repayLabel.setBounds(20, 284, 130, 25);
		frame.getContentPane().add(repayLabel);
		
		depositField = new JLabel("100.00");
		depositField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		depositField.setBounds(170, 64, 180, 25);
		frame.getContentPane().add(depositField);
		
		withdrawField = new JLabel("100.00");
		withdrawField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		withdrawField.setBounds(170, 108, 180, 25);
		frame.getContentPane().add(withdrawField);
		
		servicefeeField = new JLabel("5.00");
		servicefeeField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		servicefeeField.setBounds(170, 152, 180, 25);
		frame.getContentPane().add(servicefeeField);
		
		transactionField = new JLabel("700.00");
		transactionField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		transactionField.setBounds(170, 196, 180, 25);
		frame.getContentPane().add(transactionField);
		
		loanField = new JLabel("100.00");
		loanField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		loanField.setBounds(170, 240, 180, 25);
		frame.getContentPane().add(loanField);
		
		repayField = new JLabel("100.00");
		repayField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		repayField.setBounds(170, 284, 180, 25);
		frame.getContentPane().add(repayField);
		
		deposittimeField = new JLabel("5");
		deposittimeField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		deposittimeField.setBounds(400, 64, 100, 25);
		frame.getContentPane().add(deposittimeField);
		
		withdrawtimeField = new JLabel("5");
		withdrawtimeField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		withdrawtimeField.setBounds(400, 108, 100, 25);
		frame.getContentPane().add(withdrawtimeField);
		
		servicefeetimeField = new JLabel("5");
		servicefeetimeField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		servicefeetimeField.setBounds(400, 152, 100, 25);
		frame.getContentPane().add(servicefeetimeField);
		
		transcationtimeField = new JLabel("5");
		transcationtimeField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		transcationtimeField.setBounds(400, 196, 100, 25);
		frame.getContentPane().add(transcationtimeField);
		
		loantimeField = new JLabel("5");
		loantimeField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		loantimeField.setBounds(400, 240, 100, 25);
		frame.getContentPane().add(loantimeField);
		
		repaytimeField = new JLabel("5");
		repaytimeField.setFont(new Font("Segoe UI", Font.BOLD, 14));
		repaytimeField.setBounds(400, 284, 100, 25);
		frame.getContentPane().add(repaytimeField);
		
		JLabel timeLabel_1 = new JLabel("time(s)");
		timeLabel_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeLabel_1.setBounds(500, 64, 50, 25);
		frame.getContentPane().add(timeLabel_1);
		
		JLabel timeLabel = new JLabel("time(s)");
		timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeLabel.setBounds(500, 108, 50, 25);
		frame.getContentPane().add(timeLabel);
		
		JLabel timeLabel_2 = new JLabel("time(s)");
		timeLabel_2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeLabel_2.setBounds(500, 152, 50, 25);
		frame.getContentPane().add(timeLabel_2);
		
		JLabel timeLabel_3 = new JLabel("time(s)");
		timeLabel_3.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeLabel_3.setBounds(500, 196, 50, 25);
		frame.getContentPane().add(timeLabel_3);
		
		JLabel timeLabel_4 = new JLabel("time(s)");
		timeLabel_4.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeLabel_4.setBounds(500, 240, 50, 25);
		frame.getContentPane().add(timeLabel_4);
		
		JLabel timeLabel_5 = new JLabel("time(s)");
		timeLabel_5.setFont(new Font("Segoe UI", Font.PLAIN, 14));
		timeLabel_5.setBounds(500, 284, 50, 25);
		frame.getContentPane().add(timeLabel_5);
		
		refreshdata();
	}
	private void refreshdata() {
		HashMap<String, Double[][]> rpHashMap = new HashMap<>();
		String currencyType = (String)currencyBox.getSelectedItem();
		int currency = 0;
		switch(currencyType) {
		case Common.CurrencyType_CNY:
			currency = 0;
			break;
		case Common.CurrencyType_EUR:
			currency = 1;
			break;
		case Common.CurrencyType_USD:
			currency = 2;
			break;
		}
		try {
			rpHashMap = this.managerSystem.GetDayReport(LocalDate.now());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(frame.getContentPane(), String.valueOf(e), "Error",JOptionPane.WARNING_MESSAGE); 
			e.printStackTrace();
		}
		if(rpHashMap != null) {
			
			this.depositField.setText(String.format("%.2f",rpHashMap.get(Common.TransName_Deposit)[currency][0]));
			this.deposittimeField.setText(String.format("%.0f",rpHashMap.get(Common.TransName_Deposit)[currency][1]));
			
			this.withdrawField.setText(String.format("%.2f",rpHashMap.get(Common.TransName_Withdraw)[currency][0]));
			this.withdrawtimeField.setText(String.format("%.0f",rpHashMap.get(Common.TransName_Withdraw)[currency][1]));
			
			this.transactionField.setText(String.format("%.2f",rpHashMap.get(Common.TransName_trans)[currency][0]));
			this.transcationtimeField.setText(String.format("%.0f",rpHashMap.get(Common.TransName_trans)[currency][1]));
			
			this.servicefeeField.setText(String.format("%.2f",rpHashMap.get(Common.TransName_ServiceFee)[currency][0]));
			this.servicefeetimeField.setText(String.format("%.0f",rpHashMap.get(Common.TransName_ServiceFee)[currency][1]));
			
			this.loanField.setText(String.format("%.2f",rpHashMap.get(Common.TransName_Loan)[currency][0]));
			this.loantimeField.setText(String.format("%.0f",rpHashMap.get(Common.TransName_Loan)[currency][1]));
			
			this.repayField.setText(String.format("%.2f",rpHashMap.get(Common.TransName_Repay)[currency][0]));
			this.repaytimeField.setText(String.format("%.0f",rpHashMap.get(Common.TransName_Repay)[currency][1]));
		}
	}
}
