package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import TransactionSystem.Transaction;

public class TransactionPage {

	private JFrame frame;
	private String uuid;
	private JTable table;
	private DefaultTableModel tablemodel;
	private ArrayList<Transaction> transactions;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayList<Transaction> transactions = new ArrayList<>();
					TransactionPage window = new TransactionPage(transactions);
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
	public TransactionPage(ArrayList<Transaction> transactions) {
		this.transactions = transactions;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 770, 380);
		frame.setResizable(false);
		frame.setTitle("Transaction");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int windowWidth = frame.getWidth();
		int windowHeight = frame.getHeight();
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);
		
		JLabel userJLabel = new JLabel("Account ID: ");
		userJLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		userJLabel.setBounds(10, 10, 750, 25);
		frame.getContentPane().add(userJLabel);
		userJLabel.setText("Account ID: " + this.uuid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 750, 300);
		frame.getContentPane().add(scrollPane);
		
		//table = new JTable();
		// set cell not editable
		table = new JTable(){
			public boolean isCellEditable(int row, int column){
				return false;
            }
		}; 
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		//table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		String[] header = {"Trans ID", "Trans Reason","Send ID", "Receiver ID", "Money","Date Time"};
		tablemodel = new DefaultTableModel(null,header);
		loadtrans();
		table.setModel(tablemodel);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
	}
	public void loadtrans() {
		int num = tablemodel.getRowCount();
		for(int i = num-1; i >= 0; i--) {
			tablemodel.removeRow(i);
		}
		for(int i = 0;i<this.transactions.size();i++) {
			Transaction transaction = this.transactions.get(i);
			tablemodel.addRow(new String[] {transaction.gettransUUID(),
											transaction.gettransname(),
											transaction.getsenderUUID(),
											transaction.getreceiverUUID(),
											String.format("%.2f", transaction.getmoney()),
											transaction.getTransTime()});
		}
	}
}
