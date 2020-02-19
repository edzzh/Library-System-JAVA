package pages;

import javax.swing.*;
import models.*;

@SuppressWarnings("serial")
public class ProfilePage {
	public static JPanel jPanel = new JPanel();
	private User user;
	
	ProfilePage(User user) {
		this.user = user;
	}
	
	public JComponent renderProfilePage () {
		return jPanel;
	}
}
