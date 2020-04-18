package pages;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import models.Database;
import models.User;

@SuppressWarnings("serial")
public class RegisterPage extends JFrame implements ActionListener{
    JButton REGISTER;
    JLabel nameLabel, surnameLabel, usernameLabel, passwordLabel;
    JTextField nameTextField, surnameTextField, usernameTextField;
    JPasswordField passwordField;
    
    RegisterPage() {
    	setTitle("REGISTER FORM");
    	setSize(500, 500);
		
    	GridBagLayout g = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(g);
        c.insets = new Insets(0, 0, 10, 0);
        
        c.gridy = 0;
        nameLabel = new JLabel("Name *");
        add(nameLabel, c);
        
        c.gridy = 1;
        nameTextField = new JTextField(15);
        add(nameTextField, c);
        
        c.gridy = 2;
        surnameLabel = new JLabel("Surname *");
        add(surnameLabel, c);
        
        c.gridy = 3;
        surnameTextField = new JTextField(15);
        add(surnameTextField, c);
        
        c.gridy = 4;
        usernameLabel = new JLabel("Username *");
        add(usernameLabel, c);
        
        c.gridy = 5;
        usernameTextField = new JTextField(15);
        add(usernameTextField, c);
        
        c.gridy = 6;
        passwordLabel = new JLabel("Password *");
        add(passwordLabel, c);
        
        c.gridy = 7;
        passwordField = new JPasswordField(15);
        add(passwordField, c);
        
        c.gridy = 8;
        REGISTER = new JButton("REGISTER");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 0, 0);
        add(REGISTER, c);
        REGISTER.addActionListener(this);
    }

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			User user = new User(
				nameTextField.getText(), 
				surnameTextField.getText(), 
				usernameTextField.getText(),
				passwordField.getText()
			);
			
			Database.registerUserInDatabase(
					user.getName(), 
					user.getSurname(), 
					user.getUsername(), 
					user.getPassword(),
					user.getUserCode()
			);
			
			this.dispose();
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), 
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
