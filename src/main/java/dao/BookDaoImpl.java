package dao;

import dao.interfaces.BookDao;
import model.Book;
import model.Client;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 16.1.11.
 * Amended 16.2.11, DP.
 */

@Component("bookDao")
@Transactional
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean add(Book book) {
        manager.persist(book);

        return true;
    }

    @Override
    public boolean update(Book book) {
        manager.merge(book);

        return true;
    }

    @Override
    public boolean delete(Book book) {
        book = manager.find(Book.class, book.getId());
        manager.remove(book);

        return true;
    }

    @Override
    public boolean takeBook(Book book, Client client) {
        book = manager.find(Book.class, book.getId());
        Book clientBook = new Book(book.getTitle(), book.getYear(), book.getGenre(), book.getAuthor(), 1);
        clientBook.setId(book.getId());
        book.setBookCount(book.getBookCount() - 1);
        client = manager.find(Client.class, client.getId());
        List<Book> takenBook = client.getTakenBooks();
        takenBook.add(clientBook);
        client.setTakenBooks(takenBook);
        manager.merge(book);
        manager.merge(client);

        return true;
    }

    @Override
    public boolean returnBook(Book book, Client client) {
        client = manager.find(Client.class, client.getId());
        book = manager.find(Book.class, book.getId());
        client.getTakenBooks().remove(book);
        client.setTakenBooks(client.getTakenBooks());
        book.setBookCount(book.getBookCount() + 1);
        manager.merge(book);
        manager.merge(client);

        return true;
    }

    @Override
    public Book findBook(Book book) {
        TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b WHERE b.title=:title AND b.author=:author", Book.class);
        query.setParameter("title", book.getTitle());
        query.setParameter("author", book.getAuthor());

        return query.getSingleResult();
    }

    @Override
    public List<Book> searchBookTitle(String title) {
        TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b WHERE b.title=:title", Book.class);
        query.setParameter("title", title);

        return query.getResultList();
    }

    @Override
    public List<Book> searchByYear(int year) {
        TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b WHERE b.year=:year", Book.class);
        query.setParameter("year", year);

        return query.getResultList();
    }

    @Override
    public List<Book> recommendedBooks(String genreBook) {
        TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b WHERE b.genre=:genreBook", Book.class);
        query.setParameter("genreBook", genreBook);

        return query.getResultList();
    }

    @Override
    public List<Book> showAllBooks() {
        TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b", Book.class);

        return query.getResultList();
    }

}