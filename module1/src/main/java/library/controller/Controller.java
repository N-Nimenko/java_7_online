package library.controller;

import library.entity.Author;
import library.entity.Book;
import library.service.Service;
import library.util.AppUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to library application");
        System.out.println("Select your option");
        String answer;
        menu();
        while ((answer = bufferedReader.readLine()) != null) {
            crud(bufferedReader, answer);
        }
    }

    public void menu(){
        System.out.println("********************************************");
        System.out.println("If you want to create an author, enter 1");
        System.out.println("If you want to create a book, enter 2");
        System.out.println("If you want to add a book to an author, enter 3");
        System.out.println("If you want to add an author to the book, enter 4");
        System.out.println("If you want to update a book, enter 5");
        System.out.println("If you want to update an author, enter 6");
        System.out.println("If you want to delete a book, enter 7");
        System.out.println("If you want to delete an author, enter 8");
        System.out.println("If you want to delete an author from book, enter 9");
        System.out.println("If you want to find a certain book, enter 10");
        System.out.println("If you want to find a certain author, enter 11");
        System.out.println("If you want to display all books id's, enter 12");
        System.out.println("If you want to display all authors id's, enter 13");
        System.out.println("If you want to display all the books of an author, enter 14");
        System.out.println("If you want to display all the authors of book, enter 15");
        System.out.println("If you want to close the application, enter 0");
        System.out.println("********************************************");
    }

    public void crud(BufferedReader bufferedReader, String answer) throws IOException {
        switch (answer) {
            case "1" -> createAuthor(bufferedReader);
            case "2" -> createBook(bufferedReader);
            case "3" -> addBookToAuthor(bufferedReader);
            case "4" -> addAuthorToBook(bufferedReader);
            case "5" -> updateBook(bufferedReader);
            case "6" -> updateAuthor(bufferedReader);
            case "7" -> deleteBook(bufferedReader);
            case "8" -> deleteAuthor(bufferedReader);
            case "9" -> deleteAuthorFromBook(bufferedReader);
            case "10" -> findBook(bufferedReader);
            case "11" -> findAuthor(bufferedReader);
            case "12" -> displayAllBookIds();
            case "13" -> displayAllAuthorIds();
            case "14" -> displayAllBooksOfAuthor(bufferedReader);
            case "15" -> displayAllAuthorsOfBook(bufferedReader);
            case "0" -> {
                System.out.println("Closing the application");
                System.exit(0);
            }
        }
        menu();
    }

    private void createAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the first name of the author:");
        String firstName = bufferedReader.readLine();
        System.out.println("Enter the last name of the author:");
        String lastName = bufferedReader.readLine();

        Author author = new Author();
        author.setId(AppUtil.getUUID());
        author.setFirstName(firstName);
        author.setLastName(lastName);

        Service.createAuthor(author);
        System.out.println("Author created with ID: " + author.getId());
    }

    private void createBook(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the name of the book:");
        String bookName = bufferedReader.readLine();
        System.out.println("Enter the amount of pages:");
        String bookYearOfPublish = bufferedReader.readLine();

        Book book = new Book();
        book.setId(AppUtil.getUUID());
        book.setName(bookName);
        book.setAmountOfPages(Integer.parseInt(bookYearOfPublish));

        Service.createBook(book);
        System.out.println("Book created with ID: " + book.getId());
    }

    private void addBookToAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the author ID:");
        String authorId = bufferedReader.readLine();
        System.out.println("Enter the book ID:");
        String bookId = bufferedReader.readLine();
        Service.addAuthorToBook(authorId, bookId);
        System.out.println("Author added to the book successfully.");
    }

    private void updateBook(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_BOOK);
        String bookId = bufferedReader.readLine();
        Book book = Service.findBookById(bookId);
        if (book != null) {
            System.out.println("Enter the new name of the book:");
            String bookName = bufferedReader.readLine();
            System.out.println("Enter the new amount of pages:");
            String bookYearOfPublish = bufferedReader.readLine();
            book.setName(bookName);
            book.setAmountOfPages(Integer.parseInt(bookYearOfPublish));
            Service.updateBook(book);
            System.out.println("Book has been updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void updateAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_AUTHOR);
        String authorId = bufferedReader.readLine();
        Author author = Service.findAuthorById(authorId);
        if (author != null) {
            System.out.println("Enter the new first name of the author:");
            String firstName = bufferedReader.readLine();
            System.out.println("Enter the new last name of the author:");
            String lastName = bufferedReader.readLine();
            author.setFirstName(firstName);
            author.setLastName(lastName);
            Service.updateAuthor(author);
            System.out.println("Author has been updated successfully.");
        } else {
            System.out.println("Author not found.");
        }
    }

    private void deleteBook(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_BOOK);
        String bookId = bufferedReader.readLine();
        Book book = Service.findBookById(bookId);
        if (book != null) {
            Service.deleteBookById(bookId);
            System.out.println("Book has been deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    private void deleteAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_AUTHOR);
        String authorId = bufferedReader.readLine();
        Author author = Service.findAuthorById(authorId);
        if (author != null) {
            Service.deleteAuthorById(authorId);
            System.out.println("Author has been deleted successfully.");
        } else {
            System.out.println("Author not found.");
        }
    }

    private void deleteAuthorFromBook(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_AUTHOR);
        String authorId = bufferedReader.readLine();
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_BOOK);
        String bookId = bufferedReader.readLine();
        Service.deleteAuthorFromBook(authorId, bookId);
        System.out.println("Author has been removed from the book successfully.");
    }

    private void findBook(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_BOOK);
        String bookId = bufferedReader.readLine();
        Book book = Service.findBookById(bookId);
        if (book != null) {
            System.out.println("Book found:");
            System.out.println("ID: " + book.getId());
            System.out.println("Name: " + book.getName());
            System.out.println("Amount of pages: " + book.getAmountOfPages());
        } else {
            System.out.println("Book not found.");
        }
    }

    private void findAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_AUTHOR);
        String authorId = bufferedReader.readLine();
        Author author = Service.findAuthorById(authorId);
        if (author != null) {
            System.out.println("Author found:");
            System.out.println("ID: " + author.getId());
            System.out.println("First Name: " + author.getFirstName());
            System.out.println("Last Name: " + author.getLastName());
        } else {
            System.out.println("Author not found.");
        }
    }

    private void displayAllBookIds() {
        Book[] books = Service.findAllBooks();
        System.out.println("All Book IDs:");
        for (Book book : books) {
            System.out.println(book.getId());
        }
    }

    private void displayAllAuthorIds() {
        Author[] authors = Service.findAllAuthors();
        System.out.println("All Author IDs:");
        for (Author author : authors) {
            System.out.println(author.getId());
        }
    }

    private void addAuthorToBook(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_AUTHOR);
        String authorId = bufferedReader.readLine();
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_BOOK);
        String bookId = bufferedReader.readLine();
        Service.addAuthorToBook(authorId, bookId);
        System.out.println("Author has been added to the book successfully.");
    }

    private void displayAllBooksOfAuthor(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_AUTHOR);
        String authorId = bufferedReader.readLine();
        Book[] books = Service.findAllBooksByAuthorsId(authorId);
        System.out.println("All Books of Author:");
        for (Book book : books) {
            System.out.println("ID: " + book.getId());
            System.out.println("Name: " + book.getName());
            System.out.println("Amount of pages: " + book.getAmountOfPages());
            System.out.println("------------------------");
        }
    }

    private void displayAllAuthorsOfBook(BufferedReader bufferedReader) throws IOException {
        System.out.println(AppUtil.FIND_BY_ID_MESSAGE_BOOK);
        String bookId = bufferedReader.readLine();
        Author[] authors = Service.findAllAuthorsByBooksId(bookId);
        System.out.println("All Authors of Book:");
        for (Author author : authors) {
            System.out.println("ID: " + author.getId());
            System.out.println("First Name: " + author.getFirstName());
            System.out.println("Last Name: " + author.getLastName());
            System.out.println("------------------------");
        }
    }
}


