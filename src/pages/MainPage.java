package pages;
import java.awt.*;
import javax.swing.*;

import models.*;

import java.awt.event.*;
import java.sql.SQLException;

public class MainPage {
	private JFrame frame = new JFrame("Library UI");
	private JTabbedPane tabbedPane = new JTabbedPane();

	public MainPage(User user) throws SQLException {
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLayout(new GridLayout(1,1));
		
		JComponent libaryPanel = new LibraryPage().renderLibraryPanel(user);
		tabbedPane.addTab("Library", null, libaryPanel, "Library");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		JComponent profilePanel = new ProfilePage(user).renderProfilePage();
		tabbedPane.addTab("Profile", null, profilePanel, "Profile");
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		JComponent informationPanel = new InformationPage().renderInformationPage();
		tabbedPane.addTab("Information", null, informationPanel, "Information");
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

		frame.add(tabbedPane);
		frame.setResizable(false);
		frame.setSize(950, 500);
		frame.setVisible(true);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Are you sure you want to close this window?", "Close Window?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	LoginPage login = new LoginPage();
		        	login.setSize(400,100);
		        	login.setVisible(true);
		        	
		            frame.dispose();
		        }
		    }
		});
	}
}
