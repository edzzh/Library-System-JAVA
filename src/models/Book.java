package models;

public class Book {
	private String ISBN;
	private int year;
	private String author;
	private String title;
	private int rating;
	private String condition;
	private String rarity;
		
	public Book(String ISBN, int year, String author, String title, int rating, String condition, String rarity) {
		this.setISBN(ISBN);
		this.setYear(year);
		this.setAuthor(author);
		this.setTitle(title);
		this.setRating(rating);
		this.setCondition(condition);
		this.setRarity(rarity);
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		if (ISBN != null && ISBN.length() == 10 && !ISBN.isEmpty()) {
			this.ISBN = ISBN;
		} else {
			throw new IllegalArgumentException("ISBN Code Is Not Valid");
		}
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		if (year >= 1600 && Integer.toString(year).length() == 4) {
			this.year = year;
		} else {
			throw new IllegalArgumentException("Year Is Not Valid");
		}
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		if (author != null && !author.isEmpty()) {
			this.author = author;
		} else {
			throw new IllegalArgumentException("Author Is Not Valid");
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title != null && !title.isEmpty()) {
			this.title = title;
		} else {
			throw new IllegalArgumentException("Title Is Not Valid");
		}	
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getRarity() {
		return rarity;
	}

	public void setRarity(String rarity) {
		this.rarity = rarity;
	}
}