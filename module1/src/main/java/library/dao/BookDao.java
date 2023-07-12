package library.dao;

import library.db.BookStorage;
import library.entity.Book;

public class BookDao {
    private static final BookStorage bookStorage = BookStorage.getInstance();

    public void create(Book book) {
        bookStorage.create(book);
    }

    public void update(Book book) {
        bookStorage.update(book);
    }

    public static void delete(String id) {
        bookStorage.delete(id);
    }

    public Book findOne(String id) {
        return bookStorage.findOne(id);
    }

    public Book[] findAll() {
        return bookStorage.findAll();
    }
}
