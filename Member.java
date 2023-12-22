package org.example;

import java.util.*;


// CLASS MEMBER
public class Member {

    private String name;
    private int age;
    private String phoneNo;
    private ArrayList<IssuedBook> booksBorrowed;
    private static int balance;
    private int ID;

    public Member(String name, int age, String phoneNo, int memID) {
        this.name = name;
        this.age = age;
        this.phoneNo = phoneNo;
        this.booksBorrowed = new ArrayList<IssuedBook>();
        this.balance = 0;
        this.ID = memID;
    }

    // GETTERS
    public String getName() {return this.name;}

    public ArrayList<IssuedBook> getBorrowedBooks() {return booksBorrowed;}

    public int getID() {return this.ID;}

    public int getAge() {return this.age;}

    public String getPhone() {return this.phoneNo;}

    public int getFine() {return this.calFine();}

    // COMPARING FUNCTIONS
    public boolean cmpName(String name2) {
        if (this.name.equals(name2)) {
            return true;
        }
        return false;
    }

    public boolean cmpPhone(String phone2) {
        if (this.phoneNo.equals(phone2)) {
            return true;
        }
        return false;
    }
    public boolean cmpID(int id) {
        if (this.ID == id) {
            return true;
        }
        return false;
    }


    // ISSUE A BOOK
    public void issueBook(int bookID, Library library) {

        System.out.println("--------------------------------");

        // List of all Books
        ArrayList<Book> bc = library.getAllBooks();

        // No Books in the Library
        if (bc.size() == 0) {
            System.out.println("No Books Available in the Library!");
            System.out.println("--------------------------------");
            return;
        }

        // If Balance != 0
        if (this.getFine() != 0) {
            System.out.println("Sorry, You cannot Issue!\nYour Balance Amount is not paid");
            System.out.println("--------------------------------");
            return;
        }

        // Issuing the Book
        for (int i = 0; i < bc.size(); i++) {
            Book book = bc.get(i);
            if (book.getBookID() == bookID) {
                if (book.issuedCopies == book.totalCopies) {
                    System.out.println("All the copies present in the library of BookID - " + bookID + " have already been issued");
                    System.out.println("--------------------------------");
                    return;
                }
                IssuedBook newIssuedBook = new IssuedBook(book);
                this.booksBorrowed.add(newIssuedBook);
                book.issuedCopies += 1;
                System.out.println("The Book with ID - " + bookID + " has been successfully Issued");
                System.out.println("--------------------------------");
                return;
            }
        }

        // No book exists with given bookID
        System.out.println("Book not found in the library");
        System.out.println("--------------------------------");
    }


    // RETURNING THE ISSUED BOOK
    public void returnBook(int bookID) {
        System.out.println("--------------------------------");
        boolean flag = false;

        // Searching Book with ID = bookID
        for (int i = 0; i < this.booksBorrowed.size(); i++) {
            IssuedBook bk = this.booksBorrowed.get(i);
            int f = bk.retFine();
            if (bk.getBook().getBookID() == bookID) {
                if (bk.retFine() == 0) {
                    System.out.println("Book ID : " + bk.getBook().getBookID() + " has been successfully returned.");
                    System.out.println("You Returned the Book on Time. No Fine to be Paid!!");
                    System.out.println("--------------------------------");
                } else {
                    System.out.println("Book ID : " + bk.getBook().getBookID() + " has been successfully returned. Rs. " + f + " have been charged for delay of " + f / 3 + " days.");
                    System.out.println("--------------------------------");
                }
                this.booksBorrowed.remove(i);
                bk.getBook().issuedCopies -= 1;
                flag = true;
                this.balance += f;
                return;
            }
        }

        // If the book with "bookID" was not issued by the Member
        if (flag == false) {
            System.out.println("You have not Issued any Book with ID --> " + bookID);
            System.out.println("--------------------------------");
        }
    }


    // LIST ALL THE BORROWED BOOKS
    public void listBorrowedBooks() {

        // If no book borrowed
        if (this.booksBorrowed.size() == 0) {
            System.out.println("You have not Borrowed any Book!!");
            System.out.println("--------------------------------");
            return;
        }

        // Printing books + Properties
        for (int i = 0; i < this.booksBorrowed.size(); i++) {
            Book book = this.booksBorrowed.get(i).getBook();
            System.out.println("Name - " + book.getTitle());
            System.out.println("Book ID - " + book.getBookID());
            System.out.println("Author - " + book.getAuthor());
            System.out.println();
            System.out.println("       ***");
        }
        System.out.println("--------------------------------");
    }


    // LISTING AVAILABLE BOOKS
    public void listAvailableBooks(Library library) {

        System.out.println("--------------------------------");

        // Getting list of all the books in the Library
        ArrayList<Book> bc = library.getAllBooks();

        // If no books in the Library
        if (bc.size() == 0) {
            System.out.println("No Books in the Library Currently!");
            System.out.println("--------------------------------");
            return;
        }

        // Printing Books + Properties
        for (int i = 0; i < bc.size(); i++) {
            Book book = bc.get(i);
            if(book.issuedCopies != book.totalCopies) {
                System.out.println();
                System.out.println("Book ID - " + book.getBookID());
                System.out.println("Name - " + book.getTitle());
                System.out.println("Author - " + book.getAuthor());

                System.out.println("Copies Available for Issuing - " + (book.totalCopies - book.issuedCopies));
                System.out.println();
                System.out.println("       ***");
            }
        }
        System.out.println("--------------------------------");

    }


    // Calculates the Total Fine 
    public int calFine() {
        int f = 0;
        for (int i = 0; i < this.booksBorrowed.size(); i++) {
            IssuedBook ib = this.booksBorrowed.get(i);
            if (ib.retFine() != 0) {
                System.out.println((i+1) + ". Book : '" + ib.getBook().getTitle() + "' | Fine: " + ib.retFine());
            }
            f += ib.retFine();
        }
        f += this.balance;
        return f;
    }
    

    // PAY FINE
    public void payFine() {
        System.out.println("--------------------------------");
        int f = this.calFine();
        if (f != 0) {
            System.out.println("\nOverall Fine : Rs. " + f);

            if (this.balance != 0) {
                System.out.println("\nYou had a Balance Due of : " + this.balance + "." );
                this.balance = 0;
                System.out.println("It has been paid successfully!!");
                System.out.println("--------------------------------");
            } else {
                System.out.println("\nNo Balance is Due!!. Please return the books whose return date has been exceeded");            }
                System.out.println("--------------------------------");
        } else {
            System.out.println("NO Balance is Due!!");
            System.out.println("--------------------------------");
        }

    }
}
