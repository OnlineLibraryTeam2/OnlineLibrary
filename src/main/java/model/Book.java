package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String title;

    @Column
    private int year;

    @Column
    private String genre;

    @Column
    private int bookCount;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToMany
    @JoinTable(name = "client_history",
            joinColumns={@JoinColumn(name="book_id", referencedColumnName="id")},
            inverseJoinColumns=@JoinColumn(name="client_id", referencedColumnName="id"))
    private List<Client> history;

    @ManyToMany
    @JoinTable(
            name="taken_books",
            joinColumns={@JoinColumn(name="book_id", referencedColumnName="id")},
            inverseJoinColumns=@JoinColumn(name="client_id", referencedColumnName="id")

           )
    private List<Client> clients;

    public Book() {
    }

    public Book(String title, int year, String genre, Author author, int bookCount) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.author = author;
        this.bookCount = bookCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setBookCount(int bookCount) {
        this.bookCount = bookCount;
    }

    public int getBookCount() {
        return bookCount;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Client> getHistory() {
        return history;
    }

    public void setHistory(List<Client> history) {
        this.history = history;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (year != book.year) return false;
        if (!title.equals(book.title)) return false;
        return genre.equals(book.genre);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + title.hashCode();
        result = 31 * result + year;
        result = 31 * result + genre.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book: " +
                "id= " + id +
                ", title= " + title +
                ", year= " + year +
                ", genre= " + genre +
                ", author= " + author +
                ", bookCount= " + bookCount;
    }

}
