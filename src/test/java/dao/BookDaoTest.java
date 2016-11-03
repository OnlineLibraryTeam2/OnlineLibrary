package dao;

import model.Author;
import model.Book;
import model.Client;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private static EntityManagerFactory factory;
    private static BookDao bookDao;
    private static Client client;


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        factory = Persistence.createEntityManagerFactory("hibernate-unit");
        client  = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        bookDao = new BookDao(factory, client);
    }

    @Test
    public void add() throws Exception {
        Book book = new Book("Java", 2010, "Technical", new Author("Oleg", "GG"), 1);
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
        assertTrue(bookDao.delete(book));
    }

    @Test
    public void update() throws Exception {
        Book book  = new Book("Java8", 2010, "Technical", new Author("Oleg", "GG"), 1);
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
        Book book  = new Book("Java8", 2010, "Technical", new Author("Oleg", "GG"), 1);
        assertTrue(bookDao.add(book));
        assertTrue(bookDao.delete(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(0, books.size());
    }


    @Test
    public void takeBook() throws Exception {
        Book book  = new Book("Java8", 2010, "Technical", new Author("Oleg", "GG"), 1);
        assertTrue(bookDao.add(book));
        assertTrue(bookDao.takeBook(book));


    }

    @Test
    public void returnBook() throws Exception {

    }


    @Test
    public void searchBookTitle() throws Exception {

    }

    @Test
    public void searchByYear() throws Exception {

    }

    @Test
    public void recommendedBooks() throws Exception {

    }

    @Test
    public void showAllBooks() throws Exception {

    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        factory.close();
        bookDao = null;
        client = null;
    }





}