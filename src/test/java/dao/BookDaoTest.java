package dao;

import controller.AdminController;
import controller.ClientController;
import model.Author;
import model.Book;
import model.Client;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexp on 16.1.11.
 * Amended by DP.
 */
public class BookDaoTest {

    private static EntityManagerFactory factory;
    private static BookDao bookDao;
    private static Client client;
    private static Author author;
    private static AdminController adminController;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        factory = Persistence.createEntityManagerFactory("hibernate-unit");
        client  = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        adminController = new AdminController(factory);
        adminController.addAuthor("Fedya", "Vasilyev");
        author = adminController.getAllAuthors().get(0);
        bookDao = new BookDao(factory);
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
        assertTrue(bookDao.update(book));
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
        book.setId(books.get(0).getId());
        assertEquals(1, books.size());
        assertTrue(bookDao.delete(books.get(0)));
        /*books.remove(0);
        assertEquals(0, books.size());*/
        assertEquals(0, bookDao.showAllBooks());
    }

    @Test
    public void takeBook() throws Exception {
        Book book  = new Book("Java8", 2010, "Technical", author, 5);
        ClientController clientController = new ClientController(factory);
        clientController.registration(client);
        client = adminController.findClientByMail(client.getLoginMail());
        assertTrue(bookDao.add(book));
        assertTrue(bookDao.takeBook(book, client));
        List<Book> ourAddedBook = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, ourAddedBook.size());
        int addedBookCount = ourAddedBook.get(0).getBookCount();
        assertEquals(book.getBookCount() - 1, addedBookCount);
        assertTrue(bookDao.delete(book));
    }

    @Test
    public void returnBook() throws Exception {
        Book book  = new Book("Java8", 2010, "Technical", author, 5);
        ClientController clientController = new ClientController(factory);
        clientController.registration(client);
        assertTrue(bookDao.add(book));
        assertTrue(bookDao.takeBook(book, client));
        List<Book> ourTakenBook = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, ourTakenBook.size());
        int addedBookCount = ourTakenBook.get(0).getBookCount();
        assertEquals(book.getBookCount() - 1, addedBookCount);
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
        factory.close();
        bookDao = null;
        client = null;
    }

}