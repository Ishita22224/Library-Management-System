# Library Management System in Java (using OOP Principles)

This is a Library Management System implemented using Object-Oriented Programming (OOP) Principles in Java. It is a menu-driven interface for users to interact with the library system. The system provides two main interfaces: Librarian and Members. Librarian has the authority to Register Members, Remove Members, Add Books, Remove Books, View Members and View Books. On the other hand Members can View, Borrow Books and Return Borrowed Books.

Implemented Library Management System by defining the following classes - 

## 1. Library
This class contains two private ArrayLists - "bookCollection" and "memberList". These ArrayLists store the list of all the books in the library and all the members registered in the library, respectively. This class basically keeps track of all the books and members registered in the library. Other classes access these lists to retrieve information as required.

There are two methods in this class -

`(i) getAllBooks()` : This method returns an ArrayList<Book> of all the books in the Library.<br><br>
`(ii) getAllMembers() :` This method returns return an ArratList<Member> of all the members registered in the library.<br><br>

## 2. Librarian
This class represents a librarian in an actual library. It has the authority to Register a Member, Remove a Member, Add a Book, Remove a Book, get a List of all the Books and Members Registered in the Library.
It has two Priority Queues<Integers> and two private variables - "memberID" & "bookID" which help in assigning appropriate and unique IDs to Books and Members.

It has following methods -

`(i) addBooK() :`
It allows the librarian to add more books to the ArrayList<Book> by taking inputs for Book Name, Author and Total number of Copies. It also assigns a unique bookID to the book.<br><br>
`(ii) removeBook() :`
It allows the librarian to remove books from the ArrayList<Book> by taking input for bookID as it is unique.
It also checks whether a book with bookID entered exists in the library or not.<br><br>
`(iii) registerMember() :`
It allows the librarian to register a new member to the library (ArrayList<Member>)  by taking input for Name, Age and Phone number. It also assigns a unique MemberID to every Member. At the same time, it checks for duplicate registration using phone number.

`(iv) removeMember() :` 
It allows the librarian to remove members from the library (ArrayList<Member>) by taking input for member's ID. It first checks, whether a member with entered ID exists or not, if it exists then it removes him from the library.<br><br>
`(v) listBooks() :`
If there are books in the library then it lists all the books along with their Author's name and bookID. It also shows the number of copies available for issuing.<br><br>

`(vi) listMembers() :`
If there are members registered in the library then it lists out all the member's names along with their age, phone number, total fine to be paid for delayed return of books, and a list of all the borrowed books by each member.  

## 3. Book 
This class represents an actual book in library management system. It encapsulates all the essential properties that a book must have. 

The properties and methods defined under this class are -

### Attributes -
`a. title` : the Title of the Book  
`b. author` : the Author of the Book  
`c. issuedCopies` : the number of issued copies of the book  
`d. totalCopies` : the total number of copies of the book in the library initially  
`e. ID` : a unique book ID assigned to each book for easy identification in case of overlapping names.  

### Methods -
`a. Book() :` 
Constructor Book() initialises a new book by taking in all the necessary parameteres like Book's Title, Author's Name, Total number of Copies and Book ID.
The number of issued Copies are initialised with zero.  

`b. getTitle() :`
Returns the title of the book.<br><br>
`c. getAuthor() :` 
Returns the name of the author of the book.<br><br>
`d. getBookID() :`
Returns the Book ID of the book.<br><br>

## 4. IssuedBook 
This class is a representation of an actual Issued Book from the library. It tracks the status of an issued book by managing the fine to paid on the book and the number of the days for which the book has been borrowed.

The properties and methods of this class are below -

### Properties :

`a. book` : Has the book object for the issued book.  
`b. timeElapsed` :** Days for which the book has been borrowed. (1 sec = 1 day).  
`c. timeMax` :** Maximum number of days the book is allowed to issued. When the time exceeds, the member has to pay fine on the issued book.  
`d. fine` :** Keeps track of the fine to paid on the book (after 10 days (=10 sec) the member has to pay Rs. 3 for every passing day(=sec)).  

### Methods : 

`(i) IssuedBook()`: 
Constructor IssuedBook() initialises an issued book by taking in input of the book to be issued. The timeMax for every book is 10 seconds and timeElapsed is calculated using the object of ScheduledExecutorService.<br><br>
`(ii) getBooks()` : 
This getter method is defined to get access to the book that has been issued.<br><br>
`(iii) retFine()` :
This getter method returns the fine on the issued book.<br><br>

## 5. Member 
This class represents an actual member of a library in library management system. It allows the members to view all the books in the library, issue books, return the books that they have issued and pay the fine on the books.

The properties and methods of this class are below - 

### Properties :

`a. name` : Name of the Member.  
`b. age` : Age of the Member.    
`c. booksBorrowed` : Stores the list of all the books borrowed by the member from the library.  
`d. phoneNo` : Phone Number of the member.  
`e. balance` : The balance amount the member has to pay in case he returns the book after the exceeding the day/time limit.  
`f. ID` : Unique ID for each Member.  

### Methods 

