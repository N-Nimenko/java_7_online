package library.dao;

import library.entity.AuthorEntity;
import library.entity.BookEntity;

import java.util.ArrayList;
import java.util.List;

public class Dao {
    private List<BookEntity> books;
    private List<AuthorEntity> authors;

    public Dao() {
        books = new ArrayList<>();
        authors = new ArrayList<>();
    }

    public void createBook(BookEntity book) {
        books.add(book);
    }

    public void createAuthor(AuthorEntity author) {
        authors.add(author);
    }

    public boolean addBookToAuthor(String authorId, String bookId) {
        AuthorEntity author = findAuthorById(authorId);
        BookEntity book = findBookById(bookId);

        if (author != null && book != null) {
            author.getBooks().add(book);
            book.setAuthor(author);
            return true;
        }

        return false;
    }

    public void updateBook(BookEntity book) {
    }

    public void updateAuthor(AuthorEntity author) {
    }

    public boolean deleteBook(String id) {
        BookEntity book = findBookById(id);
        if (book != null) {
            books.remove(book);
            return true;
        }
        return false;
    }

    public boolean deleteAuthor(String id) {
        AuthorEntity author = findAuthorById(id);
        if (author != null) {
            authors.remove(author);
            return true;
        }
        return false;
    }

    public BookEntity findBookById(String id) {
        for (BookEntity book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public AuthorEntity findAuthorById(String id) {
        for (AuthorEntity author : authors) {
            if (author.getId().equals(id)) {
                return author;
            }
        }
        return null;
    }

    public List<BookEntity> getAllBooks() {
        return books;
    }

    public List<AuthorEntity> getAllAuthors() {
        return authors;
    }
}

