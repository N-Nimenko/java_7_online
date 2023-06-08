package HomeWork.homework2.entity;

public class Book extends EntityID {
    private String bookTitle;
    private String bookAuthor;
    private int price;

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price <= 0) {
            System.out.println("Something gone wrong, try again.");
        } else {
            this.price = price;
        }
    }
}


