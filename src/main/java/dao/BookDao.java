package dao;


import model.Book;
import model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by student on 16.1.11.
 * Amended 16.2.11, DP.
 */


public class BookDao implements IDao<Book> {

    private EntityManagerFactory factory;

    public BookDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public boolean takeBook(Book book, Client client) {
        if(book != null) {

            EntityManager bookManager = factory.createEntityManager();
            EntityTransaction bookTransaction = bookManager.getTransaction();

            book = bookManager.find(Book.class, book.getId());

            if (book.getBookCount() > 0) {
                    client = bookManager.find(Client.class, client.getId());
                try {
                    bookTransaction.begin();

                    List<Book> clientBooks = client.getTakenBooks();
                    book.setBookCount(book.getBookCount() - 1);
                    Book takenBook = new Book(book.getTitle(), book.getYear(), book.getGenre(), book.getAuthor(), 1);
                    takenBook.setId(book.getId());
                    clientBooks.add(takenBook);
                    client.setTakenBooks(clientBooks);

                    bookManager.merge(book);

                    bookTransaction.commit();
                    return true;
                } catch (Exception e) {
                    bookTransaction.rollback();
                    return false;
                } finally {
                    bookManager.close();
                }
            }
            return false;
        }
        return false;

    }

    public boolean returnBook(Book book, Client client) {

        EntityManager bookManager = factory.createEntityManager();
        EntityTransaction transaction = bookManager.getTransaction();

        book = bookManager.find(Book.class, book.getId());
        client = bookManager.find(Client.class, client.getId());

        List<Book> takenBooks = client.getTakenBooks();
        if (takenBooks.contains(book)) {

            takenBooks.remove(book);
            List<Book> clientBooks = client.getHistory();
            clientBooks.add(book);

            try {
                transaction.begin();
                book.setBookCount(book.getBookCount() + 1);
                client.setHistory(clientBooks);
                bookManager.merge(book);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            } finally {
                bookManager.close();
            }

        }

        return false;
    }

    public List<Book> searchBookTitle(String title) {
        if (title != null && !title.equals("")) {

            EntityManager manager = factory.createEntityManager();

            try {
                TypedQuery<Book> query = manager.createQuery(
                        "SELECT b FROM Book b WHERE b.title =:title", Book.class);

                query.setParameter("title", title);
                return query.getResultList();
            } finally {
                manager.close();
            }
        }

        return null;
    }


    public List<Book> searchByYear(int year) {
        if (year != 0) {

            EntityManager manager = factory.createEntityManager();

            try {
                TypedQuery<Book> query = manager.createQuery(
                        "SELECT b FROM Book b WHERE b.year =:year", Book.class);

                query.setParameter("year", year);
                return query.getResultList();
            } finally {
                manager.close();
            }
        }

        return null;
    }


    public List<Book> recommendedBooks(String genreBook) {
        if (genreBook != null && !genreBook.equals("")) {
            EntityManager manager = factory.createEntityManager();

            try {
                TypedQuery<Book> query = manager.createQuery(
                        "SELECT b FROM Book b WHERE b.genre =:genreBook", Book.class);

                query.setParameter("genreBook", genreBook);

                return query.getResultList();
            } finally {
                manager.close();
            }
        }

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


    @Override
    public boolean add(Book book) {

        if (book != null) {
            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();

            try {
                transaction.begin();
                manager.persist(book);
                transaction.commit();
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            } finally {
                manager.close();
            }

        }

        return false;
    }

    @Override
    public boolean update(Book book) {
        if (book != null) {

            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            Book originBook = manager.find(Book.class, book.getId());

            try {
                transaction.begin();

                originBook.setTitle(book.getTitle());
                originBook.setAuthor(book.getAuthor());
                originBook.setGenre(book.getGenre());
                originBook.setYear(book.getYear());

                manager.merge(originBook);
                transaction.commit();

                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            } finally {
                manager.close();

            }
        }

        return false;
    }

    @Override
    public boolean delete(Book book) {

            EntityManager manager = factory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            book = manager.find(Book.class, book.getId());

            try {

                transaction.begin();
                manager.remove(book);
                transaction.commit();

                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            } finally {
                manager.close();
            }

    }
}