package GUI;
import LoginSystem.LoginSystem;
import User.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Point;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JToggleButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginPage {

	private JFrame frmBank;
	private JTextField textField;
	private JPasswordField passwordField;
	private LoginSystem loginSystem;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HashMap<String, String> Users = new HashMap<>();
					ArrayList<User> UsersObjects = new ArrayList<>();
					LoginSystem loginSystem = new LoginSystem(Users, UsersObjects);
					LoginPage window = new LoginPage(loginSystem);
					window.Show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}
	public LoginPage(LoginSystem loginSystem) {
		this.loginSystem = loginSystem;
		initialize();
	}
	public void Show() {
		this.frmBank.setVisible(true);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBank = new JFrame();
		frmBank.getContentPane().setFont(new Font("宋体", Font.PLAIN, 16));
		frmBank.setTitle("Bank");
		frmBank.setBounds(100, 100, 200, 150);
		frmBank.setSize(new Dimension(340, 245));
		frmBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBank.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 16));
		lblNewLabel.setBounds(40, 20, 80, 25);
		frmBank.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("宋体", Font.BOLD, 16));
		lblPassword.setBounds(40, 65, 80, 25);
		frmBank.getContentPane().add(lblPassword);
		
		textField = new JTextField();
		textField.setBounds(120, 20, 180, 25);
		frmBank.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(120, 65, 180, 25);
		frmBank.getContentPane().add(passwordField);
		
		JRadioButton rdbtnCustomer = new JRadioButton("Customer",true);
		buttonGroup.add(rdbtnCustomer);
		rdbtnCustomer.setFont(new Font("宋体", Font.PLAIN, 16));
		rdbtnCustomer.setBounds(40, 110, 100, 25);
		frmBank.getContentPane().add(rdbtnCustomer);
		
		JRadioButton rdbtnManager = new JRadioButton("Manager");
		buttonGroup.add(rdbtnManager);
		rdbtnManager.setFont(new Font("宋体", Font.PLAIN, 16));
		rdbtnManager.setBounds(40, 155, 100, 25);
		frmBank.getContentPane().add(rdbtnManager);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(rdbtnCustomer.isSelected()) {

					String string = loginAsCustomer(textField.getText(), new String(passwordField.getPassword()));
					JOptionPane.showMessageDialog(frmBank.getContentPane(), string, "LoginAsCustomer Info",JOptionPane.WARNING_MESSAGE); 
				}else {

					String string = loginAsManager(textField.getText(), new String(passwordField.getPassword()));
					JOptionPane.showMessageDialog(frmBank.getContentPane(), string, "LoginAsManager Info",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		btnLogin.setFont(new Font("宋体", Font.PLAIN, 16));
		btnLogin.setBounds(180, 110, 100, 25);
		frmBank.getContentPane().add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("宋体", Font.PLAIN, 16));
		btnSignUp.setBounds(180, 155, 100, 25);
		frmBank.getContentPane().add(btnSignUp);
	}
	private String loginAsCustomer(String username,String psw) {
		return this.loginSystem.LoginAsUser(username, psw);
	}
	private String loginAsManager(String username,String psw) {
		return this.loginSystem.LoginAsManager(username, psw);
	}
}
