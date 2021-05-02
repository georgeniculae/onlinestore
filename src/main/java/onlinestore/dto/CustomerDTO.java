package onlinestore.dto;

import java.time.LocalDate;

public class CustomerDTO extends UserDTO {

    private String firstName;
    private String lastName;

    public CustomerDTO() {
    }

    public CustomerDTO(String email, String accountName, String password, String confirmPassword, String city, String address, LocalDate accountCreationDate, String accountStatus, String type, String firstName, String lastName) {
        super(email, accountName, password, confirmPassword, city, address, accountCreationDate, accountStatus, type);
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

