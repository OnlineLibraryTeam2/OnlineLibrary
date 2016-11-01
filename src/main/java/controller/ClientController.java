package controller;

import dao.AuthorDao;
import dao.BookDao;
import dao.ClientDao;
import model.Author;
import model.Book;
import model.Client;

import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * Created by alexp on 16.1.11.
 */
public class ClientController {
    // null
    private AuthorDao authorDao;
    private BookDao bookDao;
    private ClientDao clientDao;
    private EntityManagerFactory factory;

    public ClientController(AuthorDao authorDao, BookDao bookDao, ClientDao clientDao, EntityManagerFactory factory) {
        this.authorDao = authorDao;
        this.bookDao = bookDao;
        this.clientDao = clientDao;
        this.factory = factory;
    }

    public boolean takeBook(Book book) {
        return bookDao.takeBook(book);
    }

    public boolean returnBook(Book book) {
        return bookDao.returnBook(book);
    }

    public boolean registration(String name, String surname, int age, String phoneNumber, String password, String loginMail) {
        Client client = new Client(name, surname, age, phoneNumber, loginMail, password, false);
        return clientDao.add(client);
    }

    public boolean signIn(String loginMail, String password) {
        return clientDao.signIn(loginMail, password);
    }

    public int reservation(Book book) {
        return bookDao.reservationBook(book);
    }

    public List<Book> searchBookTitle(String title) {
        return bookDao.searchBookTitle(title);
    }

    public List<Book> searchBookAuthor(Author author) {
        return authorDao.searchByAuthor(author);
    }

    public List<Book> searchByYear(int year) {
        return bookDao.searchByYear(year);
    }

    public List<Book> recomendedBooks(String genreBook) {
        return bookDao.recommendedBooks(genreBook);
    }

    public List<Book> showAllBooks() {
        return bookDao.showAllBooks();
    }

}