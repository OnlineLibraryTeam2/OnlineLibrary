package dao.interfaces;

import model.Author;
import model.Book;


import java.util.List;

/**
 * Created by alexp on 16.9.11.
 */
public interface AuthorDao extends IDao<Author> {

    Author findAuthor(Author author);
    List<Author> authorsList();
    List<Book> searchByAuthor(Author author);
}
