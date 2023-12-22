package org.example;

import java.util.*;


public class Librarian {

    // Priority Queues for Correct Assigning of "memberID" and "bookID"
    private static PriorityQueue<Integer> pq1 = new PriorityQueue<>();
    private static PriorityQueue<Integer> pq2 = new PriorityQueue<>();
    private static int memberID = 1;
    private static int bookID = 1;


    // ADDING BOOK TO THE LIBRARY
    public void addBook(String title, String author, int totalCopies, Library library) {

        System.out.println("--------------------------------");

        int i=0;
        while (i<totalCopies) {
            if (!pq2.isEmpty()) {

            int a = pq2.remove();
            Book bk = new Book(title, author, 1, a);
            library.getAllBooks().add(bk);           
            

            } else {

                Book bk = new Book(title, author, 1, bookID);
                library.getAllBooks().add(bk);                
                bookID++;
                
            }
            i++;
        }

        System.out.println("The Book - '" + title + "' added Successfully!! ");
        System.out.println("--------------------------------");

        
    }

    // REMOVE BOOK FROM LIBRARY
    public void removeBook(int bookID, Library library) {

        System.out.println("--------------------------------");

        // Getting list of all Books
        boolean flag = false;
        ArrayList<Book> bc = library.getAllBooks();
        ArrayList<Member> ml = library.getAllMembers();

        // Removing it from borrowed list of all members
        for (int i = 0; i < ml.size(); i++) {
            Member m = ml.get(i);
            ArrayList<IssuedBook> bb = m.getBorrowedBooks();
            for (int j = 0; j < bb.size(); j++) {
                IssuedBook ibk = bb.get(j);
                int id = ibk.getBook().getBookID();
                if (id == bookID) {
                    bb.remove(j);
                    break;
                }
            }
        }

        // Removing the book from the Library
        for (int i = 0; i < bc.size(); i++) {
            Book b = bc.get(i);

            if (b.getBookID() == bookID) {
                pq2.add(b.getBookID());
                bc.remove(i);
                flag = true;
                System.out.println("The Book - '" + b.getTitle() + "' was Successfully Removed!!");
                System.out.println("--------------------------------");
                break;
            }
        }

        // If the Book ID not found
        if (!flag) {
            System.out.println("The book ID - " + bookID + " is not Available in the Library!!");
            System.out.println("--------------------------------");
        }
    }


    // REGISTER A NEW MEMBER
    public void registerMember(String name, int age, String phoneNo, Library library) {

        System.out.println("--------------------------------");

        // Getting a list of all Members
        ArrayList<Member> ml = library.getAllMembers();

        // Checking for Duplicate Registrations
        for (int i = 0; i < ml.size(); i++) {
            Member m = ml.get(i);
            if (m.cmpPhone(phoneNo)) {
                System.out.println("Duplicate Registration!! Phone No. " + phoneNo + " already Registered.");
                System.out.println("--------------------------------");
                return;
            }
        }

        if (!pq1.isEmpty()) {

            int a = pq1.remove();
            Member newMember = new Member(name, age, phoneNo, a);
            library.getAllMembers().add(newMember);
            System.out.println("Member Successfully Registered with Member ID : " + newMember.getID() + "!!");
            System.out.println("--------------------------------");

        } else {

            Member newMember = new Member(name, age, phoneNo, memberID);
            library.getAllMembers().add(newMember);
            System.out.println("Member Successfully Registered with Member ID : " + newMember.getID() + "!!");
            memberID++;
            System.out.println("--------------------------------");
        }

    }

    // REMOVING MEMBER FROM LIBRARY
    public void removeMember(int memberID, Library library) {

        System.out.println("--------------------------------");

        // Getting list of all Registered Members
        boolean flag = false;
        ArrayList<Member> ml = library.getAllMembers();

        // Removing all the issued books from the Member's Borrowed list
        for (int i = 0; i < ml.size(); i++) {
            Member m = ml.get(i);
            if (m.cmpID(memberID)) {
                flag = true;
                ArrayList<IssuedBook> bb = m.getBorrowedBooks();
                for (int j = 0; j < bb.size(); j++) {
                    Book b = bb.get(j).getBook();
                    b.issuedCopies -= 1;
                    bb.remove(j);
                }
                // Removing the member from the list
                ml.remove(i);
                System.out.println("The Member : " + m.getName() + " with ID: " + memberID + " was Successfully Removed!!");
                pq1.add(m.getID());
                System.out.println("--------------------------------");
                return;
            }
        }

        // If a member with given ID does not exists
        if (!flag) {
            System.out.println("Member with Member ID: " + memberID + " Does Not Exist!!");
            System.out.println("--------------------------------");
        }
    }

    // LIST ALL BOOKS IN LIBRARY
    public void listBooks(Library library) {

        System.out.println("--------------------------------");

        // Getting list of all the books in the Library
        ArrayList<Book> bc = library.getAllBooks();

        // If no books in the Library at Present
        if (bc.size() == 0) {
            System.out.println("No Books in the Library Currently!");
            System.out.println("--------------------------------");
            return;
        }

        // Printing All the Properties of the Books prent in the Library
        for (int i = 0; i < bc.size(); i++) {
            Book book = bc.get(i);
            System.out.println();
            System.out.println("Book ID - " + book.getBookID());
            System.out.println("Name - " + book.getTitle());
            System.out.println("Author - " + book.getAuthor());

            System.out.println("Copies Available for Issuing - " + (book.totalCopies - book.issuedCopies));
            System.out.println();
            System.out.println("       ***");
        }
        System.out.println("--------------------------------");

    }

    // LIST ALL MEMBERS
    public void listMembers(Library library) {

        System.out.println("--------------------------------");

        // Getting list of all the Members 
        ArrayList<Member> ml = library.getAllMembers();

        // IF no members
        if (ml.size() == 0) {
            System.out.println("No Members Currently Registered in the Library");
            System.out.println("--------------------------------");
            return;
        }

        // Printing All the Information of the Members registered in the Library
        for (int i = 0; i < ml.size(); i++) {
            Member member = ml.get(i);

            System.out.println("Member ID - " + member.getID());
            System.out.println("Name - " + member.getName());
            System.out.println("Age - " + member.getAge());
            System.out.println("Phone No - " + member.getPhone());
            System.out.println();
            System.out.print("Books Borrowed --> ");
            ArrayList<IssuedBook> bb = member.getBorrowedBooks();
            if (bb.size() == 0) {
                System.out.print("No Books Borrowed!!");
            } else {
                for (int j = 0; j < bb.size(); j++) {
                    IssuedBook bk = bb.get(j);
                    System.out.print("\n" + (j + 1) + ") " + bk.getBook().getTitle());
                }
            }

            System.out.println("\n\nFINE --> ");
            System.out.println("Balance Due - " + member.getFine());
            
            System.out.println();
            System.out.println("       ***");
            System.out.println();
        }

        System.out.println("--------------------------------");
    }
}
