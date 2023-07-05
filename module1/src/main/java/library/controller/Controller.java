package library.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import library.entity.*;
import library.service.Service;

public class Controller {
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the DemoLibraryApplication");
        System.out.println("Select your options");
        String answer;
        menu();
        while ((answer = bufferedReader.readLine()) != null) {
            crud(bufferedReader, answer);
        }
    }

    private void menu() {
        System.out.println("********************************************");
        System.out.println("If you want to create a book, enter 1");
        System.out.println("If you want to create an author, enter 2");
        System.out.println("If you want to add a book to an author, enter 3");
        System.out.println("If you want to update a book, enter 4");
        System.out.println("If you want to update an author, enter 5");
        System.out.println("If you want to delete a book, enter 6");
        System.out.println("If you want to delete an author, enter 7");
        System.out.println("If you want to find a certain book, enter 8");
        System.out.println("If you want to find a certain author, enter 9");
        System.out.println("If you want to find a certain book and author, enter 10");
        System.out.println("If you want to browse all books and authors, enter 11");
        System.out.println("If you want to close the application, enter 0");
        System.out.println("********************************************");
    }

    private void crud(BufferedReader bufferedReader, String answer) throws IOException {
        switch (answer) {
            case "1" -> bookCreate(bufferedReader);
            case "2" -> authorCreate(bufferedReader);
            case "3" -> addBookToAuthor(bufferedReader);
            case "4" -> updateBook(bufferedReader);
            case "5" -> updateAuthor(bufferedReader);
            case "6" -> deleteBook(bufferedReader);
            case "7" -> deleteAuthor(bufferedReader);
            case "8" -> findCertainBook(bufferedReader);
            case "9" -> findCertainAuthor(bufferedReader);
            case "10" -> findCertainBookAndAuthor(bufferedReader);
            case "11" -> browseAll(bufferedReader);
            case "0" -> {
                System.out.println("Closing the application");
                System.exit(0);
            }
        }
        menu();
    }

    private void bookCreate(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the book title:");
        String title = bufferedReader.readLine();
        System.out.println("Enter the amount of pages:");
        int amountOfPages = Integer.parseInt(bufferedReader.readLine());

        BookEntity book = new BookEntity();
        book.setTitle(title);
        book.setAmountOfPages(String.valueOf(amountOfPages));

        Service.createBook(book);

        System.out.println("Book created successfully.");
    }

    private void authorCreate(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the author's first name:");
        String firstName = bufferedReader.readLine();
        System.out.println("Enter the author's last name:");
        String lastName = bufferedReader.readLine();

        AuthorEntity author = new AuthorEntity();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        Service.createAuthor(author);

        System.out.println("Author created successfully.");
    }

    private void addBookToAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the author ID:");
        String authorId = bufferedReader.readLine();
        System.out.println("Enter the book ID:");
        String bookId = bufferedReader.readLine();

        boolean success = Service.addBookToAuthor(authorId, bookId);

        if (success) {
            System.out.println("Book added to author successfully.");
        } else {
            System.out.println("Failed to add book to author.");
        }
    }

    private void updateBook(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the book ID:");
        String id = bufferedReader.readLine();

        BookEntity book = Service.findBookById(id);
        if (book != null) {
            System.out.println("Enter the new title:");
            String title = bufferedReader.readLine();
            System.out.println("Enter the new amount of pages:");
            int amountOfPages = Integer.parseInt(bufferedReader.readLine());

            book.setTitle(title);
            book.setAmountOfPages(String.valueOf(amountOfPages));

            Service.updateBook(book);

            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void updateAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the author ID:");
        String id = bufferedReader.readLine();

        AuthorEntity author = Service.findAuthorById(id);
        if (author != null) {
            System.out.println("Enter the new first name:");
            String firstName = bufferedReader.readLine();
            System.out.println("Enter the new last name:");
            String lastName = bufferedReader.readLine();

            author.setFirstName(firstName);
            author.setLastName(lastName);

            Service.updateAuthor(author);

            System.out.println("Author updated successfully.");
        } else {
            System.out.println("Author not found.");
        }
    }

    private void deleteBook(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the book ID:");
        String id = bufferedReader.readLine();

        boolean success = Service.deleteBook(id);

        if (success) {
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void deleteAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the author ID:");
        String id = bufferedReader.readLine();

        boolean success = Service.deleteAuthor(id);

        if (success) {
            System.out.println("Author deleted successfully.");
        } else {
            System.out.println("Author not found.");
        }
    }

    private void findCertainBook(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the book ID:");
        String id = bufferedReader.readLine();

        BookEntity book = Service.findBookById(id);
        if (book != null) {
            System.out.println("Book found:");
            System.out.println("ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Amount of Pages: " + book.getAmountOfPages());
        } else {
            System.out.println("Book not found.");
        }
    }

    private void findCertainAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the author ID:");
        String id = bufferedReader.readLine();

        AuthorEntity author = Service.findAuthorById(id);
        if (author != null) {
            System.out.println("Author found:");
            System.out.println("ID: " + author.getId());
            System.out.println("First Name: " + author.getFirstName());
            System.out.println("Last Name: " + author.getLastName());
        } else {
            System.out.println("Author not found.");
        }
    }

    private void findCertainBookAndAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the book ID:");
        String bookId = bufferedReader.readLine();
        System.out.println("Enter the author ID:");
        String authorId = bufferedReader.readLine();

        BookEntity book = Service.findBookById(bookId);
        AuthorEntity author = Service.findAuthorById(authorId);

        if (book != null && author != null) {
            System.out.println("Book and author found:");
            System.out.println("Book ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Amount of Pages: " + book.getAmountOfPages());
            System.out.println("Author ID: " + author.getId());
            System.out.println("First Name: " + author.getFirstName());
            System.out.println("Last Name: " + author.getLastName());
        } else {
            System.out.println("Book or author not found.");
        }
    }

    private void browseAll(BufferedReader bufferedReader) {
        System.out.println("Books and Authors:");

        for (BookEntity book : Service.getAllBooks()) {
            System.out.println("Book ID: " + book.getId());
            System.out.println("Title: " + book.getTitle());
            System.out.println("Amount of Pages: " + book.getAmountOfPages());
        }

        for (AuthorEntity author : Service.getAllAuthors()) {
            System.out.println("Author ID: " + author.getId());
            System.out.println("First Name: " + author.getFirstName());
            System.out.println("Last Name: " + author.getLastName());
        }
    }
}

