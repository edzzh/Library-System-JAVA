package pages;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import models.Database;
import models.User;

@SuppressWarnings("serial")
public class RegisterPage extends JFrame implements ActionListener{
    JButton register;
    JLabel nameLabel, surnameLabel, usernameLabel, passwordLabel;
    JTextField name, surname, username;
    JPasswordField password;
    
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
        name = new JTextField(15);
        add(name, c);
        
        c.gridy = 2;
        surnameLabel = new JLabel("Surname *");
        add(surnameLabel, c);
        
        c.gridy = 3;
        surname = new JTextField(15);
        add(surname, c);
        
        c.gridy = 4;
        usernameLabel = new JLabel("Username *");
        add(usernameLabel, c);
        
        c.gridy = 5;
        username = new JTextField(15);
        add(username, c);
        
        c.gridy = 6;
        passwordLabel = new JLabel("Password *");
        add(passwordLabel, c);
        
        c.gridy = 7;
        password = new JPasswordField(15);
        add(password, c);
        
        c.gridy = 8;
        register = new JButton("REGISTER");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 0, 0);
        add(register, c);
        register.addActionListener(this);
    }

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			User user = new User(
				name.getText(), 
				surname.getText(), 
				username.getText(),
				password.getText()
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
