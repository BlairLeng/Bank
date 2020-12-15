package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Account.Account;
import AccountSystem.AccountSystem;
import Database.DatabaseConnection;
import TransactionSystem.Transaction;
import User.ManagerSystem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManagerPage {

	private String username;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tablemodel;
	private ManagerSystem managerSystem;
	
	public String getUsername() {
		return username;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection connection = DatabaseConnection.getConnection();
					ManagerSystem managerSystem = new ManagerSystem(connection);
					ManagerPage window = new ManagerPage("root",managerSystem);
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
	public ManagerPage(String username,ManagerSystem managerSystem) {
		this.username = username;
		this.managerSystem = managerSystem;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 850, 400);
		frame.setTitle("Bank");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int windowWidth = frame.getWidth();
		int windowHeight = frame.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel userJLabel = new JLabel("User Name: ");
		userJLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		userJLabel.setBounds(10, 10, 650, 25);
		frame.getContentPane().add(userJLabel);
		userJLabel.setText("User Name: " + this.username);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		refreshButton.setBounds(670, 10, 150, 25);
		refreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshaccountlist();
			}
		});
		frame.getContentPane().add(refreshButton);
		
		JButton enterButton = new JButton("Enter");
		enterButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		enterButton.setBounds(670, 60, 150, 25);
		enterButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickEnterbutton();
			}
		});
		frame.getContentPane().add(enterButton);
		
		JButton usertransactionButton = new JButton("User Transaction");
		usertransactionButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		usertransactionButton.setBounds(670, 120, 150, 25);
		usertransactionButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickUserTransactionbutton();
			}
		});
		frame.getContentPane().add(usertransactionButton);
		
		JButton dailyreportButton = new JButton("Daily Report");
		dailyreportButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		dailyreportButton.setBounds(670, 180, 150, 25);
		dailyreportButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickDailyReportbutton();
			}
		});
		frame.getContentPane().add(dailyreportButton);
		
		JButton loanrequesetButton = new JButton("Loan Request");
		loanrequesetButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		loanrequesetButton.setBounds(670, 240, 150, 25);
		loanrequesetButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickLoanRequestbutton();
			}
		});
		frame.getContentPane().add(loanrequesetButton);
		
		JButton stocksystemButton = new JButton("Stock System");
		stocksystemButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		stocksystemButton.setBounds(670, 300, 150, 25);
		stocksystemButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickStockSystembutton();
			}
		});
		frame.getContentPane().add(stocksystemButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 650, 300);
		frame.getContentPane().add(scrollPane);
		
		//table = new JTable();
		// set cell not editable
		table = new JTable(){
			public boolean isCellEditable(int row, int column){
				return false;
		    }
		}; 
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		String[] header = {"Account ID","User Name", "Account Type","Currency", "Balance", "Date","Loan"};
		tablemodel = new DefaultTableModel(null,header);
		refreshaccountlist();
		table.setModel(tablemodel);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		scrollPane.setViewportView(table);
	}
	public void refreshaccountlist() {
		ArrayList<Account> accounts = new ArrayList<>();
		try {
			accounts = this.managerSystem.Allaccounts();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int num = tablemodel.getRowCount();
		for(int i = num-1; i >= 0; i--) {
			tablemodel.removeRow(i);
		}
		for(int i = 0;i<accounts.size();i++) {
			Account account = accounts.get(i);
			tablemodel.addRow(new String[] {account.getUUID(),
											account.getCustomerName(),
											account.getType(),
											account.getCurrencyType(),
											String.format("%.2f", account.getCurrentBalance()),
											account.getOpenTime(),
											"0"});
		}
	}
	public void clickEnterbutton() {
		int item = table.getSelectedRow();
		String uuidString = String.valueOf(tablemodel.getValueAt(item, 0));
		new AccountPage(this.username,"Manager",new AccountSystem(this.managerSystem.conn),uuidString);
	}
	public void clickUserTransactionbutton() {
		int item = table.getSelectedRow();
		String usernameString = String.valueOf(tablemodel.getValueAt(item, 1));
		ArrayList<Transaction> transactions = new ArrayList<>();
		try {
			transactions = this.managerSystem.Usertrans(usernameString);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame.getContentPane(), String.valueOf(e), "Error",JOptionPane.WARNING_MESSAGE); 
			System.out.println(e);
			return;
		}
		new TransactionPage(transactions,usernameString,"Username");
	}
	public void clickDailyReportbutton() {
		int item = table.getSelectedRow();
		String usernameString = String.valueOf(tablemodel.getValueAt(item, 1));
		ArrayList<String> report = new ArrayList<>();
		try {
			report = this.managerSystem.GetDayReport(LocalDate.now());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(frame.getContentPane(), String.valueOf(e), "Error",JOptionPane.WARNING_MESSAGE); 
			System.out.println(e);
			return;
		}
	}
	public void clickLoanRequestbutton() {
		
	}
	public void clickStockSystembutton() {
		
	}
}
