package model;

import java.sql.*;
import java.util.*;
import model.User;
import utils.Utils;

public class Database {
	final private static String userDatabase = "jdbc:sqlite:library_users.db";
    final private static String bookDatabase = "jdbc:sqlite:library_books.db";
    public static Connection con = null;

    public static void connect() {
        try {
            con = DriverManager.getConnection(userDatabase);
            
            System.out.println("CONNECTION TO SQLITE HAS BEEN ESTABLISHED");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch(SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS (\n"
                + "ID integer PRIMARY KEY,\n"
                + "NAME text NOT NULL,\n" 
                + "SURNAME text NOT NULL,\n" 
                + "USERNAME text NOT NULL,\n"
                + "PASSWORD text NOT NULL, \n"
                + "USER_NUMBER integer NOT NULL"+ ")";
        
        try (Connection con = DriverManager.getConnection(userDatabase)){
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("CREATED USERS TABLE");
        } catch (SQLException e) {
           System.out.println(e.getMessage()); 
        }
    }
    
    public static void createBooksTable() {
        String sql = "CREATE TABLE IF NOT EXISTS BOOKS (\n"
                + "ID integer PRIMARY KEY,\n"
                + "ISBN text NOT NULL,\n" 
                + "YEAR integer NOT NULL,\n"
                + "AUTHOR text NOT NULL, \n"
                + "TITLE text NOT NULL, \n"
                + "RATING integer NOT NULL, \n"
                + "CONDITION text NOT NULL, \n"
                + "RARITY text NOT NULL"+ ")";
        
        try (Connection con = DriverManager.getConnection(bookDatabase)){
            Statement stmt = con.createStatement();
            stmt.execute(sql);
            System.out.println("CREATED BOOKS TABLE");
        } catch (SQLException e) {
           System.out.println(e.getMessage()); 
        }
    }
    
    public static void registerUserInDatabase(String name, String surname, String username, String password) throws SQLException {
        String sql = "INSERT INTO USERS(NAME, SURNAME, USERNAME, PASSWORD, USER_NUMBER) "
                + "VALUES (?, ?, ?, ?, ?)";
        
        con = DriverManager.getConnection(userDatabase);
        
        PreparedStatement ps = con.prepareStatement(sql);
        Random r = new Random();
        
        ps.setString(1, name);
        ps.setString(2, surname);
        ps.setString(3, username);
        ps.setString(4, Utils.MD5(password));
        ps.setInt(5, r.nextInt((1000 - 1) + 1) + 1);
        
        ps.executeUpdate();
    }
    
    public static User loginInDatabase(String username, String password) throws SQLException {
		User user = null;
		
		String query = "SELECT * FROM USERS WHERE USERNAME=? AND PASSWORD=?";
		
		con = DriverManager.getConnection(userDatabase);
		
		PreparedStatement stmt = con.prepareStatement(query);
		
		stmt.setString(1, username);
		stmt.setString(2, Utils.MD5(password));
		
		ResultSet result = stmt.executeQuery();
		
		if (result.next()) {
			String outputName = result.getString("NAME");
			String outputSurname = result.getString("SURNAME");
			String outputUsername = result.getString("USERNAME");
			String outputUserNumber = result.getString("USER_NUMBER");
			
			user = new User(outputName, outputSurname, outputUsername, outputUserNumber);
		}
		
		return user;
	}
    
    public static void saveBookInDatabase(
    		String isbn, 
    		int year, 
    		String author, 
    		String title, 
    		int rating, 
    		String condition, 
    		String rarity
    ) throws SQLException {
    	String sql = "INSERT INTO BOOKS(ISBN, YEAR, AUTHOR, TITLE, RATING, CONDITION, RARITY) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    	
    	con = DriverManager.getConnection(bookDatabase);
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, isbn);
        ps.setInt(2, year);
        ps.setString(3, author);
        ps.setString(4, title);
        ps.setInt(5, rating);
        ps.setString(6, condition);
        ps.setString(7, rarity);
        
        ps.executeUpdate();
    }
    
    public static String[][] getBooks() throws SQLException {
    	String query = "SELECT * FROM BOOKS ORDER BY ID ASC";
    	String [][] Content;
    	int bookRowCount = 0;
    	int rowNum = 0;
    	
    	con = DriverManager.getConnection(bookDatabase);
    	Statement stmt = con.createStatement();
    	
    	// Get Row Count By Using TYPE_FORWARD_ONLY as ResultSet Type
    	ResultSet rsBookRowCount = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM BOOKS");
    	rsBookRowCount.next();
    	bookRowCount = rsBookRowCount.getInt("rowcount");
    	rsBookRowCount.close();
    	
    	// Get BOOKS from database
    	ResultSet rsBooks = stmt.executeQuery(query);
  	
        if (bookRowCount != 0) {
        	Content = new String[bookRowCount][7];
        	
        	while (rsBooks.next()) {
        		Content[rowNum][0] = "" + rsBooks.getString("ISBN");
        		Content[rowNum][1] = "" + rsBooks.getInt("Year");
        		Content[rowNum][2] = "" + rsBooks.getString("Author");
        		Content[rowNum][3] = "" + rsBooks.getString("Title");
        		Content[rowNum][4] = "" + rsBooks.getInt("Rating");
        		Content[rowNum][5] = "" + rsBooks.getString("Condition");
        		Content[rowNum][6] = "" + rsBooks.getString("Rarity");
        		rowNum++;
        	}
        } else {
        	Content = new String[0][7];
        }
        
        return Content;
    }
    
    public static void deleteBook(String isbn) throws SQLException {
    	String sql = "DELETE FROM BOOKS WHERE ISBN = ?";
    	PreparedStatement pstmt = con.prepareStatement(sql);
    	
    	pstmt.setString(1, isbn);
    	pstmt.executeUpdate();
    }
    
    // Use for DB creation on your local computer
    public static void main(String[] args) {
//    	connect();
//    	createBooksTable();
//    	createUsersTable();
    }
}
