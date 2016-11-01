package dao;


import model.Author;
import model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by student on 11/1/16.
 * Author DAO implementation.
 */
public class AuthorDao implements IDao<Author> {

    private EntityManagerFactory factory;

    public AuthorDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public List<Author> authorsList() {
        EntityManager entityManager = factory.createEntityManager();

        try {
            TypedQuery<Author> typedQuery = entityManager.createQuery("SELECT author FROM Author author", Author.class);

            return typedQuery.getResultList();
        }
        finally {
            entityManager.close();
        }
    }

    public List<Book> searchByAuthor(Author author) {
        EntityManager entityManager = factory.createEntityManager();

        try {
            TypedQuery<Book> typedQuery = entityManager.createQuery("SELECT book FROM Book book WHERE book.author = author", Book.class);
            return typedQuery.getResultList();
        }
        finally {
            entityManager.close();
        }
    }

    @Override
    public boolean add(Author author) {
        if(author != null) {
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            try {
                entityTransaction.begin();
                entityManager.persist(author);
                entityTransaction.commit();

                return true;
            } catch (Exception e) {
                entityTransaction.rollback();

                return false;
            }
            finally {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public boolean update(Author author) {
        if(author != null) {
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            Author lookupAuthor = entityManager.find(Author.class, author.getId());

            try {
                entityTransaction.begin();
                lookupAuthor.setName(author.getName());
                lookupAuthor.setSurname(author.getSurname());
                lookupAuthor.setBookList(author.getBookList());
                entityManager.merge(lookupAuthor);
                entityTransaction.commit();

                return true;
            } catch (Exception e) {
                entityTransaction.rollback();

                return false;
            }
            finally {
                entityManager.close();
            }
        }
        return false;
    }

    @Override
    public boolean delete(Author author) {
        if(author != null) {
            EntityManager entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();

            author = entityManager.find(Author.class, author.getId());

            try {
                entityTransaction.begin();
                entityManager.remove(author);
                entityTransaction.commit();

                return true;
            } catch (Exception e) {
                entityTransaction.rollback();

                return false;
            }
            finally {
                entityManager.close();
            }
        }
        return false;
    }

}
