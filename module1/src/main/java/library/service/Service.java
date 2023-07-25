package library.service;

import library.entity.Author;
import library.dao.*;
import library.entity.AuthorBook;
import library.entity.Book;

public class Service {

    private static final AuthorDao authorDao = new AuthorDao();
    private static final BookDao bookDao = new BookDao();
    private static final AuthorBookDao authorBookDao = new AuthorBookDao();


    public static void createAuthor(Author author) {
        authorDao.create(author);
    }

    public static void createBook(Book book) {
        bookDao.create(book);
    }

    public static void updateAuthor(Author author) {
        authorDao.update(author);
    }

    public static void updateBook(Book book) {
        bookDao.update(book);
    }

    public static Author findAuthorById(String id) {
        return authorDao.findOne(id);
    }

    public static Book findBookById(String id) {
        return bookDao.findOne(id);
    }

    public static Book[] findAllBooks() {
        return bookDao.findAll();
    }

    public static Author[] findAllAuthors() {
        return authorDao.findAll();
    }

    public static void deleteAuthorById(String id) {
        authorDao.delete(id);
        authorBookDao.deleteAuthorById(id);
    }

    public static void deleteBookById(String id) {
        BookDao.delete(id);
        authorBookDao.deleteBookById(id);
    }

    public static Author[] findAllAuthorsByBooksId(String id) {
        var authorBooks = authorBookDao.getAllAuthorsFromBooks(id);
        Author[] result = new Author[authorBooks.length];
        for (int i = 0; i < result.length; i++) {
            Author found = authorDao.findOne(authorBooks[i].getAuthorId());
            result[i] = found;
        }
        return result;
    }

    public static Book[] findAllBooksByAuthorsId(String id) {
        var authorBooks = authorBookDao.getAllBooksFromAuthors(id);
        Book[] result = new Book[authorBooks.length];
        for (int i = 0; i < result.length; i++) {
            Book found = bookDao.findOne(authorBooks[i].getBookId());
            result[i] = found;
        }
        return result;
    }

    public static void addAuthorToBook(String authorId, String bookId) {
        AuthorBook authorBook = new AuthorBook();
        authorBook.setAuthorId(authorId);
        authorBook.setBookId(bookId);
        authorBookDao.create(authorBook);
    }

    public static void deleteAuthorFromBook(String authorId, String bookId) {
        authorBookDao.deleteAuthorFromBook(authorId, bookId);
    }
}