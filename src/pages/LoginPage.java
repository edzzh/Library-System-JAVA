package pages;

import models.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

@SuppressWarnings("serial")
public class LoginPage extends JFrame implements ActionListener {
	private JButton SUBMIT, REGISTER;
	private JPanel loginPanel;
	private JLabel usernameLabel, passwordLabel;
	private JTextField usernameTextField, passwordTextField;
	public User user;
	
	public LoginPage() {
	   setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	   
	   usernameLabel = new JLabel();
	   usernameLabel.setText("Username:");
	   usernameTextField = new JTextField(15);
	 
	   passwordLabel = new JLabel();
	   passwordLabel.setText("Password:");
	   passwordTextField = new JPasswordField(15);
	  
	   SUBMIT = new JButton("SUBMIT");
	   REGISTER = new JButton("REGISTER");
	   
	   loginPanel = new JPanel(new GridLayout(3,1));
	   loginPanel.add(usernameLabel);
	   loginPanel.add(usernameTextField);
	   loginPanel.add(passwordLabel);
	   loginPanel.add(passwordTextField);
	   loginPanel.add(REGISTER);
	   loginPanel.add(SUBMIT);
	   
	   add(loginPanel, BorderLayout.CENTER);
	   
	   SUBMIT.addActionListener(this);	   
	   REGISTER.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			RegisterPage registerPage = new RegisterPage();
			registerPage.setVisible(true);
		}
	   });
	   
	   setTitle("LOGIN FORM");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String username = usernameTextField.getText();
		String password = passwordTextField.getText();
		
		try {
			user = Database.loginInDatabase(username, password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (user != null) {
			this.dispose();
			try {
				new MainPage(user);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "Incorrect login or password", 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		try {
		  LoginPage loginPage = new LoginPage();
		  loginPage.setSize(400,100);
		  loginPage.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
