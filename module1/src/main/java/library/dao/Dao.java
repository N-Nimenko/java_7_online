package library.dao;

import library.entity.AuthorEntity;
import library.entity.BookEntity;

public class Dao {
    private static final int INITIAL_CAPACITY = 10;
    private static final int CAPACITY_INCREMENT = 10;

    private BookEntity[] books;
    private AuthorEntity[] authors;
    private int bookCount;
    private int authorCount;

    public Dao() {
        books = new BookEntity[INITIAL_CAPACITY];
        authors = new AuthorEntity[INITIAL_CAPACITY];
        bookCount = 0;
        authorCount = 0;
    }

    public void createBook(BookEntity book) {
        if (bookCount == books.length) {
            expandBooksArray();
        }
        books[bookCount++] = book;
    }

    public void createAuthor(AuthorEntity author) {
        if (authorCount == authors.length) {
            expandAuthorsArray();
        }
        authors[authorCount++] = author;
    }

    public boolean addBookToAuthor(String authorId, String bookId) {
        AuthorEntity author = findAuthorById(authorId);
        BookEntity book = findBookById(bookId);

        if (author != null && book != null) {
            author.getBooks().add(book);
            return true;
        }
        return false;
    }

        public void updateBook(BookEntity updatedBook) {
            for (int i = 0; i < bookCount; i++) {
                if (books[i].getId().equals(updatedBook.getId())) {
                    books[i] = updatedBook;
                    return;
                }
            }
        }

    public void updateAuthor(AuthorEntity updatedAuthor) {
        for (int i = 0; i < authorCount; i++) {
            if (authors[i].getId().equals(updatedAuthor.getId())) {
                authors[i] = updatedAuthor;
                return;
            }
        }
    }

    public boolean deleteBook(String id) {
        int index = findBookIndexById(id);
        if (index != -1) {
            removeBookAtIndex(index);
            return true;
        }
        return false;
    }

    public boolean deleteAuthor(String id) {
        int index = findAuthorIndexById(id);
        if (index != -1) {
            removeAuthorAtIndex(index);
            return true;
        }
        return false;
    }

    public BookEntity findBookById(String id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId().equals(id)) {
                return books[i];
            }
        }
        return null;
    }

    public AuthorEntity findAuthorById(String id) {
        for (int i = 0; i < authorCount; i++) {
            if (authors[i].getId().equals(id)) {
                return authors[i];
            }
        }
        return null;
    }

    public BookEntity[] getAllBooks() {
        BookEntity[] allBooks = new BookEntity[bookCount];
        System.arraycopy(books, 0, allBooks, 0, bookCount);
        return allBooks;
    }

    public AuthorEntity[] getAllAuthors() {
        AuthorEntity[] allAuthors = new AuthorEntity[authorCount];
        System.arraycopy(authors, 0, allAuthors, 0, authorCount);
        return allAuthors;
    }

    private void expandBooksArray() {
        BookEntity[] newBooks = new BookEntity[books.length + CAPACITY_INCREMENT];
        System.arraycopy(books, 0, newBooks, 0, books.length);
        books = newBooks;
    }

    private void expandAuthorsArray() {
        AuthorEntity[] newAuthors = new AuthorEntity[authors.length + CAPACITY_INCREMENT];
        System.arraycopy(authors, 0, newAuthors, 0, authors.length);
        authors = newAuthors;
    }

    private int findBookIndexById(String id) {
        for (int i = 0; i < bookCount; i++) {
            if (books[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private void removeBookAtIndex(int index) {
        System.arraycopy(books, index + 1, books, index, bookCount - index - 1);
        books[--bookCount] = null;
    }

    private int findAuthorIndexById(String id) {
        for (int i = 0; i < authorCount; i++) {
            if (authors[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private void removeAuthorAtIndex(int index) {
        System.arraycopy(authors, index + 1, authors, index, authorCount - index - 1);
        authors[--authorCount] = null;
    }
}
