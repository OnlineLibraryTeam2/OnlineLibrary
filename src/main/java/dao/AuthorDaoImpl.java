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
import java.util.List;

/**
 * Created by student on 11/1/16.
 * Author DAO implementation.
 */

@Component("authorDao")
@Transactional
public class AuthorDaoImpl implements AuthorDao {


    private SessionFactory sessionFactory;
    @PersistenceContext
    private EntityManager manager;

    public AuthorDaoImpl() {

    }

    @Override
    public boolean add(Author author) {
        manager.persist(author);
        //sessionFactory.getCurrentSession().save(author);
        return true;
    }

    @Override
    public boolean update(Author author) {
        sessionFactory.getCurrentSession().update(author);
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
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Author.class);
        criteria.add(Restrictions.eq("name", author.getName()));
        criteria.add(Restrictions.eq("surname", author.getSurname()));


        return (Author) criteria.uniqueResult();
    }

    @Override
    public List<Author> authorsList() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Author.class);
        return criteria.list();
    }

    @Override
    public List<Book> searchByAuthor(Author author) {
        List<Author> authors = authorsList();

        for (Author current : authors) {
            if (author.equals(current)) {
                return current.getBookList();
            }
        }

        return null;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
