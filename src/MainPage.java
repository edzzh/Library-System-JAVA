import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class MainPage extends JPanel{
	private static final long serialVersionUID = -4582810412450985776L;
	private static User activeUser;
	
	public static JTable jBooksTabel;
	public static JScrollPane jScrollPane = new JScrollPane();
	public static JPanel panel = new JPanel();
	
	public MainPage() throws SQLException {
		super(new GridLayout(1,1));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel1 = makeLibraryPanel();
		tabbedPane.addTab("Library", null, panel1, "Library");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		add(tabbedPane);
	}
	
	private JComponent makeLibraryPanel() throws SQLException {
		jBooksTabel = createTable();
		jScrollPane.getViewport().add(jBooksTabel);
        
        JButton addBook = new JButton("Add Book");
        addBook.setSize(40, 40);
        
        addBook.addActionListener((e) -> {
        	AddBookPage addBookPage = new AddBookPage();
        	addBookPage.setVisible(true);
        });
        
        JButton deleteBook = new JButton("Delete Book");
        deleteBook.setSize(40, 40);
        
        deleteBook.addActionListener((e) -> {
        	System.out.println("Book deleted");
        });
        
        
        panel.add(new JLabel("Hello, " + activeUser.getName().toUpperCase() + "!"));
        panel.add(jScrollPane);
        panel.add(addBook);
		panel.add(deleteBook);
        
		return panel;
	};
	
	private static JTable createTable() throws SQLException {
		String ColumnHeaderName[] = { 
				"ISBN", "Year", "Author", "Title", "Rating", "Condition", "Rarity" };
		
		String[][] books = Database.getBooks();
		
		@SuppressWarnings("serial")
		JTable newTable = new JTable(books, ColumnHeaderName) {
    		public boolean isCellEditable (int iRows, int iCols) {
    			return false;
    		}
    	};
    	
    	newTable.setPreferredScrollableViewportSize(new Dimension(900, 300));
    	newTable.setFillsViewportHeight(true);
    	
    	newTable.getColumnModel().getColumn(0).setMinWidth(0);
    	newTable.getColumnModel().getColumn(0).setPreferredWidth(100);
    	newTable.getColumnModel().getColumn(1).setPreferredWidth(100);
    	newTable.getColumnModel().getColumn(2).setPreferredWidth(150);
    	newTable.getColumnModel().getColumn(3).setPreferredWidth(100);
    	newTable.getColumnModel().getColumn(4).setPreferredWidth(100);
    	newTable.getColumnModel().getColumn(5).setPreferredWidth(100);
    	newTable.getColumnModel().getColumn(6).setPreferredWidth(100);
        
        return newTable;
	}
	
	public static void reloadTable() throws SQLException {
		jScrollPane.getViewport().remove(jBooksTabel);
		jBooksTabel = createTable();
		jScrollPane.getViewport().add(jBooksTabel);
		panel.repaint();
    }
	
	public static void showMainLibraryGUI(User user) throws SQLException {
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
