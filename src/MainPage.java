import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainPage extends JPanel{
	private static final long serialVersionUID = -4582810412450985776L;
	private static User activeUser;
	
	public MainPage() {
		super(new GridLayout(1,1));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel1 = makeLibraryPanel();
		tabbedPane.addTab("Library", null, panel1, "Library");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		add(tabbedPane);
	}
	
	private JComponent makeLibraryPanel() {
		JPanel panel = new JPanel();
		
		JLabel welcomeLabel = new JLabel("Hello, " + activeUser.getName().toUpperCase() + "!");
		
		String [] columnNames = {"ISBN", "Year", "Author", "Title", "Rating", "Condition", "Rarity"};
		
//		Object [][] data = {
//				{
//					"ISBN11111", (short) 2019, "Edgars", "DAMN", (short) 9, "Normal", "Legendary"
//				}
//		};
		
		Object [][] data = {};
		
		JTable table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(900, 300));
		table.setFillsViewportHeight(true);
		
        JScrollPane scrollPane = new JScrollPane(table);
        
        JButton addBook = new JButton("Add Book");
        addBook.setSize(40, 40);
        
        addBook.addActionListener((e) -> {
        	AddBookPage addBookPage = new AddBookPage();
        	addBookPage.setVisible(true);
        });
        
        JButton deleteBook = new JButton("Delete Book");
        addBook.setSize(40, 40);
        
        deleteBook.addActionListener((e) -> {
        	System.out.println("Book deleted");
        });
        
        panel.add(welcomeLabel);
        panel.add(scrollPane);
        panel.add(addBook);
		panel.add(deleteBook);
        
		return panel;
	};
	
	public static void showMainLibraryGUI(User user) {
		JFrame frame = new JFrame("Library UI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Assign active user globally
		activeUser = user;
		
		frame.add(new MainPage());
		
		frame.pack();
		frame.setSize(1000, 500);
		frame.setVisible(true);
	}
}
