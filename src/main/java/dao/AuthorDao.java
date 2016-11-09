package dao;


import model.Author;
import model.Book;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by student on 11/1/16.
 * Author DAO implementation.
 */
@Component
public class AuthorDao implements IDao<Author> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean add(Author author) {
        sessionFactory.getCurrentSession().save(author);
        return true;
    }

    @Override
    @Transactional
    public boolean delete(Author author) {
        sessionFactory.getCurrentSession().delete(author);
        return true;
    }

    @Transactional
    public Author findAuthor(Author author){
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Author.class);
        criteria.add(Restrictions.eq("name", author.getName()));
        criteria.add(Restrictions.eq("surname", author.getSurname()));


        return (Author) criteria.uniqueResult();
    }


    @Transactional
    public List<Author> authorsList() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Author.class);
        return criteria.list();
    }

    @Transactional
    public List<Book> searchByAuthor(Author author) {
        List<Author> authors = authorsList();

        for (Author current: authors) {
            if(author.equals(current)){
                return current.getBookList();
            }
        }

        return null;
    }

}
