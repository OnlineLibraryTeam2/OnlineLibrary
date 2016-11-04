package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexp on 16.1.11.
 */
@Entity
@Table(name = "authors")
public class Author extends Person{

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
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
                '}';
    }
}
