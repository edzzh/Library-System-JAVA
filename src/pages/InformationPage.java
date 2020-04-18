package pages;

import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.*;

public class InformationPage {
	public static JPanel jPanel1 = new JPanel();
    public static JPanel jPanel2 = new JPanel();
    public static JPanel jPanel3 = new JPanel();
    
    private static JTable jInfoTable = new JTable();
    private static JTable jInfoTable2 = new JTable();
    
    public JComponent renderInformationPage () throws SQLException {
        
        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        
        jPanel2.add(new JLabel("Working hours: "));    
        jPanel3.add(new JLabel("Employees and Specialization: "));    

        jInfoTable = createTimesheetTable(); 
        jInfoTable2 = createEmployeeTabel();
        
        jPanel2.add(new JScrollPane(jInfoTable));
        jPanel3.add(new JScrollPane(jInfoTable2));
        
        jPanel1.add(jPanel2);
        jPanel1.add(jPanel3);
        
        return jPanel1;
        
    }
    
    public static JTable createTimesheetTable() throws SQLException {
        
		String ColumnHeaderName[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
		Object [][] ColumnTime = new Object[][] {
                { "9.00 - 18.00", "9.00 - 18.00", "9.00 - 18.00", "9.00 - 18.00", "9.00 - 18.00", "10.00 - 16.00", "Closed" },
        };
                
		@SuppressWarnings("serial")
		JTable newTable = new JTable(ColumnTime, ColumnHeaderName) {
    		public boolean isCellEditable (int iRows, int iCols) {
    			return false;
    		}
    	};                
                
    	newTable.setPreferredScrollableViewportSize(new Dimension(900, 30));
    	newTable.setFillsViewportHeight(true);
        
        return newTable;
	}
        
	public static JTable createEmployeeTabel() throws SQLException {
            
		String ColumnHeaderName[] = { "Name", "Surname", "Specialization" };
		Object [][] ColumnTime = new Object[][] {
                { "Janis", "Sausserdis", "IT technician"},
                { "Žanis", "Gauja", "Librarian"},
                { "Santa", "Lampa", "Administrator"},
                { "Anna", "Kanna", "Administrator"},
        };
                
		@SuppressWarnings("serial")
		JTable newTable = new JTable(ColumnTime, ColumnHeaderName) {
    		public boolean isCellEditable (int iRows, int iCols) {
    			return false;
    		}
    	};        
                
    	newTable.setPreferredScrollableViewportSize(new Dimension(900, 75));
    	newTable.setFillsViewportHeight(true);
        
        return newTable;
	}
        
}
