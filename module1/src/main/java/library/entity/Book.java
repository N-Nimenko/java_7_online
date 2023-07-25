package library.entity;

public class Book extends BaseEntity {
    private String name;
    private int amountOfPages;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmountOfPages() {
        return amountOfPages;
    }

    public void setAmountOfPages(int amountOfPages) {
        this.amountOfPages = amountOfPages;
    }
}
