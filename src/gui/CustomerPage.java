package gui;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Account.Account;
import Database.DatabaseConnection;
import User.UserSystem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;


public class CustomerPage {
	
	private String username;
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tablemodel;
	private UserSystem userSystem;
	
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
					UserSystem userSystem = new UserSystem(connection);
					CustomerPage window = new CustomerPage("Bill",userSystem);
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
	public CustomerPage(String username, UserSystem userSystem) {
		this.username = username;
		this.userSystem = userSystem;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 600, 400);
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
		userJLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		userJLabel.setBounds(10, 10, 460, 25);
		frame.getContentPane().add(userJLabel);
		userJLabel.setText("User Name: " + this.username);
		
		JButton enterJButton = new JButton("Enter");
		enterJButton.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		enterJButton.setBounds(480, 45, 100, 25);
		enterJButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickenterbutton();
			}
		});
		frame.getContentPane().add(enterJButton);
		
		JButton createJButton = new JButton("Create");
		createJButton.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		createJButton.setBounds(480, 100, 100, 25);
		createJButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickcreatebutton();
			}
		});
		frame.getContentPane().add(createJButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 460, 300);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		String[] header = {"Account ID", "Account Type", "Balance", "Date"};
		tablemodel = new DefaultTableModel(null,header);
		refreshaccountlist();
		table.setModel(tablemodel);
		table.getColumnModel().getColumn(0).setPreferredWidth(75);
		table.getColumnModel().getColumn(1).setPreferredWidth(75);
//		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		scrollPane.setViewportView(table);
	}
	public void refreshaccountlist() {
		ArrayList<Account> accounts = new ArrayList<>();
		try {
			accounts = this.userSystem.ViewAccounts(this.username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int num = tablemodel.getRowCount();
		for(int i = num-1; i >= 0; i--) {
			tablemodel.removeRow(i);
		}
		for(int i = 0;i<accounts.size();i++) {
			Account account = accounts.get(i);
			tablemodel.addRow(new String[] {account.getUUID(),
											account.getType(),
											String.format("%.2f", account.getCurrentBalance()),
											account.getOpenTime()});
		}
	}
	private void clickcreatebutton() {
		new CreateAccountPage(username,this);
	}
	public String createnewaccount(String type,double money) {
		String resultString = new String();
		if(type.equals("Saving")) {
			try {
				resultString = this.userSystem.CreateSavingAccount(username, money);
			}catch (Exception e) {
				return String.valueOf(e);
			}
		}else if(type.equals("Checking")) {
			try {
				resultString = this.userSystem.CreateCheckingAccount(username, money);
			}catch (Exception e) {
				return String.valueOf(e);
			}
		}
		return resultString;
	}
	private void clickenterbutton() {
		int item = table.getSelectedRow();
		String uuidString = String.valueOf(tablemodel.getValueAt(item, 0));
		System.out.println(item);
		System.out.println(uuidString);
	}
}
