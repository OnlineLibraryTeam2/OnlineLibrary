package dao;

import model.Author;
import model.Book;
import model.Client;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.GeneralService;
import spring_config.SpringConfig;

import static org.junit.Assert.*;

/**
 * Created by alexp on 16.1.11.
 * Amended by DP.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:app-test-context.xml")
public class BookDaoImplTest {

    private static ApplicationContext context;
    private static GeneralService service;
    private static Author author;
    private static Book book;

    /*private static BookDao bookDao;
    private static AuthorDao authorDao;
    private static ClientDao clientDaoImpl;
    private static Client client;
    private static Author author;*/


    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        context = new AnnotationConfigApplicationContext(SpringConfig.class);
        service = context.getBean(GeneralService.class);
        /*client  = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        author = new Author("Fedya", "Vasilyev");
        authorDao.add(author);
        clientDaoImpl.add(client);
        author = authorDao.findAuthor(author);
        client = clientDaoImpl.findClientByMail(client.getLoginMail());
        bookDao = new BookDaoImpl();*/
    }

    @Before
    public void setUp() throws Exception {
        author = new Author("Fedya", "Vasilyev");
        book = new Book("Java", 2010, "Technical", author, 3);
    }

    @Test
    public void add() throws Exception {
        assertTrue(service.addAuthor(author));
        assertTrue(service.addBook(book));
        Book bookFromDb = service.findBook(book);
        assertEquals(book, bookFromDb);
        assertTrue(service.deleteBook(book));
        assertTrue(service.deleteAuthor(author));
        /*Book book = new Book("Java", 2010, "Technical", author, 1);
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
        assertTrue(bookDao.delete(book));*/
    }

    @Test
    public void update() throws Exception {
        assertTrue(service.addAuthor(author));
        assertTrue(service.addBook(book));
        book.setTitle("Java Persistence API");
        book.setYear(2010);
        book.setGenre("Technical");
        Author newAuthor = new Author("Petya", "Petrov");
        newAuthor.setId(author.getId());
        book.setAuthor(newAuthor);
        book.setBookCount(5);
        assertTrue(service.updateAuthor(newAuthor));
        assertTrue(service.updateBook(book));
        assertEquals(book, service.findBook(book));
        assertTrue(service.deleteBook(book));
        assertTrue(service.deleteAuthor(newAuthor));
        /*Book book  = new Book("Java8", 2010, "Technical", author, 1);
        assertTrue(bookDao.add(book));
        book.setTitle("Java9");
        book.setYear(2012);
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertEquals(book, books.get(0));
        assertTrue(bookDao.delete(book));*/
    }

    @Test
    public void delete() throws Exception {
        assertTrue(service.addAuthor(author));
        assertTrue(service.addBook(book));
        assertEquals(book, service.findBook(book));
        assertTrue(service.deleteBook(book));
        assertTrue(service.deleteAuthor(author));
        assertNull(service.findBook(book));
        assertNull(service.findAuthor(author));
        /*Book book  = new Book("Java8", 2010, "Technical", author, 1);
        assertTrue(bookDao.add(book));
        List<Book> books = bookDao.searchBookTitle(book.getTitle());
        assertEquals(1, books.size());
        assertTrue(bookDao.delete(books.get(0)));
        assertEquals(0, bookDao.showAllBooks().size());*/
    }

    @Test
    public void takeBook() throws Exception {
        assertTrue(service.addAuthor(author));
        assertTrue(service.addBook(book));
        Client client  = new Client("Ivan", "Ivanov", 23, "371", "mail.com", "1234");
        assertTrue(service.addClient(client));
        assertFalse(client.getTakenBooks().contains(book));
        Book initialBook = book;
        assertTrue(service.takeBook(book, client));
        assertEquals(initialBook.getBookCount() -1, service.findBook(book).getBookCount());
        assertTrue(client.getTakenBooks().contains(book));
        assertTrue(service.deleteBook(book));
        assertTrue(service.deleteAuthor(author));
        assertTrue(service.deleteClient(client));
        /*Book book  = new Book("Java10", 2012, "Technical", author, 3);

        assertTrue(bookDao.add(book));
        book = authorDao.searchByAuthor(author).get(0);
        assertTrue(bookDao.takeBook(book, client));
        List<Book> ourAddedBook = bookDao.searchBookTitle(book.getTitle());
        assertTrue(bookDao.delete(book));*/
    }

    /*@Test
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
    }*/

    @After
    public void tearDown() throws Exception {
        author = null;
        book = null;
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        service = null;
        /*bookDao = null;
        clientDaoImpl = null;
        authorDao = null;
        author = null;
        client = null;*/
    }

}