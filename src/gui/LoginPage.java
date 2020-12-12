package GUI;
import LoginSystem.LoginSystem;
import User.User;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JTextField;

import Common.Common;

import java.sql.Connection;


import Database.DatabaseConnection;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginPage {

	private JFrame frmBank;
	private JTextField userField;
	private JPasswordField pswField;
	private LoginSystem loginSystem;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection connection = DatabaseConnection.getConnection();
					LoginSystem loginSystem = new LoginSystem(connection);
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
		frmBank.setResizable(false);
		frmBank.getContentPane().setFont(new Font("宋体", Font.PLAIN, 16));
		frmBank.setTitle("Bank");
		frmBank.setBounds(100, 100, 200, 150);
		frmBank.setSize(new Dimension(340, 245));
		frmBank.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBank.getContentPane().setLayout(null);
		
		JLabel userJLabel = new JLabel("User:");
		userJLabel.setFont(new Font("宋体", Font.BOLD, 16));
		userJLabel.setBounds(40, 20, 80, 25);
		frmBank.getContentPane().add(userJLabel);
		
		JLabel pswJLabel = new JLabel("Password:");
		pswJLabel.setFont(new Font("宋体", Font.BOLD, 16));
		pswJLabel.setBounds(40, 65, 80, 25);
		frmBank.getContentPane().add(pswJLabel);
		
		userField = new JTextField();
		userField.setBounds(120, 20, 180, 25);
		frmBank.getContentPane().add(userField);
		userField.setColumns(10);
		
		pswField = new JPasswordField();
		pswField.setBounds(120, 65, 180, 25);
		frmBank.getContentPane().add(pswField);
		
		JRadioButton customerJRadioButton = new JRadioButton("Customer",true);
		buttonGroup.add(customerJRadioButton);
		customerJRadioButton.setFont(new Font("宋体", Font.PLAIN, 16));
		customerJRadioButton.setBounds(40, 110, 100, 25);
		frmBank.getContentPane().add(customerJRadioButton);
		
		JRadioButton manageJRadioButton = new JRadioButton("Manager");
		buttonGroup.add(manageJRadioButton);
		manageJRadioButton.setFont(new Font("宋体", Font.PLAIN, 16));
		manageJRadioButton.setBounds(40, 155, 100, 25);
		frmBank.getContentPane().add(manageJRadioButton);
		
		JButton loginJButton = new JButton("Login");
		loginJButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(customerJRadioButton.isSelected()) {

					String string = loginAsCustomer(userField.getText(), new String(pswField.getPassword()));
					JOptionPane.showMessageDialog(frmBank.getContentPane(), string, "LoginAsCustomer Info",JOptionPane.WARNING_MESSAGE); 
				}else {

					String string = loginAsManager(userField.getText(), new String(pswField.getPassword()));
					JOptionPane.showMessageDialog(frmBank.getContentPane(), string, "LoginAsManager Info",JOptionPane.WARNING_MESSAGE);
				}
				
			}
		});
		loginJButton.setFont(new Font("宋体", Font.PLAIN, 16));
		loginJButton.setBounds(180, 110, 100, 25);
		frmBank.getContentPane().add(loginJButton);
		
		JButton signupJButton = new JButton("Sign Up");
		signupJButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(customerJRadioButton.isSelected()) {

					String string = signup(userField.getText(), new String(pswField.getPassword()), "Customer");
					JOptionPane.showMessageDialog(frmBank.getContentPane(), string, "SignUpAsCustomer Info",JOptionPane.WARNING_MESSAGE); 
				}else {

					String string = signup(userField.getText(), new String(pswField.getPassword()), "Manager");
					JOptionPane.showMessageDialog(frmBank.getContentPane(), string, "SignUpAsManager Info",JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		signupJButton.setFont(new Font("宋体", Font.PLAIN, 16));
		signupJButton.setBounds(180, 155, 100, 25);
		frmBank.getContentPane().add(signupJButton);
	}
	private String loginAsCustomer(String username,String psw) {
		String result = Common.Failed;
		try {
			result = this.loginSystem.LoginAsUser(username, psw);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private String loginAsManager(String username,String psw) {
		String result = Common.Failed;
		try {
			result = this.loginSystem.LoginAsManager(username, psw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	private String signup(String username,String psw,String type) {
		String result = Common.Failed;
		try {
			result = this.loginSystem.SignupNewUser(username, psw, type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
