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
            TypedQuery<Author> typedQuery = entityManager.createQuery(
                    "SELECT author FROM Author author", Author.class);

            return typedQuery.getResultList();
        }
        finally {
            entityManager.close();
        }
    }

    public List<Book> searchByAuthor(Author author) {
        EntityManager entityManager = factory.createEntityManager();

        try {
            author = entityManager.find(Author.class, author.getId());
            return author.getBookList();
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

    /*public boolean addAuthorBook(Book book) {
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        Author author = manager.find(Author.class, book.getAuthor().getId());

        try {
            transaction.begin();
            List<Book> books = author.getBookList();
            books.add(book);
            author.setBookList(books);
            manager.merge(author);
            transaction.commit();

        }catch (Exception e) {
            transaction.rollback();

            return false;
        }
        finally {
            manager.close();
        }

        return false;
    }*/
}
