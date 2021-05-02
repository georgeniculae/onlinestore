package onlinestore.entity;

import javax.persistence.Entity;

@Entity
public class Customer extends User {

    private String firstName;
    private String lastName;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer() {
    }

    public Customer(String username, String email, String password, String address, String accountStatus, String type, String firstName, String lastName) {
        super(username, email, password, address, accountStatus, type);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
