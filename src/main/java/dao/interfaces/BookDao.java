package dao.interfaces;

import model.Book;
import model.Client;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexp on 16.9.11.
 */
public interface BookDao extends IDao<Book> {

    boolean takeBook(Book book, Client client);

    boolean returnBook(Book book, Client client);

    List<Book> searchBookTitle(String title);

    List<Book> searchByYear(int year);

    List<Book> recommendedBooks(String genreBook);

    List<Book> showAllBooks();
}
