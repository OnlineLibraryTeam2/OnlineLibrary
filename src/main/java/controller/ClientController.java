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

    private AuthorDao authorDao;
    private BookDao bookDao;
    private ClientDao clientDao;
    private EntityManagerFactory factory;

    public ClientController(EntityManagerFactory factory) {
        this.factory = factory;
        this.authorDao = new AuthorDao(this.factory);
        this.bookDao = new BookDao(this.factory);
        this.clientDao = new ClientDao(this.factory);
    }

    public boolean takeBook(Book book, Client client) {
        return bookDao.takeBook(book, client);
    }

    public boolean returnBook(Book book, Client client) {
        return bookDao.returnBook(book, client);
    }

    public boolean registration(Client client) {
        return clientDao.add(client);
    }

    public Client signIn(String loginMail, String password) {
        return clientDao.signIn(loginMail, password);
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

    public List<Book> recommendedBooks(String genreBook) {
        return bookDao.recommendedBooks(genreBook);
    }

    public List<Book> showAllBooks() {
        return bookDao.showAllBooks();
    }

}