`a. Member()` :
Constructor Member() initialises a member by taking input of name, age and phone Number. It also assigns a unique member ID which is calculated when registerMember() is called by the librarian. It also initialises an empty ArrayList<IssuedBook> for the Member and balance to zero.<br><br>
`b. getName(), getAge(), getPhone(), getBorrowedBooks(), getID(), getFine()` :
These are the getter methods as the attributes have been declared as private to implement encapsulation. It does not allows librarian to change the member's name, age and phone number once declared as these are supposed to be fixed. These functions return the name of the member, age of the member, phone number of the number, an ArrayList<IssuedBook>, unique ID and balance amount due to be paid, respectively.<br><br> 
`c. cmpID(), cmpPhone(), cmpName()` :
These are the comparing methods to compare between the ID, Phone and Name of the member with the member name, Id, Phone passed as a parameter.<br><br>
`d. issueBook()` : 
This method allows the Member to issue the book from the library. It takes input of the BookID of the book the member wants to issue. First, it checks whether the member has any pending balance. If yes, then the member is asked to clear the dues first. If not then, it checks that whether the member has borrowed 2 books because a member is allowed to borrow at-most two books. Then it searches for the bookID entered by the member. If found it checks for the available number of copies and issues the book to the member accordingly. It also then updates the total number of issued copies of that book. If the BookID is not found then it prints a suitable message and returns from the function.<br><br> 
`e. returnBook()` :
This method allows the Member to return the book that it has issued. It first checks whether there are any issued books by the member. If yes, then it shows him the option to choose which book he would like to return by entering the unique bookID. If the bookID is correct then it removes that book from its borrowed books list. While removing it checks for delayed return and displays a penalty message along with the amount charged for delayed return. It also adds this amount to the balance payment and asks the member to pay fine in the next step.<br><br>
`f. listBorrowedBooks()` :
This method lists all the books borrowed by the member.<br><br>
`g. listAvailableBooks()` :
This method lists all the books available in the library for issuing.<br><br>
`h. calFine()` : 
This method calculates the total fine of all the books the member has borrowed. This includes the penaly that was charged on the previous returns and the fine on the books that are still issued by the member.<br>  
`i. payFine()` : 
This method displays the total fine the member has due. It first displays the fine it has on the books that are still issued by him. After that, it shows the total fine including the balance amount to be paid. Then it pays the balance amount and if the person had fine on the books that it has still issued, it asks the member to return the books and pay the fine.<br>  

## 6. Main 
This class contains the main function and it serves as the entry point for library management system. It is the class responsible for displaying a menu-driven interface for the both librarian and members. The library portal gets initialized as soon as you run the program and a do-while loop allows both librarian and members to navigate all their way through library management system. It also has defined functions for checking the validity of inputs - name (isAlpha) and phone number (isPhone). It does error handling for integer inputs using try and catch.

### The main function :

This main function has a do-while loop which allows the user to enter as Librarian or as a Member. While entering as a member, the member is supposed to enter its name and phone number for verification. The main function also checks for the validity of these inputs as described above.
As soon as the library portal is initialised, the user is shown three option -

`(i) Librarian`
If a person enters as librarian by pressing 1, he gets in the authority position as a librarian. He is allowed to register a member, remove a member, add a new book, remove an existing book, view all members and view all books in the library.<br>    
`(ii) Member`
If a person enters as a member by pressing 2, after giving correct login credentials, he can view all the books, borrow a book, pay his fine and return the book that he has borrowed.<br>    
`(iii) Exit`
If a person presses 3, he exits from the library portal.<br>    



### (i) enterLibrarian() : 
When you enter as a librarian, you get directed to this function. This function uses do-while loop to print the menu every time you get done with the tasks until you press 7 and return to the main menu. You can perform the functions from the menu by pressing the number corresponding to each task. 
The options that you will see are as follows : 
1. Register a Member
2. Remove a Member
3. Add a Book
4. Remove a Book
5. View all Members along with their Books and Fines to be Paid
6. View all Books
7. Back


This function also takes in input for all the function calls that is needed. It also checks for each input as needed. Like -

`1. Register a Member` - Takes input for name, age & phone number. Then calls for registerMember() function.<br>      
`2. Remove a Member` - Takes input for Member ID and Member Name. Then calls for removeMember() function. <br>   
`3. Add a Book` - Takes input for title, author, number of copies of the book. Then calls for addBook() function.<br>    
`4. Remove a Book` - Takes input for book ID. Then calls for removeBook() function.<br>  
`5. View all Members along with their Books and Fines to be Paid` - Calls for listMembers() function. <br>     
`6. View all Books` - Calls for listBooks() function.<br>  
`7. Back` - Returns to the Main function and shows the menu containing options - 1. Librarian, 2. Member, 3. Exit.<br>      

### (ii) enterMember() : 
When you enter as member, you get directed to this function. This function also uses do-while loop to print the menu every time you get done with the tasks until you press 6 and return to the main menu. You can perform function from the menu by pressing the number corresponding to each task. The options that you will see are as follows: 
1. List Available Books
2. List My Books
3. Issue Books
4. Return Book
5. Pay Fine
6. Back

This function also takes in input for all the function calls that is needed. It also checks for each input as needed. Like -

`1. List Available Books` - Calls the listAvailableBooks() function.<br>      
`2. List My Books` - Calls the listBorrowedBooks() function.<br>  
`3. Issue Books` - Checks for any fine on the already books borrowed or any balance amount to be paid. If due, then displays a message stating that you cannot issue books. If not, then checks for the number of already borrowed books. If it is greater than 2, then does not allows you to borrow book as it is the limit. Else displays all the available books and takes input for the bookID. Then calls for issueBook() function.<br>      
`4. Return Book` - Checks whether any books have been borrowed or not. If not, then returns. Else takes input for bookID. Then calls for returnBook() function.<br>    
`5. Pay Fine` - Calls for payFine() function.<br>    
`6. Back` - Returns to the Main function and shows the menu containing options - 1. Librarian, 2. Member, 3. Exit.<br>  

### Other Methods :

`(i) isAlpha()` :
Checks for valid name inputs by ensuring only alphabets have been entered.<br>  
`(ii) isPhone()` :
Checks for valid phone numbers as inputs. It does this by ensuring that all characters are digits and the length is 10.<br>  
