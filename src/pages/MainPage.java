package pages;
import java.awt.*;
import javax.swing.*;

import model.Database;
import model.User;

import java.awt.event.*;
import java.sql.SQLException;

public class MainPage extends JPanel{
	private static final long serialVersionUID = -4582810412450985776L;
	private static User activeUser;
	
	public static JPanel panel = new JPanel();
	public static JScrollPane jScrollPane = new JScrollPane();
	public static JTable jBooksTable;
	
	public MainPage() throws SQLException {
		super(new GridLayout(1,1));
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel1 = makeLibraryPanel();
		tabbedPane.addTab("Library", null, panel1, "Library");
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		add(tabbedPane);
	}
	
	private JComponent makeLibraryPanel() throws SQLException {
		jBooksTable = createTable();
		jScrollPane.getViewport().add(jBooksTable);
        
        JButton addBook = new JButton("Add Book");
        addBook.setSize(40, 40);
        
        addBook.addActionListener((e) -> {
        	AddBookPage addBookPage = new AddBookPage();
        	addBookPage.setVisible(true);
        });
        
        JButton deleteBook = new JButton("Delete Book");
        deleteBook.setSize(40, 40);
        
        deleteBook.addActionListener((e) -> {
        	deleteBook();
        });
        
        
        panel.add(new JLabel("Hello, " + activeUser.getName().toUpperCase() + "!"));
        panel.add(jScrollPane);
        panel.add(addBook);
		panel.add(deleteBook);
        
		return panel;
	};
	
	private static JTable createTable() throws SQLException {
		String ColumnHeaderName[] = { "ISBN", "Year", "Author", "Title", "Rating", "Condition", "Rarity" };
		
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
	
	public static void deleteBook() {
		try {
			if (jBooksTable.getValueAt(jBooksTable.getSelectedRow(), jBooksTable.getSelectedColumn()) != null) {
				String ObjButtons[] = {"YES", "NO"};
				String ISBN = jBooksTable.getValueAt(jBooksTable.getSelectedRow(),0).toString();
				
				int PromptResult = JOptionPane.showOptionDialog(
						null, 
						"Are you sure you want to remove book - '" + jBooksTable.getValueAt(jBooksTable.getSelectedRow(),3) + "'?",
						"Delete Book",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.ERROR_MESSAGE,
						null,
						ObjButtons,
						ObjButtons[0]
				);
				
				if (PromptResult == 0) {
					try {
						Database.deleteBook(ISBN);
						reloadTable();
					} catch(Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					JOptionPane.showMessageDialog(
							null,
							"Record has been successfully removed.",
							"Comfirm Delete",
							JOptionPane.INFORMATION_MESSAGE
					);
				}
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(
					null,
					"Please select a record in the list to deleted.",
					"No Record Selected",
					JOptionPane.INFORMATION_MESSAGE
			);
		}
	}
	
	public static void reloadTable() throws SQLException {
		jScrollPane.getViewport().remove(jBooksTable);
		jBooksTable = createTable();
		jScrollPane.getViewport().add(jBooksTable);
		panel.repaint();
    }
	
	public static void showMainLibraryGUI(User user) throws SQLException {
		JFrame frame = new JFrame("Library UI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Assign active user globally
		activeUser = user;
		
		frame.add(new MainPage());
		
		frame.pack();
		frame.setSize(950, 500);
		frame.setVisible(true);
	}
}
