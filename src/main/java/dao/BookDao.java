package dao;


import model.Book;

import java.util.List;

/**
 * Created by student on 16.1.11.
 */
public class BookDao{

    public boolean takeBook(Book book){
        return true;
    }

    public boolean returnBook(Book book) {
        return false;
    }

    public int reservationBook(Book book) {
        return 0;
    }

    public List<Book> searchBookTitle(String title) {
        return null;
    }

    public List<Book> searchByYear (int year){
        return null;
    }


    public List<Book> recommendedBooks (String genreBook) {
        return null;
    }

    public List<Book> showAllBooks() {
        return null;
    }

    public boolean editBookCount (int bookNumber) {
        return false;
    }

    public boolean deleteBookNumber (int bookNumber) {
        return false;
    }

}
