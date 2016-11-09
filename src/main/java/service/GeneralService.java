package service;

import dao.ClientDaoImpl;
import dao.interfaces.AuthorDao;
import dao.BookDaoImpl;

import dao.interfaces.BookDao;
import dao.interfaces.ClientDao;
import model.Author;
import model.Book;
import model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by alexp on 16.7.11.
 */
@Component
public class GeneralService {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private ClientDao clientDaoImpl;


    public boolean deleteClient(Client client) {
        return clientDaoImpl.delete(client);
    }

    public Client findClientByMail(String mailClient) {
        return clientDaoImpl.findClientByMail(mailClient);
    }

    public List<Client> showClients() {
        return clientDaoImpl.showAllClients();
    }

    public List<Client> showBlackList() {
        return clientDaoImpl.showBlacklist();
    }

    public boolean addBlackList(Client client) {
        return clientDaoImpl.addBlacklist(client);
    }

    public boolean deleteBlackList(Client client) {
        return clientDaoImpl.deleteFromBlacklist(client);
    }

    public boolean addBook(String title, int year, String genre, Author author, int bookCount) {
        Book book = new Book(title, year, genre, author, bookCount);

        return bookDao.add(book);
    }

    public boolean deleteBook(Book book) {
        return bookDao.delete(book);
    }

    public boolean addAuthor(String name, String surname) {
        return authorDao.add(new Author(name, surname));
    }

    public boolean deleteAuthor(Author author) {
        return authorDao.delete(author);
    }


    public List<Author> getAllAuthors() {
        return authorDao.authorsList();
    }

    public boolean takeBook(Book book, Client client) {
        return bookDao.takeBook(book, client);
    }

    public boolean returnBook(Book book, Client client) {
        return bookDao.returnBook(book, client);
    }

    public boolean registration(Client client) {
        return clientDaoImpl.add(client);
    }

    public Client signIn(String loginMail, String password) {
        return clientDaoImpl.signIn(loginMail, password);
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


