package pages;
import java.awt.*;
import javax.swing.*;

import models.*;

import java.awt.event.*;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class MainPage extends JPanel {	
	MainPage(User user) throws SQLException {
		super(new GridLayout(1,1));
		JFrame frame = new JFrame("Library UI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent libaryPanel = new LibraryPage().renderLibraryPanel(user);
		tabbedPane.addTab("Library", null, libaryPanel, "Library");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JComponent profilePanel = new ProfilePage(user).renderProfilePage();
		tabbedPane.addTab("Profile", null, profilePanel, "Profile");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		frame.add(this);
		frame.pack();
		frame.setSize(950, 500);
		frame.setVisible(true);
		
		add(tabbedPane);
	}
}
