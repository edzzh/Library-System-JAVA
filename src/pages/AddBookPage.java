package pages;

import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

import models.Database;

@SuppressWarnings("serial")
public class AddBookPage extends JFrame implements ActionListener{
	JButton addBook;
    JLabel isbnNumberLabel, yearLabel, authorLabel, titleLabel, ratingLabel, conditionLabel, rarityLabel;
    JTextField isbnNumber, year, author, title;
    JComboBox<Integer> ratingComboBox;
    JComboBox<String> conditionComboBox, rarityComboBox;
    
    AddBookPage() {
    	setTitle("ADD BOOK");
		setSize(500, 600);
		
		GridBagLayout g = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(g);
        c.insets = new Insets(0, 0, 10, 0);
        
        c.gridy = 0;
        isbnNumberLabel = new JLabel("ISBN");
        add(isbnNumberLabel, c);
        
        c.gridy = 1;
        isbnNumber = new JTextField(15);
        add(isbnNumber, c);
        
        c.gridy = 2;
        yearLabel = new JLabel("Year");
        add(yearLabel, c);
        
        c.gridy = 3;
        year = new JTextField(15);
        add(year, c);
        
        c.gridy = 4;
        authorLabel = new JLabel("Author");
        add(authorLabel, c);
        
        c.gridy = 5;
        author = new JTextField(15);
        add(author, c);
        
        c.gridy = 6;
        titleLabel = new JLabel("Title");
        add(titleLabel, c);
        
        c.gridy = 7;
        title = new JTextField(15);
        add(title, c);
        
        c.gridy = 8;
        ratingLabel = new JLabel("Rating");
        add(ratingLabel, c);
        
        c.gridy = 9;
        Integer ratings[] = {1,2,3,4,5,6,7,8,9,10};
        ratingComboBox = new JComboBox<Integer>(ratings);
        add(ratingComboBox, c);
        
        c.gridy = 10;
        conditionLabel = new JLabel("Conditions");
        add(conditionLabel, c);
        
        c.gridy = 11;
        String conditions[] = {"Fine", "Very Good", "Good", "Fair", "Bad"};
        conditionComboBox = new JComboBox<String>(conditions);
        add(conditionComboBox, c);
        
        c.gridy = 12;
        rarityLabel = new JLabel("Rarity");
        add(rarityLabel, c);
        
        c.gridy = 13;
        String rarities[] = {"Rare", "Special Release", "Common"};
        rarityComboBox = new JComboBox<String>(rarities);
        add(rarityComboBox, c);
        
        c.gridy = 14;
        addBook = new JButton("ADD BOOK");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 0, 0, 0);
        add(addBook, c);
        addBook.addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Database.saveBookInDatabase(
					isbnNumber.getText(), 
					Integer.parseInt(year.getText()), 
					author.getText(), 
					title.getText(), 
					Integer.parseInt(ratingComboBox.getSelectedItem().toString()), 
					conditionComboBox.getSelectedItem().toString(), 
					rarityComboBox.getSelectedItem().toString()
			);
			
			LibraryPage.reloadLibraryBookTable();
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
		
		this.dispose();
	}

}
