package dao;


import model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by student on 16.1.11.
 */


public class BookDao implements IDao {

    private EntityManagerFactory factory;

    public BookDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public boolean takeBook(Book book) {
        return true;
    }

    public boolean returnBook(Book book) {
        return false;
    }

    public int reservationBook(Book book) {
        return 0;
    }

    public List<Book> searchBookTitle(String title) {
        return null;
    }

    public List<Book> searchByYear(int year) {
        return null;
    }


    public List<Book> recommendedBooks(String genreBook) {
        return null;
    }

    public List<Book> showAllBooks() {

        EntityManager manager = factory.createEntityManager();
        try {
            TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b", Book.class);

            return query.getResultList();

        } finally {
            manager.close();
        }

    }

    public boolean editBookCount(int bookNumber) {
        return false;
    }

    public boolean deleteBookNumber(int bookNumber) {
        return false;
    }

    @Override
    public boolean add(Object obj) {


        return false;
    }

    @Override
    public boolean update(Object obj) {
 //       if(book != null) {

//        }

        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }
}
