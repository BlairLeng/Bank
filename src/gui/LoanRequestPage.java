package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Database.DatabaseConnection;
import LoanSystem.Loan;
import LoanSystem.LoanSystem;
import User.ManagerSystem;

public class LoanRequestPage {

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tablemodel;
	private String username;
	private ManagerSystem managerSystem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection connection = DatabaseConnection.getConnection();
					ManagerSystem managerSystem = new ManagerSystem(connection);
					LoanRequestPage window = new LoanRequestPage("root",managerSystem);
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
	public LoanRequestPage(String username,ManagerSystem managerSystem) {
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
		frame.setBounds(100, 100, 600, 400);

		
		frame.setTitle("Bank");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		userJLabel.setBounds(10, 10, 460, 25);
		frame.getContentPane().add(userJLabel);
		userJLabel.setText("User Name: " + this.username);
		
		JButton approveButton = new JButton("Approve");
		approveButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		approveButton.setBounds(480, 45, 100, 25);
		approveButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clickapprovebutton();
			}
		});
		frame.getContentPane().add(approveButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 460, 300);
		frame.getContentPane().add(scrollPane);
		
		//table = new JTable();
		// set cell not editable
		table = new JTable(){
			public boolean isCellEditable(int row, int column){
				return false;
            }
		}; 
		table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		String[] header = {"Loan ID","Loan Name","Loan Reason","Collateral","Begin Date","End Date","Money Loaned","Money Returned","MoneyOwed","Interests Rate","Status"};
		tablemodel = new DefaultTableModel(null,header);
		refreshloanlist();
		table.setModel(tablemodel);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(150);
		table.getColumnModel().getColumn(9).setPreferredWidth(100);
		table.getColumnModel().getColumn(10).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
	}
	public void refreshloanlist() {
		ArrayList<Loan> loans = new ArrayList<>();
		try {
			loans = this.managerSystem.UnapprovedLoan();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int num = tablemodel.getRowCount();
		for(int i = num-1; i >= 0; i--) {
			tablemodel.removeRow(i);
		}
		for(int i = 0;i<loans.size();i++) {
			Loan loan = loans.get(i);
			int status = loan.getStatus();
			String statuString = new String();
			if(status == 0) {
				statuString = "Processing";
			}else if(status == 1) {
				statuString = "Completed";
			}else if(status == -1) {
				statuString = "Unapproved";
			}
			tablemodel.addRow(new String[] {loan.getID(),
											loan.getLoanName(),
											loan.getLoanReason(),
											loan.getCollateral(),
											loan.getBeginDate(),
											loan.getEndDate(),
											String.format("%.2f", loan.getMoneyLoaned()),
											String.format("%.2f", loan.getMoneyReturned()),
											String.format("%.2f", loan.getMoneyOwed()),
											String.format("%.4f", loan.getInterestRate()),
											statuString});
		}
	}
	private void clickapprovebutton() {
		String resultString = new String();
		int item = table.getSelectedRow();
		String loanid = String.valueOf(tablemodel.getValueAt(item, 0));
		if(loanid.length() == 0) {
			resultString = "Please Select a Loan!";
		}else {
			try {
				resultString = this.managerSystem.ApproveLoan(loanid);
			}catch (Exception e) {
				resultString = String.valueOf(e);
			}
			refreshloanlist();
			JOptionPane.showMessageDialog(frame.getContentPane(), resultString, "Warning",JOptionPane.WARNING_MESSAGE); 
		}
	}

}
