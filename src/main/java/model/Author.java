package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexp on 16.1.11.
 */
@Entity
@Table(name = "authors")
public class Author extends Person{

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList;

    public Author() {
        bookList = new ArrayList<>();
    }

    public Author(String name, String surname) {
        super(name, surname);
        this.bookList = new ArrayList<>();
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString() {
        return "Author{" + super.toString() +
                "bookList=" + bookList +
                '}';
    }
}
