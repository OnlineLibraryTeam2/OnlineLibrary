package dao;

import model.Author;
import org.junit.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by student on 16.2.11.
 */
public class AuthorDaoTest {
    private static EntityManagerFactory factory;
    static AuthorDao authorDao;
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        factory = Persistence.createEntityManagerFactory("hibernate-unit");
        authorDao = new AuthorDao(factory);
    }

    @Test
    public void delete() throws Exception {
        Author author = new Author("Danny", "Hibikos");
        assertTrue(authorDao.add(author));
        assertTrue(authorDao.delete(author));
        List<Author> authors = authorDao.authorsList();
        assertEquals(0, authors.size());
    }

    @Test
    public void authorsList() throws Exception {

    }

    @Test
    public void searchByAuthor() throws Exception {

    }

    @Test
    public void add() throws Exception {
        Author author = new Author("Dan", "Hibiki");

        assertTrue(authorDao.add(author));
        List<Author> authors = authorDao.authorsList();
        assertFalse(authors.isEmpty());
        assertEquals(1, authors.size());
        assertEquals(author, authors.get(0));
        assertTrue(authorDao.delete(author));
    }

    @Test
    public void update() throws Exception {
        Author author = new Author("Danny", "Hibikos");

        assertTrue(authorDao.add(author));
        author.setName("Rob");
        author.setSurname("Kenzie");
        assertTrue(authorDao.update(author));
        List<Author> authors = authorDao.authorsList();
        assertEquals(1, authors.size());
        assertEquals(author, authors.get(0));
        assertTrue(authorDao.delete(author));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        factory.close();
        authorDao = null;

    }
}