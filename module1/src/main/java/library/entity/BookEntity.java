package library.entity;

public class BookEntity extends BaseEntity {
    private String title;
    private String amountOfPages;
    private AuthorEntity author;

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

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "id='" + (getId() != null ? getId() : "null") + '\'' +
                ", title='" + title + '\'' +
                ", author=" + (author != null ? (author.getId() != null ? author.getId() : "null") : "null") +
                '}';
    }
}