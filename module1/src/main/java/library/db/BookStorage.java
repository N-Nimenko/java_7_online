package library.db;

import library.entity.Book;
import library.util.AppUtil;

public class BookStorage {
    private static BookStorage instance;
    private Book[] books = new Book[10];
    private int size = 0;

    private BookStorage() {
    }

    public static BookStorage getInstance() {
        if (instance == null) {
            instance = new BookStorage();
        }
        return instance;
    }

    public void create(Book book) {
        if (size == books.length) {
            Book[] updatedArray = new Book[size * 2];
            System.arraycopy(books, 0, updatedArray, 0, size);
            books = updatedArray;
        }
        String id = AppUtil.getUUID();
        book.setId(id);
        books[size] = book;
        size++;
    }

    public void update(Book book) {
        for (int i = 0; i < size; i++) {
            if (books[i].getId().equals(book.getId())) {
                books[i] = book;
                break;
            }
        }
    }

    public void delete(String id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (books[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                books[i] = books[i + 1];
            }
            books[size - 1] = null;
            size--;
        }
    }

    public Book findOne(String id) {
        for (int i = 0; i < size; i++) {
            if (books[i].getId().equals(id)) {
                return books[i];
            }
        }
        return null;
    }

    public Book[] findAll() {
        Book[] allBooks = new Book[size];
        System.arraycopy(books, 0, allBooks, 0, size);
        return allBooks;
    }
}
