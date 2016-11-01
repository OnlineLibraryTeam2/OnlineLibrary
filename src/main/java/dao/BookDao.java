package dao;


import model.Book;
import model.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;
import java.util.List;

/**
 * Created by student on 16.1.11.
 */


public class BookDao implements IDao<Book> {

    private EntityManagerFactory factory;
    private Client client;



    public BookDao(EntityManagerFactory factory, Client client) {
        this.factory = factory;
        this.client = client;

    }

    public boolean takeBook(Book book) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        book = manager.find(Book.class, book.getId());
        if (book.getBookCount() !=0) {

            client = manager.find(Client.class, client.getId());
            List<Book> clientBooks = client.getTakenBooks();
            clientBooks.add(book);
            try {
                transaction.begin();
                book.setBookCount(book.getBookCount()-1);
                client.setTakenBooks(clientBooks);
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

    public boolean returnBook(Book book) {

        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        book = manager.find(Book.class, book.getId());
        client = manager.find(Client.class, client.getId());
        List<Book> clientBooks = client.getHistory();
        clientBooks.add(book);
        try {
            transaction.begin();
            book.setBookCount(book.getBookCount()+1);
            client.setHistory(clientBooks);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            return false;
        } finally {
            manager.close();
        }



    }

    public List<Book> searchBookTitle(String title) {
        if (!title.equals("")) {
            EntityManager manager = factory.createEntityManager();

            try {
                TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b WHERE b.title = title" , Book.class );
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
                TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b WHERE b.year = year" , Book.class );
                return query.getResultList();
            } finally {
                manager.close();
            }
        }
        return null;
    }



    public List<Book> recommendedBooks(String genreBook) {
        if (!genreBook.equals("")) {
            EntityManager manager = factory.createEntityManager();

            try {
                TypedQuery<Book> query = manager.createQuery("SELECT b FROM Book b WHERE b.genre = genreBook" , Book.class );
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
        if (book != null) {
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
        return false;
    }
}



