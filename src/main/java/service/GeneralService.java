package service;

import dao.interfaces.AuthorDao;
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
    private ClientDao clientDao;


    public boolean addClient(Client client) {
        return clientDao.add(client);
    }

    public boolean deleteClient(Client client) {
        return clientDao.delete(client);
    }

    public boolean updateCLient(Client client) { return clientDao.update(client); }

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

    public boolean addAuthor(Author author) {
        return authorDao.add(author);
    }

    public boolean updateAuthor(Author author)  {
        return authorDao.update(author);
    }

    public boolean deleteAuthor(Author author) {
        return authorDao.delete(author);
    }

    public Author findAuthor(Author author){
        return authorDao.findAuthor(author);
    }


    public List<Author> getAllAuthors() {
        return authorDao.authorsList();
    }

    public boolean addBook(Book book) {

        return bookDao.add(book);
    }

    public boolean updateBook(Book book){

        return bookDao.update(book);
    }

    public boolean deleteBook(Book book) {

        return bookDao.delete(book);
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

    public Book findBook(Book book){
        return bookDao.findBook(book);
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


