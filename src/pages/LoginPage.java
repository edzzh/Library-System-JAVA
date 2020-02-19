package pages;

import model.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	JButton SUBMIT, REGISTER;
	JPanel panel;
	JLabel label1, label2;
	final JTextField text1, text2;
	User user;
	
	LoginPage() {
	   setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	   
	   label1 = new JLabel();
	   label1.setText("Username:");
	   text1 = new JTextField(15);
	 
	   label2 = new JLabel();
	   label2.setText("Password:");
	   text2 = new JPasswordField(15);
	  
	   SUBMIT=new JButton("SUBMIT");
	   SUBMIT.setMnemonic(KeyEvent.VK_ENTER);
	   
	   REGISTER=new JButton("REGISTER");
	   
	   panel = new JPanel(new GridLayout(3,1));
	   panel.add(label1);
	   panel.add(text1);
	   panel.add(label2);
	   panel.add(text2);
	   panel.add(REGISTER);
	   panel.add(SUBMIT);
	   add(panel,BorderLayout.CENTER);
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
		String username=text1.getText();
		String password=text2.getText();
		
		try {
			try {
				user = Database.loginInDatabase(username, password);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		if (user != null) {
			this.dispose();
			try {
				MainPage.showMainLibraryGUI(user);
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
		  LoginPage frame=new LoginPage();
		  frame.setSize(400,100);
		  frame.setVisible(true);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
}
