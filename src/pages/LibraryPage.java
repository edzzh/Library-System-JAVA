package pages;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import models.*;

@SuppressWarnings("serial")
public class LibraryPage {
	private static JScrollPane jBookScrollPane = new JScrollPane();
	private static JPanel jPanel = new JPanel();
	private static JTable jBooksTable;
	
    private JButton addBookButton = new JButton("Add Book");
    private JButton deleteBookButton = new JButton("Delete Book");
    private JButton addBookToUserListButton = new JButton("Add To Book List");
    	
	public JComponent renderLibraryPanel (User user) throws SQLException {
		JLabel welcomeLabel = new JLabel("Hello, " + user.getName().toUpperCase() + "!");
		
		addBookButton.setSize(40, 40);
		addBookButton.addActionListener((e) -> {
        	AddBookPage addBookPage = new AddBookPage();
        	addBookPage.setVisible(true);
        });
		
		deleteBookButton.setSize(40, 40);      
        deleteBookButton.addActionListener((e) -> {
        	deleteBook(user);
        });
        
        addBookToUserListButton.setSize(40, 40);
        addBookToUserListButton.addActionListener((e) -> {
        	addBookToUserBookList(user);
        });
        
        jPanel.add(welcomeLabel);
        
        jBooksTable = createBooksTable();
        jBookScrollPane.getViewport().add(jBooksTable);
        jPanel.add(jBookScrollPane);
        
        jPanel.add(addBookButton);
        
        jPanel.add(deleteBookButton);
        
        jPanel.add(addBookToUserListButton);
        
        return jPanel;
	}
	
	public static JTable createBooksTable() throws SQLException {
		String ColumnHeaderName[] = { "ISBN", "Year", "Author", "Title", "Rating", "Condition", "Rarity", "Availability" };
		
		String[][] books = Database.getBooks();
		
		JTable booksTable = new JTable(books, ColumnHeaderName) {
    		public boolean isCellEditable (int iRows, int iCols) {
    			return false;
    		}
    	};
    	
    	booksTable.setPreferredScrollableViewportSize(new Dimension(900, 200));
    	booksTable.setFillsViewportHeight(true);
        
        return booksTable;
	}
	
	private void deleteBook(User user) {
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
						reloadLibraryBookTable();
						ProfilePage.reloadTakenBookTable(user.getUserCode());
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
	
	private void addBookToUserBookList(User user) {
		try {
			if (jBooksTable.getValueAt(jBooksTable.getSelectedRow(), jBooksTable.getSelectedColumn()) != null) {
				String ISBN = jBooksTable.getValueAt(jBooksTable.getSelectedRow(),0).toString();
				String bookTitle = jBooksTable.getValueAt(jBooksTable.getSelectedRow(),3).toString();
				
				try {
					Database.saveTakenBook(ISBN, user.getUserCode(), true);
					reloadLibraryBookTable();
					ProfilePage.reloadTakenBookTable(user.getUserCode());
				} catch(Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				JOptionPane.showMessageDialog(
						null,
						"Book - " + bookTitle + " has been successfully added to Book List",
						"Book Added",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(
					null,
					"Please select a book in the list to add to Book List.",
					"No Book Selected",
					JOptionPane.INFORMATION_MESSAGE
			);
		}
	}
	
	public static void reloadLibraryBookTable() throws SQLException {
		jBookScrollPane.getViewport().remove(jBooksTable);
		jBooksTable = createBooksTable();
		jBookScrollPane.getViewport().add(jBooksTable);
		jPanel.repaint();
    }
}
