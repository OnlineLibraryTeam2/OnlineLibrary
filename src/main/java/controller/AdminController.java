package controller;

import dao.AuthorDao;
import dao.BookDao;
import dao.ClientDao;
import model.Author;
import model.Book;
import model.Client;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by student on 16.1.11.
 */
public class AdminController {

    private AuthorDao authorDao;
    private BookDao bookDao;
    private ClientDao clientDao;
    private EntityManagerFactory factory;

    public AdminController(EntityManagerFactory factory) {
        this.factory = factory;
        this.authorDao = new AuthorDao(factory);
        this.bookDao = new BookDao(factory, new Client());
        this.clientDao = new ClientDao(factory);
    }

    public boolean updateClientInfo(Client client) {

        return clientDao.update(client);
    }

    public boolean deleteClient(Client client) {
        return clientDao.delete(client);
    }

    public Client findClientByMail(String mailClient) {
        return clientDao.findClientByMail(mailClient);
    }

    public List<Client> showClients() {
        return clientDao.showAllClients();
    }

    public List<Client> showBlackList() {
        return clientDao.showBlacklist();
    }

    public boolean addBlackList(Client client) {
        return clientDao.addBlacklist(client);
    }

    public boolean deleteBlackList(Client client) {
        return clientDao.deleteFromBlacklist(client);
    }

    public boolean addBook(String title, int year, String genre, Author author,  int bookCount) {
        Book book = new Book(title, year, genre, author, bookCount);

        return bookDao.add(book);
    }

    public boolean deleteBook(Book book) {
        return bookDao.delete(book);
    }

    public boolean updateBookInfo(Book book) {
        return bookDao.update(book);
    }

    public boolean addAuthor(String name, String surname) {
        return authorDao.add(new Author(name, surname));
    }

    public boolean deleteAuthor(Author author) {
        return authorDao.delete(author);
    }

    public boolean updateAuthorInfo(Author author) {
        return authorDao.update(author);
    }

    public List<Author> getAllAuthors(){
        return authorDao.authorsList();
    }
}
