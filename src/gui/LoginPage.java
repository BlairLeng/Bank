package gui;

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

import javax.swing.JRadioButton;

public class LoginPage {

	private JFrame frmBank;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
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
		lblNewLabel.setPreferredSize(new Dimension(30, 25));
		lblNewLabel.setSize(new Dimension(30, 25));
		lblNewLabel.setLocation(new Point(20, 10));
		lblNewLabel.setBounds(40, 20, 80, 25);
		frmBank.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setSize(new Dimension(30, 25));
		lblPassword.setPreferredSize(new Dimension(30, 25));
		lblPassword.setLocation(new Point(20, 10));
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
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Customer");
		rdbtnNewRadioButton.setFont(new Font("宋体", Font.PLAIN, 16));
		rdbtnNewRadioButton.setBounds(40, 110, 100, 25);
		frmBank.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Manager");
		rdbtnNewRadioButton_1.setFont(new Font("宋体", Font.PLAIN, 16));
		rdbtnNewRadioButton_1.setBounds(40, 155, 100, 25);
		frmBank.getContentPane().add(rdbtnNewRadioButton_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(180, 110, 100, 25);
		frmBank.getContentPane().add(btnNewButton);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("宋体", Font.PLAIN, 16));
		btnSignUp.setBounds(180, 155, 100, 25);
		frmBank.getContentPane().add(btnSignUp);
	}
}
