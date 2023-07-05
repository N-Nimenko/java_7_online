package library.entity;

import java.util.ArrayList;
import java.util.List;

public class AuthorEntity extends BaseEntity {
    private String firstName;
    private String lastName;
    private List<BookEntity> books;

    public AuthorEntity() {
        this.books = new ArrayList<>();
    }

    public List<BookEntity> getBooks() {
        return books;
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

    @Override
    public String toString() {
        return "AuthorEntity{" +
                "id='" + getId() + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
