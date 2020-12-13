package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Database.DatabaseConnection;
import User.ManagerSystem;
import User.UserSystem;

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
					ManagerPage window = new ManagerPage("Bill",managerSystem);
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
		frame.setBounds(100, 100, 610, 400);
		frame.setTitle("Bank");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel userJLabel = new JLabel("User Name: ");
		userJLabel.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		userJLabel.setBounds(10, 10, 460, 25);
		frame.getContentPane().add(userJLabel);
		userJLabel.setText("User Name: " + this.username);
		
		JButton enterJButton = new JButton("Enter");
		enterJButton.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		enterJButton.setBounds(480, 45, 100, 25);
		frame.getContentPane().add(enterJButton);
		
		JButton stockJButton = new JButton("Stock");
		stockJButton.setFont(new Font("ËÎÌו", Font.PLAIN, 16));
		stockJButton.setBounds(480, 100, 100, 25);
		frame.getContentPane().add(stockJButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 460, 300);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		String[] header = {"Account ID","User Name", "Account Type", "Balance", "Date","Loan"};
		tablemodel = new DefaultTableModel(null,header);
		
		table.setModel(tablemodel);
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		scrollPane.setViewportView(table);
	}

}
