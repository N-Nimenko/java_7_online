package library.db;

import library.entity.AuthorBook;

public class AuthorBookStorage {
    private static AuthorBookStorage instance;
    private AuthorBook[] authorBooks = new AuthorBook[10];
    private int size = 0;

    private AuthorBookStorage() {
    }

    public static AuthorBookStorage getInstance() {
        if (instance == null) {
            instance = new AuthorBookStorage();
        }
        return instance;
    }

    public void create(AuthorBook authorBook) {
        if (size == authorBooks.length) {
            AuthorBook[] updatedArray = new AuthorBook[size * 2];
            System.arraycopy(authorBooks, 0, updatedArray, 0, authorBooks.length);
            authorBooks = updatedArray;
        }
        authorBooks[size] = authorBook;
        size++;
    }

    public void deleteAuthorById(String authorId) {
        boolean beginShifting = false;
        for (int i = 0; i < size; i++) {
            if (authorBooks[i].getAuthorId().equals(authorId)) {
                authorBooks[i] = null;
                beginShifting = true;
            }
            if (beginShifting) {
                authorBooks[i] = authorBooks[i + 1];
            }
        }
        size--;
    }

    public void deleteBookById(String bookId) {
        boolean beginShifting = false;
        for (int i = 0; i < size; i++) {
            if (authorBooks[i].getBookId().equals(bookId)) {
                authorBooks[i] = null;
                beginShifting = true;
            }
            if (beginShifting) {
                authorBooks[i] = authorBooks[i + 1];
            }
        }
        size--;
    }

    public AuthorBook[] getAllAuthorsFromBooks(String bookId) {
        int count = 0;
        for (AuthorBook authorBook : authorBooks) {
            if (authorBook != null && authorBook.getBookId().equals(bookId)) {
                count++;
            }
        }
        if (count == 0) {
            return new AuthorBook[0];
        }
        int index = 0;
        AuthorBook[] result = new AuthorBook[count];
        for (AuthorBook authorBook : authorBooks) {
            if (authorBook != null && authorBook.getBookId().equals(bookId)) {
                result[index] = authorBook;
                index++;
            }
        }
        return result;
    }

    public AuthorBook[] getAllBooksFromAuthors(String authorId) {
        int count = 0;
        for (AuthorBook authorBook : authorBooks) {
            if (authorBook != null && authorBook.getAuthorId().equals(authorId)) {
                count++;
            }
        }
        if (count == 0) {
            return new AuthorBook[0];
        }
        int index = 0;
        AuthorBook[] result = new AuthorBook[count];
        for (AuthorBook authorBook : authorBooks) {
            if (authorBook != null && authorBook.getAuthorId().equals(authorId)) {
                result[index] = authorBook;
                index++;
            }
        }
        return result;
    }

    public void deleteAuthorFromBookId(String authorId, String bookId) {
        boolean beginShifting = false;
        for (int i = 0; i < size; i++) {
            if (authorBooks[i] != null && authorBooks[i].getBookId().equals(bookId) && authorBooks[i].getAuthorId().equals(authorId)) {
                authorBooks[i] = null;
                beginShifting = true;
            }
            if (beginShifting) {
                authorBooks[i] = authorBooks[i + 1];
            }
        }
        size--;
    }
}
