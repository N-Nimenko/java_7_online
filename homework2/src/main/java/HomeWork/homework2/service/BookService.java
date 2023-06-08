package HomeWork.homework2.service;

import HomeWork.homework2.entity.Book;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class BookService {

    public static ArrayList<Book> books = new ArrayList<>();

    /*CREATE()*/
    public static void create(Book book) {
        String id = UUID.randomUUID().toString();
        book.setId(id);
        books.add(book);
    }

    /*FIND()*/
    public static Book findById(String id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public static Book findByTitle(String title) {
        for (Book book : books) {
            if (book.getBookTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public static Book findByAuthor(String author) {
        for (Book book : books) {
            if (book.getBookAuthor().equals(author)) {
                return book;
            }
        }
        return null;
    }

    /*UPDATE*/

    public static void update(Book book, String searchParameter) {
        switch (searchParameter) {
            case "1":
                break;
            case "2":
                for (int i = 0; i < books.size(); i++) {
                    Book currentBook = books.get(i);
                    if (currentBook.getBookTitle().equals(book.getBookTitle())) {
                        books.set(i, book);
                        break;
                    }
                }
                break;
            case "3":
                for (int i = 0; i < books.size(); i++) {
                    Book currentBook = books.get(i);
                    if (currentBook.getBookAuthor().equals(book.getBookAuthor())) {
                        books.set(i, book);
                        break;
                    }
                }
                break;
            default:
                System.out.println("Invalid search parameter");
                break;
        }
    }

    /*DELETE()*/
    public static boolean delete(String id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId().equals(id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public static boolean deleteByTitle(String title) {
        Iterator<Book> iterator = books.iterator();
        boolean isDeleted = false;
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookTitle().equals(title)) {
                iterator.remove();
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    public static boolean deleteByAuthor(String author) {
        Iterator<Book> iterator = books.iterator();
        boolean isDeleted = false;
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getBookAuthor().equals(author)) {
                iterator.remove();
                isDeleted = true;
            }
        }
        return isDeleted;
    }

    /*FINDALL()*/
    public static ArrayList<Book> findAll() {
        return books;
    }
}


