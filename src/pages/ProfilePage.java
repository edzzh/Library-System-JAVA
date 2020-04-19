package pages;

import java.awt.*;
import java.sql.SQLException;

import javax.swing.*;
import models.*;

public class ProfilePage {
	private static JPanel jPanel = new JPanel();
	private static JScrollPane jTakenBookScrollPane = new JScrollPane();
	private static JTable jBooksTable;
	private static JButton returnBookButton = new JButton("Return Book");
	private User user;
	
	public ProfilePage(User user) {
		this.user = user;
	}
	
	public JComponent renderProfilePage () throws SQLException {
		JLabel infoLabel = new JLabel(user.getName().toUpperCase() + " Taken Books");
		
		returnBookButton.setSize(40, 40);      
		returnBookButton.addActionListener((e) -> {
        	returnBook(user.getUserCode());
        });
        
		jBooksTable = userBookListTable(user.getUserCode());
		jTakenBookScrollPane.getViewport().add(jBooksTable);
		
		jPanel.add(infoLabel);
		jPanel.add(jTakenBookScrollPane);
		jPanel.add(returnBookButton);
		
		return jPanel;
	}
	
	public static JTable userBookListTable(int userCode) throws SQLException {
		String ColumnHeaderName[] = { "ISBN", "Year", "Author", "Title"};
		String[][] books = Database.getTakenBooks(userCode);
		
		@SuppressWarnings("serial")
		JTable bookListTable = new JTable(books, ColumnHeaderName) {
			public boolean isCellEditable (int iRows, int iCols) {
    			return false;
    		}
		};
		
		bookListTable.setPreferredScrollableViewportSize(new Dimension(900, 200));
		bookListTable.setFillsViewportHeight(true);
			
		return bookListTable;
	}
	
	public static void returnBook(int userCode) {
		try {
			if (jBooksTable.getValueAt(jBooksTable.getSelectedRow(), jBooksTable.getSelectedColumn()) != null) {
				String ISBN = jBooksTable.getValueAt(jBooksTable.getSelectedRow(),0).toString();
				String bookTitle = jBooksTable.getValueAt(jBooksTable.getSelectedRow(),3).toString();
				
				Database.removeBookFromTakenBooks(ISBN);
				reloadTakenBookTable(userCode);
				LibraryPage.reloadLibraryBookTable();
				
				JOptionPane.showMessageDialog(
						null,
						"Book - '" + bookTitle + "' has been successfully returned to library system.",
						"Book Returned",
						JOptionPane.INFORMATION_MESSAGE
				);
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(
					null,
					"Please select a record in the list to return.",
					"No Record Selected",
					JOptionPane.INFORMATION_MESSAGE
			);
		}
	}
	
	public static void reloadTakenBookTable(int userCode) throws SQLException {
		jTakenBookScrollPane.getViewport().remove(jBooksTable);
		jBooksTable = userBookListTable(userCode);
		jTakenBookScrollPane.getViewport().add(jBooksTable);
		jPanel.repaint();
    }
}
