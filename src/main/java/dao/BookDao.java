package dao;


import model.Book;
import model.Client;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by student on 16.1.11.
 * Amended 16.2.11, DP.
 */

@Component
public class BookDao implements IDao<Book> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean add(Book book) {
        sessionFactory.getCurrentSession().save(book);

        return true;
    }

    @Override
    public boolean delete(Book book) {
        sessionFactory.getCurrentSession().delete(book);

        return true;
    }

    public boolean takeBook(Book book, Client client) {
        Book clientBook = book;
        clientBook.setBookCount(1);
        book.setBookCount(book.getBookCount() -1);
        List<Book> takenBook = new ArrayList<>();
        takenBook.add(clientBook);
        client.setTakenBooks(takenBook);
        sessionFactory.getCurrentSession().update(book);
        sessionFactory.getCurrentSession().update(client);

        return true;
    }

    public boolean returnBook(Book book, Client client) {
        client.getTakenBooks().remove(book);
        client.setTakenBooks(client.getTakenBooks());
        book.setBookCount(book.getBookCount() + 1);
        sessionFactory.getCurrentSession().update(book);
        sessionFactory.getCurrentSession().update(client);

        return true;
    }

    public List<Book> searchBookTitle(String title) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("title", title));

        return (List<Book>)criteria.list();
    }


    public List<Book> searchByYear(int year) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("year", year));

        return (List<Book>)criteria.list();
    }


    public List<Book> recommendedBooks(String genreBook) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);
        criteria.add(Restrictions.eq("genre", genreBook));

        return (List<Book>)criteria.list();
    }


    public List<Book> showAllBooks() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Book.class);

        return (List<Book>)criteria.list();
    }

}