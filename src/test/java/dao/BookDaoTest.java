package dao;

import model.Author;
import model.Book;
import model.Client;
import org.junit.*;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexp on 16.1.11.
 * Amended by DP.
 */
public class BookDaoTest {


    private static BookDao bookDao;
    private static AuthorDao authorDao;
    private static ClientDao clientDao;
    private static Client client;
    private static Author author;



    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        client  = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        author = new Author("Fedya", "Vasilyev");
        authorDao.add(author);
        clientDao.add(client);
        author = authorDao.findAuthor(author);
        client = clientDao.findClientByMail(client.getLoginMail());
        bookDao = new BookDao();
    }

    @Test
    public void add() throws Exception {
        Book book = new Book("Java", 2010, "Technical", author, 1);
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
        assertTrue(bookDao.delete(book));
    }

    @Test
    public void update() throws Exception {
        Book book  = new Book("Java8", 2010, "Technical", author, 1);
        assertTrue(bookDao.add(book));
        book.setTitle("Java9");
        book.setYear(2012);
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
        assertTrue(bookDao.delete(book));

    }

    @Test
    public void delete() throws Exception {
        Book book  = new Book("Java8", 2010, "Technical", author, 1);
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertTrue(bookDao.delete(books.get(0)));
        assertEquals(0, bookDao.showAllBooks().size());
    }

    @Test
    public void takeBook() throws Exception {
        Book book  = new Book("Java10", 2012, "Technical", author, 3);

        assertTrue(bookDao.add(book));
        book = authorDao.searchByAuthor(author).get(0);
        assertTrue(bookDao.takeBook(book, client));
        List<Book> ourAddedBook = bookDao.searchBookTitle(book.getTitle());
        assertTrue(bookDao.delete(book));
    }

    @Test
    public void returnBook() throws Exception {
        Book book  = new Book("Java8", 2010, "Technical", author, 5);

        assertTrue(bookDao.add(book));
        assertTrue(bookDao.takeBook(book, client));

        assertTrue(bookDao.returnBook(book, client));
        List<Book> ourReturnedBook = bookDao.searchBookTitle(book.getTitle());
        assertEquals(5, ourReturnedBook.get(0).getBookCount());
        assertTrue(ourReturnedBook.contains(book));
        assertTrue(bookDao.delete(book));
    }

    @Test
    public void searchBookTitle() throws Exception {
        Book book1  = new Book("Java8", 2010, "Technical", author, 1);
        Book book2  = new Book("Java8", 2012, "Technical", author, 1);
        assertTrue(bookDao.add(book1));
        List<Book> ourAddedBook = bookDao.searchBookTitle(book1.getTitle());
        assertEquals(1, ourAddedBook.size());
        assertEquals(book1.getTitle(), ourAddedBook.get(0).getTitle());
        assertTrue(bookDao.add(book2));
        ourAddedBook = bookDao.searchBookTitle("Java8");
        assertEquals(2, ourAddedBook.size());
        assertTrue(bookDao.delete(book1));
        assertTrue(bookDao.delete(book2));
    }

    @Test
    public void searchByYear() throws Exception {
        Book book1  = new Book("Java8", 2010, "Technical", author, 1);
        Book book2  = new Book("Java Persistence API", 2010, "Technical", author, 1);
        Book book3  = new Book("Spring Framework", 2010, "Technical", author, 1);
        assertTrue(bookDao.add(book1));
        List<Book> ourAddedBook = bookDao.searchByYear(book1.getYear());
        assertEquals(1, ourAddedBook.size());
        assertEquals(book1.getYear(), ourAddedBook.get(0).getYear());
        assertTrue(bookDao.add(book2));
        assertTrue(bookDao.add(book3));
        ourAddedBook = bookDao.searchByYear(2010);
        assertEquals(3, ourAddedBook.size());
        assertTrue(bookDao.delete(book1));
        assertTrue(bookDao.delete(book2));
        assertTrue(bookDao.delete(book3));
    }

    @Test
    public void recommendedBooks() throws Exception {
        Book book1  = new Book("Java8", 2010, "Technical", author, 1);
        Book book2  = new Book("Java Persistence API", 2010, "Technical", author, 1);
        Book book3  = new Book("Spring Framework", 2010, "Technical", author, 1);
        Book book4  = new Book("Alice in Wonderland", 2010, "Fantasy", author, 1);
        Book book5  = new Book("Bill Gates' life", 2011, "Biography", author, 1);
        Book book6  = new Book("Harry Potter", 2005, "Fantasy", author, 1);
        assertTrue(bookDao.add(book1));
        assertTrue(bookDao.add(book2));
        assertTrue(bookDao.add(book3));
        assertTrue(bookDao.add(book4));
        assertTrue(bookDao.add(book5));
        assertTrue(bookDao.add(book6));
        List<Book> booksSelectByGenre = bookDao.recommendedBooks("Technical");
        assertEquals(3, booksSelectByGenre.size());
        booksSelectByGenre = bookDao.recommendedBooks("Fantasy");
        assertEquals(2, booksSelectByGenre.size());
        assertTrue(bookDao.delete(book1));
        assertTrue(bookDao.delete(book2));
        assertTrue(bookDao.delete(book3));
        assertTrue(bookDao.delete(book4));
        assertTrue(bookDao.delete(book5));
        assertTrue(bookDao.delete(book6));
    }

    @Test
    public void showAllBooks() throws Exception {
        Book book1  = new Book("Java8", 2010, "Technical", author, 1);
        Book book2  = new Book("Java Persistence API", 2010, "Technical", author, 1);
        Book book3  = new Book("Spring Framework", 2010, "Technical", author, 1);
        Book book4  = new Book("Alice in Wonderland", 2010, "Fantasy", author, 1);
        Book book5  = new Book("Bill Gates' life", 2011, "Biography", author, 1);
        Book book6  = new Book("Harry Potter", 2005, "Fantasy", author, 1);
        assertTrue(bookDao.add(book1));
        assertTrue(bookDao.add(book2));
        assertTrue(bookDao.add(book3));
        assertTrue(bookDao.add(book4));
        assertTrue(bookDao.add(book5));
        assertTrue(bookDao.add(book6));
        List<Book> allBooks = bookDao.showAllBooks();
        assertEquals(6, allBooks.size());
        assertTrue(bookDao.delete(book1));
        allBooks = bookDao.showAllBooks();
        assertEquals(5, allBooks.size());
        assertTrue(bookDao.delete(book2));
        assertTrue(bookDao.delete(book3));
        assertTrue(bookDao.delete(book4));
        assertTrue(bookDao.delete(book5));
        assertTrue(bookDao.delete(book6));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        bookDao = null;
        clientDao = null;
        authorDao = null;
        author = null;
        client = null;
    }

}