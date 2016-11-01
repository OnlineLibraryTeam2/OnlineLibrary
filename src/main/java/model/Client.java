package model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexp on 16.1.11.
 */
@Entity
@Table(name = "clients")
public class Client extends Person {

    @Column
    private int age;

    @Column
    private String phoneNumber;

    @Column
    private String loginMail;

    @Column
    private String password;

    @Column
    private boolean blackList;

   @ManyToMany
    @JoinTable(name = "client_books",
            joinColumns =
                    {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> history;

    @ManyToMany
    @JoinTable(name = "taken_books",
            joinColumns =
                    {@JoinColumn(name = "client_id", referencedColumnName = "id")},
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private List<Book> takenBooks;


    public Client() {
        history = new ArrayList<>();
        takenBooks = new ArrayList<>();

    }

    public Client(String name, String surname, int age, String phoneNumber,
                  String loginMail, String password) {

        super(name, surname);
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.loginMail = loginMail;
        this.password = password;
        blackList = false;

        history = new ArrayList<>();
        takenBooks = new ArrayList<>();

    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLoginMail() {
        return loginMail;
    }

    public void setLoginMail(String loginMail) {
        this.loginMail = loginMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlackList() {
        return blackList;
    }

    public void setBlackList(boolean blackList) {
        this.blackList = blackList;
    }

    public List<Book> getHistory() {
        return history;
    }

    public void setHistory(List<Book> history) {
        this.history = history;
    }

    public List<Book> getTakenBooks() {
        return takenBooks;
    }

    public void setTakenBooks(List<Book> takenBooks) {
        this.takenBooks = takenBooks;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Client client = (Client) o;

        if (age != client.age) return false;
        if (blackList != client.blackList) return false;
        if (!phoneNumber.equals(client.phoneNumber)) return false;
        if (!loginMail.equals(client.loginMail)) return false;
        return password.equals(client.password);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + age;
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + loginMail.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (blackList ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Client " + super.toString() +
                "age=" + age +
                ", phoneNumber= " + phoneNumber +
                ", loginMail= " + loginMail +
                ", password= " + password +
                ", blackList= " + blackList +
                ", history= " + history +
                ", takenBooks= " + takenBooks;
    }
}
