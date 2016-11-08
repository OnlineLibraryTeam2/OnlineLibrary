package dao;

import model.Author;
import model.Book;
import model.Client;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring_config.SpringConfig;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 16.2.11.
 */
public class AuthorDaoTest {

    private static AuthorDao authorDao;
    private static Author author;
    private static BookDao bookDao;
    private static ApplicationContext context;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
        authorDao = context.getBean(AuthorDao.class);
        bookDao = context.getBean(BookDao.class);
    }

    @Before
    public void setUp() throws Exception {
        author = new Author("Dan", "Hibiki");
    }

    @After
    public void tearDown() throws Exception {
        author = null;
    }

    @Test
    public void add() throws Exception {

        assertTrue(authorDao.add(author));
        List<Author> authors = authorDao.authorsList();
        assertFalse(authors.isEmpty());
        assertEquals(1, authors.size());
        assertEquals(author, authors.get(0));
        assertTrue(authorDao.delete(author));

        author = null;
        assertFalse(authorDao.add(author));
    }

    @Test
    public void add_same_author() throws Exception {

        assertTrue(authorDao.add(author));
        assertFalse(authorDao.add(author));
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void delete() throws Exception {

        assertTrue(authorDao.add(author));
        assertTrue(authorDao.delete(author));
        List<Author> authors = authorDao.authorsList();
        assertEquals(0, authors.size());
    }

    @Test
    public void update() throws Exception {

        assertTrue(authorDao.add(author));
        author.setName("Rob");
        author.setSurname("Kenzie");
        assertTrue(authorDao.add(author));
        List<Author> authors = authorDao.authorsList();
        assertEquals(1, authors.size());
        assertEquals(author, authors.get(0));
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void authorsList() throws Exception {
        Author author1 = new Author("Bill", "Force");
        Author author2 = new Author("Fred", "Mirror");

        List<Author> expected = new ArrayList<>();
        Collections.addAll(expected, author, author1, author2);

        assertTrue(authorDao.add(author));
        assertTrue(authorDao.add(author1));
        assertTrue(authorDao.add(author2));

        List<Author> authors = authorDao.authorsList();

        assertFalse(authors.isEmpty());
        assertEquals(3, authors.size());
        assertTrue(authors.containsAll(expected));

        expected.stream().forEach(author -> {
            assertTrue(authorDao.delete(author));
        });


    }

    @Test
    public void searchByAuthor() throws Exception {

        assertTrue(authorDao.add(author));
        List<Author> authors =  authorDao.authorsList();
        assertFalse(authors.isEmpty());
        assertEquals(1, authors.size());
        author = authors.get(0);

        Book book1 = new Book("java1", 1, "Tech", author, 3);
        Book book2 = new Book("java2", 2, "Tech", author, 3);
        Book book3 = new Book("java3", 3, "Tech", author, 3);

        List<Book> expected = new ArrayList<>();
        Collections.addAll(expected, book1, book2, book3);



        expected.stream().forEach(book -> {
            assertTrue(bookDao.add(book));
        });

        List<Book> actual = authorDao.searchByAuthor(author);

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));

        expected.stream().forEach(book -> {
            assertTrue(bookDao.delete(book));
        });

        assertTrue(authorDao.delete(author));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        authorDao = null;

    }
}