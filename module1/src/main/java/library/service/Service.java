package library.service;

import library.dao.Dao;
import library.entity.AuthorEntity;
import library.entity.BookEntity;

import java.util.List;

public class Service {
    private static final Dao dao = new Dao();

    public static void createBook(BookEntity book) {
        dao.createBook(book);
    }

    public static void createAuthor(AuthorEntity author) {
        dao.createAuthor(author);
    }

    public static boolean addBookToAuthor(String authorId, String bookId) {
        return dao.addBookToAuthor(authorId, bookId);
    }

    public static void updateBook(BookEntity book) {
        dao.updateBook(book);
    }

    public static void updateAuthor(AuthorEntity author) {
        dao.updateAuthor(author);
    }

    public static boolean deleteBook(String id) {
        return dao.deleteBook(id);
    }

    public static boolean deleteAuthor(String id) {
        return dao.deleteAuthor(id);
    }

    public static BookEntity findBookById(String id) {
        return dao.findBookById(id);
    }

    public static AuthorEntity findAuthorById(String id) {
        return dao.findAuthorById(id);
    }

    public static List<BookEntity> getAllBooks() {
        return List.of(dao.getAllBooks());
    }

    public static List<AuthorEntity> getAllAuthors() {
        return List.of(dao.getAllAuthors());
    }
}