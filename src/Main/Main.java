package Main;

import java.awt.EventQueue;
import java.sql.Connection;

import Database.DatabaseConnection;
import LoginSystem.LoginSystem;
import gui.LoginPage;


public class Main {
	public static void main(String[] args) {
		startbanksystem();
	}
	public static void startbanksystem() {
		try {
			Connection connection = DatabaseConnection.getConnection();
			LoginSystem loginSystem = new LoginSystem(connection);
			LoginPage window = new LoginPage(loginSystem);
			window.Show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

