package library.dao;

import library.db.AuthorBookStorage;
import library.entity.AuthorBook;

public class AuthorBookDao {
    private final AuthorBookStorage authorBookStorage = AuthorBookStorage.getInstance();

    public void create(AuthorBook authorBook) {
        authorBookStorage.create(authorBook);
    }

    public void deleteAuthorById(String authorId) {
        authorBookStorage.deleteAuthorById(authorId);
    }

    public void deleteBookById(String bookId) {
        authorBookStorage.deleteBookById(bookId);
    }

    public void deleteAuthorFromBook(String authorId, String bookId) {
        authorBookStorage.deleteAuthorFromBookId(authorId, bookId);
    }

    public AuthorBook[] getAllAuthorsFromBooks(String bookId) {
        return authorBookStorage.getAllAuthorsFromBooks(bookId);
    }

    public AuthorBook[] getAllBooksFromAuthors(String authorId) {
        return authorBookStorage.getAllBooksFromAuthors(authorId);
    }
}
