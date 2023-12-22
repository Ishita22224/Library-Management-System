package org.example;



// CLASS BOOK
public class Book {

    private String title;
    private String author;
    int totalCopies;
    int issuedCopies;
    private int ID;

    public Book(String title, String author, int totalCopies, int bookID) {
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.ID = bookID;
    }

    // GETTER METHODS
    public String getTitle() {return this.title;}

    public String getAuthor() {return this.author;}
    
    public int getBookID() {return this.ID;}

}