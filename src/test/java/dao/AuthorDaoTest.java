package dao;

import dao.interfaces.AuthorDao;
import dao.interfaces.BookDao;
import model.Author;
import model.Book;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import service.GeneralService;
import spring_config.SpringConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 16.2.11.
 */
public class AuthorDaoTest {

    /*private static AuthorDao authorDao;
    private static BookDao bookDao;*/

    private static GeneralService generalService;
    private static Author author;
    private static ApplicationContext context;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        context = new AnnotationConfigApplicationContext(SpringConfig.class);
        generalService = context.getBean(GeneralService.class);

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

        assertTrue(generalService.addAuthor(author));
        Author authorFromDb = generalService.findAuthor(author);
        assertEquals(author, authorFromDb);
        assertTrue(generalService.deleteAuthor(author));
    }

    @Test
    public void delete() throws Exception {

        assertTrue(generalService.addAuthor(author));
        assertTrue(generalService.deleteAuthor(author));
        assertNull(generalService.findAuthor(author));
    }

    @Test
    public void update() throws Exception {

        assertTrue(generalService.addAuthor(author));
        author.setName("Rob");
        author.setSurname("Kenzie");
        assertTrue(generalService.updateAuthor(author));
        assertEquals(author, generalService.findAuthor(author));
        assertTrue(generalService.deleteAuthor(author));
    }

    @Test
    public void authorsList() throws Exception {
        Author author1 = new Author("Bill", "Force");
        Author author2 = new Author("Fred", "Mirror");

        List<Author> expected = new ArrayList<>();
        Collections.addAll(expected, author, author1, author2);

        generalService.addAuthor(author);
        generalService.addAuthor(author1);
        generalService.addAuthor(author2);

        List<Author> authors = generalService.getAllAuthors();

        assertEquals(expected.size(), authors.size());
        assertTrue(authors.containsAll(expected));

        expected.stream().forEach(currentAuthor -> {
            assertTrue(generalService.deleteAuthor(currentAuthor));
        });

    }

    @Test
    public void searchByAuthor() throws Exception {

        generalService.addAuthor(author);

        Book book1 = new Book("java1", 1, "Tech", author, 3);
        Book book2 = new Book("java2", 2, "Tech", author, 3);
        Book book3 = new Book("java3", 3, "Tech", author, 3);

        List<Book> expected = new ArrayList<>();
        Collections.addAll(expected, book1, book2, book3);

        expected.stream().forEach(currentBook -> {
            assertTrue(generalService.addBook(currentBook));
        });

        List<Book> actual = generalService.searchBookAuthor(author);

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));

        /*expected.stream().forEach(currentBook -> {
            assertTrue(generalService.deleteBook(currentBook));
        });*/

        assertTrue(generalService.deleteAuthor(author));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        generalService = null;
    }
}