package library.entity;

import java.util.Objects;

public class BookEntity extends BaseEntity {
    private String title;
    private String amountOfPages;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAmountOfPages() {
        return amountOfPages;
    }

    public void setAmountOfPages(String amountOfPages) {
        this.amountOfPages = amountOfPages;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id='" + (getId() != null ? getId() : "null") + '\'' +
                ", title='" + title + '\'' +
                ", amountOfPages='" + amountOfPages + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookEntity)) return false;
        BookEntity book = (BookEntity) o;
        return Objects.equals(getId(), book.getId()) &&
                Objects.equals(title, book.title) &&
                Objects.equals(amountOfPages, book.amountOfPages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), title, amountOfPages);
    }
}