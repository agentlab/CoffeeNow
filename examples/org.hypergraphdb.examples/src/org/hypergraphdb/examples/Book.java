package org.hypergraphdb.examples;

/**
 * @author amivanoff
 */
public class Book {
	protected int yearPublished;
	protected String title;
	protected String author;

	/**
	 * We need default constructor for hgdb
	 */
	public Book() {
	}

	/**
	 * Helper constructor
	 * @param string
	 * @param string2
	 */
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
	}

	/**
	 * We need setter for hgdb
	 * @param i
	 */
	public void setYearPublished(int i) {
		yearPublished = i;
	}

	/**
	 * We need getter for hgdb
	 * @return
	 */
	public int getYearPublished() {
		return yearPublished;
	}

	/**
	 * We need getter for hgdb
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * We need setter for hgdb
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * We need getter for hgdb
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * We need setter for hgdb
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
}
