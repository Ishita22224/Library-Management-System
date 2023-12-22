package org.example;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;


// Main CLASS
public class Main {

    // For checking whether the String contains only Alphabets or not
    public static boolean isAlpha(String str) {
        return (str != null) && (str.matches("^[a-zA-Z ]*$"));
    }

    // Checking whether the input is a correct Phone No.
    public static boolean isPhone(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        if (str.length() != 10) {
            return false;
        }

        return true;
    }

    // If we Enter As Librarian
    public static void enterLibrarian(Librarian lib, Library library) throws IOException {
        // do-while loop until we press 7

        do {

            // Giving all the Options that a Librarian Can Do
            System.out.println("1. Register a Member");
            System.out.println("2. Remove a Member");
            System.out.println("3. Add a Book");
            System.out.println("4. Remove a Book");
            System.out.println("5. View all Members along with their Books and Fines to be Paid");
            System.out.println("6. View all Books");
            System.out.println("7. Back");
            System.out.println("--------------------------------");

            // Taking Input from the user
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inp = reader.readLine();

            System.out.println("--------------------------------");
            Scanner sc = new Scanner(System.in);
            if (inp.equals("1") || inp.equals("2") || inp.equals("3") || inp.equals("4") || inp.equals("5")
                    || inp.equals("6") || inp.equals("7")) {
                // Case 1 --> Register A Member
                if (inp.equals("1")) {

                    // Name Input
                    System.out.print("Name : ");
                    String name = reader.readLine();

                    // Checking Validity
                    if (!isAlpha(name)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Name");
                        System.out.println("--------------------------------");

                        continue;
                    }

                    // Age input
                    int age;
                    try {
                        System.out.print("Age : ");
                        age = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Integer Value of Age");
                        System.out.println("--------------------------------");

                        continue;
                    }

                    // Phone Input
                    String phone;
                    System.out.print("Phone No : ");
                    phone = reader.readLine();

                    // Checking Validity
                    if (!isPhone(phone)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Phone Number");
                        System.out.println("--------------------------------");

                        continue;
                    }

                    // Calling the Function to actually Register Member
                    lib.registerMember(name, age, phone, library);

                } else if (inp.equals("2")) { // 2. Remove A Member

                    // Name Input
                    System.out.print("Name : ");
                    String name = reader.readLine();

                    // Checking Validity
                    if (!isAlpha(name)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid User Name");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // ID Input
                    int ID;
                    try {
                        System.out.print("Enter the ID of the Member : ");
                        ID = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Enter a Valid Member ID");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // Calling the Function to Actually Remove Member
                    lib.removeMember(ID, library);

                } else if (inp.equals("3")) { // 3. Add Book

                    // Title Input
                    System.out.print("Title of the Book : ");
                    String title = reader.readLine();;

                    // Author Input
                    System.out.print("Author of the Book : ");
                    String author = reader.readLine();;
                    // Checking Validity
                    if (!isAlpha(author)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Author Name");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // TotalCopies Input
                    int totalCopies;
                    try {
                        System.out.print("Total Copies : ");
                        totalCopies = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Integer Value");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // Calling the Function to actually add the Book to the Library
                    lib.addBook(title, author, totalCopies, library);

                } else if (inp.equals("4")) { // 4. Removing a Book

                    // ID input
                    int ID;
                    try {
                        System.out.print("Enter ID of the Book that You want to Remove: ");
                        ID = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Enter a Valid Book ID");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // Calling the function to actually remove the book from the Library
                    lib.removeBook(ID, library);

                } else if (inp.equals("5")) { // 5. View All the Members

                    lib.listMembers(library);

                } else if (inp.equals("6")) { // 6. View All the Books

                    lib.listBooks(library);

                } else if (inp.equals("7")) { // 7. Exiting from the Library Interface
                    return;

                }
            }

            else {
                System.out.println("--------------------------------");
                System.out.println("ERROR: Incorrect Input");
                System.out.println("--------------------------------");
                continue;
            }


        } while (true);

    }

    // ENTERING AS MEMBER
    public static void enterMember(Library library, Librarian lib, Member m) throws IOException {
        // do while loop until the user presses 6
        do {
            // Giving all the Options that a Member Can Do
            System.out.println("1. List Available Books");
            System.out.println("2. List My Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Pay Fine");
            System.out.println("6. Back");
            System.out.println("--------------------------------");

            // Taking Input from the user
            Scanner sc = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String inp = reader.readLine();
            System.out.println("--------------------------------");

            if (inp.equals("1") || inp.equals("2") || inp.equals("3") || inp.equals("4") || inp.equals("5")
                    || inp.equals("6")) {
                if (inp.equals("1")) {

                    // 1. List All the Available Books
                    m.listAvailableBooks(library);

                } else if (inp.equals("2")) {

                    // 2. List All the Books Borrowed by the Member
                    m.listBorrowedBooks();

                } else if (inp.equals("3")) {
                    // 3. Issuing a Book

                    // Checking if the fine is zero or not, as cannot issue if not zero
                    int fine = m.getFine();

                    // If Due Amount is not zero
                    if (fine > 0) {

                        System.out.println("\nYour have Amount Due to be Paid. Please pay the due amount to continue issuing books");

                        System.out.println("--------------------------------");
                    } else if (m.getBorrowedBooks().size() >= 2) {

                        // Cannot borrow books More than 2
                        System.out.println("Sorry, You already have Two Books Issued. To continue issuing kindly return one of the books");
                        System.out.println("--------------------------------");
                        continue;
                    } else {

                        // Actually Issuing a Book
                        lib.listBooks(library);

                        if (library.getAllBooks().size() == 0) {
                            continue;
                        }
                        // Book ID input
                        int ID;
                        try {
                            System.out.print("Enter ID of the Book that You want to Issue: ");
                            ID = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("--------------------------------");
                            System.out.println("ERROR: Enter a Valid Book ID");
                            System.out.println("--------------------------------");
                            continue;
                        }

                        // Calling the function to actually issue the Book
                        m.issueBook(ID, library);

                    }

                } else if (inp.equals("4")) {
                    // 4. Return Books

                    // Printing a list of all the Borrowed Books
                    m.listBorrowedBooks();
                    ArrayList<IssuedBook> ibk = m.getBorrowedBooks();
                    if (ibk.size() == 0) {
                        continue;
                    }

                    // ID input
                    int ID;
                    try {
                        System.out.print("Enter ID of the Book that You want to Return: ");
                        ID = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: InValid Book ID");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    // Calling the function to return the Book
                    m.returnBook(ID);

                } else if (inp.equals("5")) {

                    // 5. Payment of Fine
                    m.payFine();

                } else if (inp.equals("6")) {
                    // Returning to the main base
                    return;

                }

            }

            else {
                System.out.println("--------------------------------");
                System.out.println("ERROR: Invalid Input");
                System.out.println("--------------------------------");
            }



        } while (true);
    }

    // MAIN FUNCTION
    public static void main(String args[]) throws IOException {

        // Creating a Library And A Librarian
        Library library = new Library();
        Librarian lib = new Librarian();

        System.out.println("--------------------------------");
        System.out.println("Library Portal Initialized...");
        System.out.println("--------------------------------");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("1. Enter as Librarian\n2. Enter as a Member\n3. Exit");
            System.out.println("--------------------------------");

            String inp = reader.readLine();
            System.out.println("--------------------------------");

            if (inp.equals("1") || inp.equals("2") || inp.equals("3")) {
                if (inp.equals("1")) {
                    enterLibrarian(lib, library);
                } else if (inp.equals("2")) {

                    System.out.print("Name : ");
                    String name = reader.readLine();;

                    // checking for valid inputs
                    if (!isAlpha(name)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid User Name");
                        System.out.println("--------------------------------");
                        return;
                    }

                    String phone;
                    System.out.print("Phone No : ");
                    phone = sc.next();
                    if (!isPhone(phone)) {
                        System.out.println("--------------------------------");
                        System.out.println("ERROR: Invalid Phone Number");
                        System.out.println("--------------------------------");
                        continue;
                    }

                    ArrayList<Member> ml = library.getAllMembers();
                    boolean flag = false;
                    for (int i = 0; i < ml.size(); i++) {
                        Member m = ml.get(i);

                        if (m.cmpName(name) && m.cmpPhone(phone)) {

                            System.out.println("\nWelcome " + name + "!!\nMember ID : " + m.getID() + "\n");
                            System.out.println("--------------------------------");
                            enterMember(library, lib, m);

                            flag = true;
                            break;
                        }
                    }

                    if (flag == false) {
                        System.out.println("--------------------------------");
                        System.out.println(

                                "Member with -- Name : " + name + " and Phone No. : " + phone + " Does Not Exists");
                        System.out.println("--------------------------------");
                        continue;

                    }
                } else if (inp.equals("3")) {

                    System.out.println("--------------------------------");
                    System.out.println("Thank-you for Visiting!!");
                    System.out.println("You have Successfully Exited from the Library Portal");
                    System.out.println("--------------------------------");

                    System.exit(1);
                }
            } else {
                System.out.println("--------------------------------");
                System.out.println("ERROR: Wrong Input");
                System.out.println("--------------------------------");
            }

        } while (true);


    }
}