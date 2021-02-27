package onlinestore.entity;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class User extends BaseEntity {

    private String username;
    private String email;
    private String password;
    private String city;
    private String address;
    private LocalDate accountCreationDate;
    private String accountStatus;
    private String type;

    public User(String username, String email, String password, String city, String address, LocalDate accountCreationDate, String accountStatus, String type) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.city = city;
        this.address = address;
        this.accountCreationDate = accountCreationDate;
        this.accountStatus = accountStatus;
        this.type = type;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String accountName) {
        this.username = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
