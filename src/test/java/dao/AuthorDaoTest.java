package dao;

import dao.interfaces.AuthorDao;
import dao.interfaces.BookDao;
import model.Author;
import model.Book;
import org.junit.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 16.2.11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-test-context.xml")
public class AuthorDaoTest {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    private static Author author;


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
        Author authorFromDb = authorDao.findAuthor(author);
        assertEquals(author, authorFromDb);
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void delete() throws Exception {

        assertTrue(authorDao.add(author));
        assertTrue(authorDao.delete(author));
        assertEquals(0, authorDao.authorsList().size());
    }

    @Test
    public void update() throws Exception {



        assertTrue(authorDao.add(author));
        author.setName("Rob");
        author.setSurname("Kenzie");
        assertTrue(authorDao.update(author));
        assertEquals(author, authorDao.findAuthor(author));
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void authorsList() throws Exception {
        Author author1 = new Author("Bill", "Force");
        Author author2 = new Author("Fred", "Mirror");

        List<Author> expected = new ArrayList<>();
        Collections.addAll(expected, author, author1, author2);

        for (Author current: expected) {
            authorDao.add(current);
        }

        List<Author> authors = authorDao.authorsList();

        assertEquals(expected.size(), authors.size());
        assertTrue(authors.containsAll(expected));

        for (Author current: expected) {
            authorDao.delete(current);
        }

    }

    @Test
    public void searchByAuthor() throws Exception {

        authorDao.add(author);

        Book book1 = new Book("java1", 1, "Tech", author, 3);
        Book book2 = new Book("java2", 2, "Tech", author, 3);
        Book book3 = new Book("java3", 3, "Tech", author, 3);

        List<Book> expected = new ArrayList<>();
        Collections.addAll(expected, book1, book2, book3);

        for (Book current: expected) {
            bookDao.add(current);
        }

        List<Book> actual = authorDao.searchByAuthor(author);

        assertEquals(expected.size(), actual.size());
        assertTrue(actual.containsAll(expected));

        assertTrue(authorDao.delete(author));
    }

}