package dao;

import dao.interfaces.AuthorDao;
import dao.interfaces.BookDao;
import dao.interfaces.ClientDao;
import model.Author;
import model.Book;
import model.Client;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by alexp on 16.1.11.
 * Amended by DP.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-test-context.xml")
public class BookDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ClientDao clientDao;

    private static Book book;
    private static Author author;

    @Before
    public void setUp() throws Exception {
        author = new Author("Fedya", "Vasilyev");
        book = new Book("Java", 2010, "Technical", author, 3);
    }

    @Test
    public void add() throws Exception {
        assertTrue(authorDao.add(author));
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
        assertTrue(bookDao.delete(book));
    }

    @Test
    public void update() throws Exception {
        book.setTitle("Waterfall");
        book.setYear(1976);
        assertTrue(authorDao.add(author));
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals("Waterfall", books.get(0).getTitle());
        assertEquals(1976, books.get(0).getYear());
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
        assertTrue(bookDao.delete(book));
    }

    @Test
    public void delete() throws Exception {
        assertTrue(authorDao.add(author));
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertTrue(bookDao.delete(books.get(0)));
        assertEquals(0, bookDao.showAllBooks().size());
    }

    @Test
    public void takeBook() throws Exception {
        assertTrue(authorDao.add(author));
        assertTrue(bookDao.add(book));
        assertEquals(book, bookDao.findBook(book));
        Client client  = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        clientDao.add(client);
        assertTrue(bookDao.takeBook(book, client));
        assertTrue(clientDao.delete(client));
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void returnBook() throws Exception {
        assertTrue(authorDao.add(author));
        assertTrue(bookDao.add(book));
        Client client  = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        clientDao.add(client);
        assertTrue(bookDao.takeBook(book, client));
        assertTrue(bookDao.returnBook(book, client));
        Book ourReturnedBook = bookDao.findBook(book);
        assertEquals(3, ourReturnedBook.getBookCount());
        assertTrue(clientDao.delete(client));
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void searchBookTitle() throws Exception {
        Book book1  = new Book("Java8", 2010, "Technical", author, 1);
        Book book2  = new Book("Java8", 2012, "Technical", author, 1);
        assertTrue(authorDao.add(author));
        assertTrue(bookDao.add(book1));
        List<Book> ourAddedBook = bookDao.searchBookTitle(book1.getTitle());
        assertEquals(1, ourAddedBook.size());
        assertEquals(book1.getTitle(), ourAddedBook.get(0).getTitle());
        assertTrue(bookDao.add(book2));
        ourAddedBook = bookDao.searchBookTitle("Java8");
        assertEquals(2, ourAddedBook.size());
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void searchByYear() throws Exception {
        Book book1  = new Book("Java8", 2010, "Technical", author, 1);
        Book book2  = new Book("Java Persistence API", 2010, "Technical", author, 1);
        Book book3  = new Book("Spring Framework", 2010, "Technical", author, 1);
        assertTrue(authorDao.add(author));
        assertTrue(bookDao.add(book1));
        List<Book> ourAddedBook = bookDao.searchByYear(book1.getYear());
        assertEquals(1, ourAddedBook.size());
        assertEquals(book1.getYear(), ourAddedBook.get(0).getYear());
        assertTrue(bookDao.add(book2));
        assertTrue(bookDao.add(book3));
        ourAddedBook = bookDao.searchByYear(2010);
        assertEquals(3, ourAddedBook.size());
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void recommendedBooks() throws Exception {
        Book book1  = new Book("Java8", 2010, "Technical", author, 1);
        Book book2  = new Book("Java Persistence API", 2010, "Technical", author, 1);
        Book book3  = new Book("Spring Framework", 2010, "Technical", author, 1);
        Book book4  = new Book("Alice in Wonderland", 2010, "Fantasy", author, 1);
        Book book5  = new Book("Bill Gates' life", 2011, "Biography", author, 1);
        Book book6  = new Book("Harry Potter", 2005, "Fantasy", author, 1);
        assertTrue(authorDao.add(author));
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
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void showAllBooks() throws Exception {
        Book book1  = new Book("Java8", 2010, "Technical", author, 1);
        Book book2  = new Book("Java Persistence API", 2010, "Technical", author, 1);
        Book book3  = new Book("Spring Framework", 2010, "Technical", author, 1);
        Book book4  = new Book("Alice in Wonderland", 2010, "Fantasy", author, 1);
        Book book5  = new Book("Bill Gates' life", 2011, "Biography", author, 1);
        Book book6  = new Book("Harry Potter", 2005, "Fantasy", author, 1);
        assertTrue(authorDao.add(author));
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
        assertTrue(authorDao.delete(author));
    }

    @After
    public void tearDown() throws Exception {
        author = null;
        book = null;
    }

}