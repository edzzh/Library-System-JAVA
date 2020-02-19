package pages;
import java.awt.event.*;
import java.sql.SQLException;
import java.awt.*;
import javax.swing.*;

import models.Database;

public class AddBookPage extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	JButton addBook;
    JLabel isbnNumberLabel, yearLabel, authorLabel, titleLabel, ratingLabel, conditionLabel, rarityLabel;
    JTextField isbnNumber, year, author, title, rating, condition, rarity;
    
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
        rating = new JTextField(15);
        add(rating, c);
        
        c.gridy = 10;
        conditionLabel = new JLabel("Condition");
        add(conditionLabel, c);
        
        c.gridy = 11;
        condition = new JTextField(15);
        add(condition, c);
        
        c.gridy = 12;
        rarityLabel = new JLabel("Rarity");
        add(rarityLabel, c);
        
        c.gridy = 13;
        rarity = new JTextField(15);
        add(rarity, c);
        
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
					Integer.parseInt(rating.getText()), 
					condition.getText(), 
					rarity.getText()
			);
			
			MainPage.reloadTable();
		} catch (NumberFormatException | SQLException e1) {
			e1.printStackTrace();
		}
		
		this.dispose();
	}

}
