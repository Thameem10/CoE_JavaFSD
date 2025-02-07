package week2;
import java.util.*;
public class Library extends Thread{

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		LibraryManager libManager = new LibraryManager();
		Book b1 = new Book("Good","shakesphere","9789");
		Book b2 = new Book("bad","william","8976");
		Book b3 = new Book("ugly","robert","7689");
		User u1 = new User("thameem","1234",new ArrayList<>());
		User u2 = new User("rajesh","2134",new ArrayList<>());
		User u3 = new User("sanjay","2456",new ArrayList<>());
		libManager.addBook(b1);
		libManager.addBook(b2);
		libManager.addBook(b3);
		libManager.addUser(u1);
		libManager.addUser(u2);
		libManager.addUser(u3);
		Runnable r1 = ()-> libManager.borrowBook("9789", "1234");
		Runnable r2 = ()-> libManager.returnBook("9789", "1234");
		Runnable r3 = ()-> libManager.reserveBook("9789","1234");
		Runnable r4 = ()-> libManager.searchBook("Good");
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		Thread t3 = new Thread(r3);
		Thread t4 = new Thread(r4);
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		t3.start();
		t3.join();
		t4.start();
		t4.join();
	}

}
class Book 
{
	 private String title;
	 private String author;
	 private String ISBN;
	public Book(String title, String author, String iSBN) 
	{
		super();
		this.title = title;
		this.author = author;
		this.ISBN = iSBN;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getISBN() {
		return ISBN;
	}
	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}
	 @Override
	    public String toString() {
	        return "Book{" +
	                "title='" + title + '\'' +
	                ", author='" + author + '\'' +
	                ", ISBN='" + ISBN + '\'' +
	                '}';
	    }
}
class User 
{
	 private String name;
	 private String userID;
	 private List<Book> borrowedBooks;
	public User(String name, String userID, List<Book> borrowedBooks) 
	{
		super();
		this.name = name;
		this.userID = userID;
		this.borrowedBooks = borrowedBooks;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public List<Book> getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(List<Book> borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
}
interface ILibrary 
{
	 void borrowBook(String ISBN, String userID) throws BookNotFoundException,
	UserNotFoundException, MaxBooksAllowedException;
	 void returnBook(String ISBN, String userID) throws BookNotFoundException,
	UserNotFoundException;
	 void reserveBook(String ISBN, String userID) throws BookNotFoundException,
	UserNotFoundException;
	 Book searchBook(String title);
}
abstract class LibrarySystem implements ILibrary 
{
	 protected List<Book> books;
	 protected List<User> users;
	 public abstract void addBook(Book book);
	 public abstract void addUser(User user);
	 // Implementations for ILibrary methods
	
}
class BookNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	public BookNotFoundException(String str){
		super(str);
	}
}
class UserNotFoundException extends Exception
{
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String str) {
		super(str);
	}
}
class MaxBooksAllowedException extends Exception
{
	private static final long serialVersionUID = 1L;

	public MaxBooksAllowedException(String str){
		super(str);
	}
}
class LibraryManager extends LibrarySystem {
	 // Detailed implementations for all abstract methods and interface methods
	 // Thread-safe implementations for borrowBook, returnBook methods
	 // Exception handling within all methods
	LibraryManager()
	{
		this.books = new ArrayList<>();
		this.users = new ArrayList<>();
	}
	 public synchronized void borrowBook(String ISBN, String userID) {
		 try{
			 Book bookToBorrow = null;
			 User userBorrowing = null;
			 for(Book b : books) {
				  
				 if(b.getISBN().equals(ISBN)){
					 bookToBorrow = b;
					 System.out.println("book borrowed");
					 System.out.println(b.getTitle()+" "+b.getAuthor()+" "+b.getISBN());
				 }
				 if( bookToBorrow == null){
					 throw new BookNotFoundException("Book is not found");
				 }
			 }
			 for(User u : users){
				 	
				 if(u.getUserID().equals(userID)){
					 userBorrowing = u;
					 userBorrowing.getBorrowedBooks().add(bookToBorrow);
					 System.out.println("user details");
					 System.out.println(u.getName()+" "+u.getUserID()+" "+u.getBorrowedBooks());
				 	}
				 if(userBorrowing == null) {
				 		throw new UserNotFoundException("user is not found");
				 	}
				 if (userBorrowing.getBorrowedBooks().size() >= 5) { 
				        throw new MaxBooksAllowedException("book out of limit");
				    }
			 }
			}
			catch(BookNotFoundException e){
				System.out.println(e);
			}
		 	catch(UserNotFoundException e){
				System.out.println(e);
			}
		 	catch(MaxBooksAllowedException e) {
		 		System.out.println(e);
		 	}
	 }
	 public synchronized void returnBook(String ISBN, String userID){
		 try
			{
			 Book bookToBorrow = null;
			 User userBorrowing = null;
			 for(Book b : books){
				 if(b.getISBN().equals(ISBN)){
					 bookToBorrow = b;
					 System.out.println("return borrowed");
					 System.out.println(b.getTitle()+" "+b.getAuthor()+" "+b.getISBN());
				 }
				 if( bookToBorrow == null){
					 throw new BookNotFoundException("Book is not found");
				 }
			}
			 for(User u : users){
				
				 if(u.getUserID().equals(userID)){
					 userBorrowing = u;
					 userBorrowing.getBorrowedBooks().remove(bookToBorrow);
					 System.out.println("user details");
					 System.out.println(u.getName()+" "+u.getUserID()+" "+u.getBorrowedBooks());
				 	}
				 if(userBorrowing == null) {
				 		throw new UserNotFoundException("user is not found");
				 	}
			 }
			}
			catch(BookNotFoundException e){
				System.out.println(e);
			}
			catch(UserNotFoundException e){
				System.out.println(e);
			}
			
		}
	 
	 public synchronized void reserveBook(String ISBN, String userID){
		 try {
			 Book bookToBorrow = null;
			 User userBorrowing = null;
		     for(Book b : books) {
				
		    	 if(b.getISBN().equals(ISBN)){
					 bookToBorrow = b;
					 System.out.println("book borrowed");
					 System.out.println(b.getTitle()+" "+b.getAuthor()+" "+b.getISBN());
				 }
				 if( bookToBorrow == null){
					 throw new BookNotFoundException("Book is not found");
				 }			  
			}
			 for(User u : users){
				 
				 if(u.getUserID().equals(userID)){
					 userBorrowing = u;
					 userBorrowing.getBorrowedBooks().add(bookToBorrow);
					 System.out.println("user details");
					 System.out.println(u.getName()+" "+u.getUserID()+" "+u.getBorrowedBooks());
				 	}
				 if(userBorrowing == null) {
				 		throw new UserNotFoundException("user is not found");
				 	}
			 }
		 }
			 catch(BookNotFoundException e){
					System.out.println(e);
			 }
		 	 catch(UserNotFoundException e){
		 			System.out.println(e);
			 }
			}
	 
	 public synchronized Book searchBook(String title) {
		 for(Book b : books){
			
			 if(b.getTitle().equalsIgnoreCase(title)){
				 System.out.println("Book found: " + b.getTitle() + " by " + b.getAuthor() + " (ISBN: " + b.getISBN() + ")");
				 return b;
			 }
		  }
		 return null;
			 
	 }
	 
	 public void addBook(Book book)
	 {
		books.add(book);
	 }
	 public void addUser(User user)
	 {
		 users.add(user);
	 }
}	 