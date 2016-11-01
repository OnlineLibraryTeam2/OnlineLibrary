package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by alexp on 16.1.11.
 */
@Entity
@Table(name = "admins")
public class Admin extends Person {

    @Column
    private String loginMail;

    @Column
    private String phoneNumber;

    @Column
    private String password;

    public Admin() {
    }

    public Admin(String name, String surname, String loginMail, String phoneNumber, String password) {
        super(name, surname);
        this.loginMail = loginMail;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getLoginMail() {
        return loginMail;
    }

    public void setLoginMail(String loginMail) {
        this.loginMail = loginMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Admin admin = (Admin) o;

        if (!loginMail.equals(admin.loginMail)) return false;
        if (!phoneNumber.equals(admin.phoneNumber)) return false;
        return password.equals(admin.password);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + loginMail.hashCode();
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Admin " + super.toString() +
                "loginMail= " + loginMail +
                ", phoneNumber= " + phoneNumber +
                ", password= " + password;
    }
}
