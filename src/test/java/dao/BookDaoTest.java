package dao;

import model.Author;
import model.Book;
import model.Client;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

/**
 * Created by alexp on 16.1.11.
 */
public class BookDaoTest {

    private EntityManagerFactory factory;
    private BookDao bookDao;
    private Client client;


    @BeforeClass
    public void setUpBeforeClass() throws Exception {
        factory = Persistence.createEntityManagerFactory("hibernate-unit");
        client  = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        bookDao = new BookDao(factory, client);
    }

    @Test
    public void add() throws Exception {
        Book book = new Book("Java", 2010, "Technical", new Author("Oleg", "GG"), 1);
        assertTrue(bookDao.add(book));
        assertEquals(book, bookDao.searchBookTitle(book.getTitle()));
    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }


    @Test
    public void takeBook() throws Exception {

    }

    @Test
    public void returnBook() throws Exception {

    }

    @Test
    public void reservationBook() throws Exception {

    }

    @Test@AfterClass
    public void tearDown() throws Exception {

    }
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

    @Test
    public void editBookCount() throws Exception {

    }

    @Test
    public void deleteBookNumber() throws Exception {

    }



    @AfterClass
    public void tearDownAfterClass() throws Exception {
        factory.close();
        bookDao = null;
        client = null;
    }

}