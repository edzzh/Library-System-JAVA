package pages;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import models.*;

@SuppressWarnings("serial")
public class LibraryPage {
	private static JScrollPane jScrollPane = new JScrollPane();
	private static JPanel jPanel = new JPanel();
	private static JTable jBooksTable;
	
    private JButton addBookButton = new JButton("Add Book");
    private JButton deleteBookButton = new JButton("Delete Book");
	
	public JComponent renderLibraryPanel (User user) throws SQLException {
		jBooksTable = createTable();
		jScrollPane.getViewport().add(jBooksTable);
		
		addBookButton.setSize(40, 40);
		addBookButton.addActionListener((e) -> {
        	AddBookPage addBookPage = new AddBookPage();
        	addBookPage.setVisible(true);
        });
		
		deleteBookButton.setSize(40, 40);      
        deleteBookButton.addActionListener((e) -> {
        	deleteBook();
        });
        
        jPanel.add(new JLabel("Hello, " + user.getName().toUpperCase() + "!"));
        jPanel.add(jScrollPane);
        jPanel.add(addBookButton);
        jPanel.add(deleteBookButton);
        
        return jPanel;
	}
	
	public static JTable createTable() throws SQLException {
		String ColumnHeaderName[] = { "ISBN", "Year", "Author", "Title", "Rating", "Condition", "Rarity" };
		
		String[][] books = Database.getBooks();
		
		JTable newTable = new JTable(books, ColumnHeaderName) {
    		public boolean isCellEditable (int iRows, int iCols) {
    			return false;
    		}
    	};
    	
    	newTable.setPreferredScrollableViewportSize(new Dimension(900, 300));
    	newTable.setFillsViewportHeight(true);
        
        return newTable;
	}
	
	private void deleteBook() {
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
	
	public static void reloadLibraryBookTable() throws SQLException {
		jScrollPane.getViewport().remove(jBooksTable);
		jBooksTable = createTable();
		jScrollPane.getViewport().add(jBooksTable);
		jPanel.repaint();
    }
}
