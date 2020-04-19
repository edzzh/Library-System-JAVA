package pages;

import java.awt.Dimension;
import java.sql.SQLException;
import javax.swing.*;

public class InformationPage {
	public static JPanel informationPanel = new JPanel();
    public static JPanel workingHoursPanel = new JPanel();
    public static JPanel employeePanel = new JPanel();
    
    private static JTable timesheetTable = new JTable();
    private static JTable employeeTable = new JTable();
    
    public JComponent renderInformationPage () throws SQLException {     
    	informationPanel.setLayout(new BoxLayout(informationPanel, BoxLayout.Y_AXIS));
        
    	workingHoursPanel.add(new JLabel("Working hours: "));    
    	employeePanel.add(new JLabel("Employees and Specialization: "));    

    	timesheetTable = createTimesheetTable(); 
    	employeeTable = createEmployeeTabel();
        
        workingHoursPanel.add(new JScrollPane(timesheetTable));
        employeePanel.add(new JScrollPane(employeeTable));
        
        informationPanel.add(workingHoursPanel);
        informationPanel.add(employeePanel);
        
        return informationPanel;
        
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
