package dao;


import dao.interfaces.AuthorDao;
import model.Author;
import model.Book;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by student on 11/1/16.
 * Author DAO implementation.
 */

@Component("authorDao")
@Transactional
public class AuthorDaoImpl implements AuthorDao {


    @PersistenceContext
    private EntityManager manager;

    public AuthorDaoImpl() {

    }

    @Override
    public boolean add(Author author) {
        manager.persist(author);
        return true;
    }

    @Override
    public boolean update(Author author) {
        manager.merge(author);
        return true;
    }

    @Override
    public boolean delete(Author author) {
        author = manager.find(Author.class, author.getId());
        manager.remove(author);
        return true;
    }

    @Override
    public Author findAuthor(Author author){

        TypedQuery<Author> query = manager.createQuery(
                "Select a FROM Author a WHERE a.name =:name AND a.surname =:surname", Author.class);

        query.setParameter("name", author.getName());
        query.setParameter("surname", author.getSurname());

        return query.getSingleResult();
    }

    @Override
    public List<Author> authorsList() {

        TypedQuery<Author> query =
                manager.createNamedQuery("SELECT a FROM author a", Author.class);
        return query.getResultList();
    }

    @Override
    public List<Book> searchByAuthor(Author author) {
        author = manager.find(Author.class, author.getId());
        return author.getBookList();
    }

}
