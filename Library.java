package org.example;
import java.util.*;



// LIBRARY CLASS
public class Library {

    // List of all Books and Members in the Library
    private ArrayList<Book> bookCollection = new ArrayList<>();
    private ArrayList<Member> memberList = new ArrayList<>();

    // Returns an Arraylist of all the books in the Library
    public ArrayList<Book> getAllBooks() {
        return bookCollection;
    }

    // Returns an Arraylist of all the members registered in the Library
    public ArrayList<Member> getAllMembers() {
        return memberList;
    }
}
