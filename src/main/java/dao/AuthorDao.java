package dao;


import model.Author;
import model.Book;

import java.util.List;

/**
 * Created by student on 11/1/16.
 */
public class AuthorDao implements IDao {

    @Override
    public boolean add(Object obj) {
        return false;
    }

    @Override
    public boolean update(Object obj) {
        return false;
    }

    @Override
    public boolean delete(Object obj) {
        return false;
    }

    public List<Book> searchByAuthor(Author author) {

        return null;
    }
}
