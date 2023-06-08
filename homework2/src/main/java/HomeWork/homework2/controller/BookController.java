package HomeWork.homework2.controller;

import HomeWork.homework2.entity.Book;
import HomeWork.homework2.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BookController {

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to demoLibraryApplication");
        System.out.println("Enter the value from 1 to 6");
        String answer;
        menu();
        while ((answer = bufferedReader.readLine()) != null) {
            crud(answer, bufferedReader);
        }
    }


    private void menu() {
        System.out.println();
        System.out.println("Press 1 to add book.");
        System.out.println("Press 2 to find book.");
        System.out.println("Press 3 to delete book.");
        System.out.println("Press 4 to update book.");
        System.out.println("Press 5 to show all books.");
        System.out.println("Press 6 to exit the program");
    }


    private void crud(String answer, BufferedReader bufferedReader) throws IOException {
        switch (answer) {
            case "1" -> create(bufferedReader);
            case "2" -> find(bufferedReader);
            case "3" -> delete(bufferedReader);
            case "4" -> update(bufferedReader);
            case "5" -> findAll(bufferedReader);
            case "6" -> System.exit(0);
        }
        menu();
    }


    private void create(BufferedReader bufferedReader) throws IOException {
        System.out.println("Please enter book title");
        String bookTitle = bufferedReader.readLine();
        System.out.println("Please enter book author");
        String bookAuthor = bufferedReader.readLine();
        System.out.println("Please book price");
        String bookPrice = bufferedReader.readLine();
        Book book = new Book();
        book.setBookTitle(bookTitle);
        book.setBookAuthor(bookAuthor);
        book.setPrice(Integer.parseInt(bookPrice));
        BookService.create(book);
    }


    private void find(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the parameter to search by:");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by title");
        System.out.println("3. Search by author");

        String searchOption = bufferedReader.readLine();

        Book book = null;
        switch (searchOption) {
            case "1":
                System.out.println("Please enter the ID:");
                String id = bufferedReader.readLine();
                book = BookService.findById(id);
                break;
            case "2":
                System.out.println("Please enter the title:");
                String title = bufferedReader.readLine();
                book = BookService.findByTitle(title);
                break;
            case "3":
                System.out.println("Please enter the author:");
                String author = bufferedReader.readLine();
                book = BookService.findByAuthor(author);
                break;
            default:
                System.out.println("Invalid search option");
        }

        if (book != null) {
            System.out.println("BookTitle: " + book.getBookTitle() + ", BookAuthor: " + book.getBookAuthor() + ", Price: " + book.getPrice());
        } else {
            System.out.println("There is no book found");
        }
    }


    private void delete(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the parameter to search by:");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by title");
        System.out.println("3. Search by author");

        String searchParameter = bufferedReader.readLine();
        boolean isDeleted = false;

        switch (searchParameter) {
            case "1":
                System.out.println("Enter the id to delete:");
                String idToDelete = bufferedReader.readLine();
                isDeleted = BookService.delete(idToDelete);
                break;
            case "2":
                System.out.println("Enter the title to delete:");
                String titleToDelete = bufferedReader.readLine();
                isDeleted = BookService.deleteByTitle(titleToDelete);
                break;
            case "3":
                System.out.println("Enter the author to delete:");
                String authorToDelete = bufferedReader.readLine();
                isDeleted = BookService.deleteByAuthor(authorToDelete);
                break;
            default:
                System.out.println("Invalid search parameter");
        }

        if (isDeleted) {
            System.out.println("Book has been successfully deleted");
        } else {
            System.out.println("Book not found");
        }
    }


    private void update(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the parameter to search by:");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by title");
        System.out.println("3. Search by author");
        String searchParameter = bufferedReader.readLine();
        System.out.println("Enter the value to search:");
        String value = bufferedReader.readLine();

        Book book = null;
        switch (searchParameter) {
            case "1":
                book = BookService.findById(value);
                break;
            case "2":
                book = BookService.findByTitle(value);
                break;
            case "3":
                book = BookService.findByAuthor(value);
                break;
            default:
                System.out.println("Invalid search parameter");
                return;
        }

        if (book != null) {
            System.out.println("Enter a new book title");
            String newTitle = bufferedReader.readLine();
            System.out.println("Enter a new book author");
            String newAuthor = bufferedReader.readLine();
            System.out.println("Enter a new book price");
            double newPrice = Double.parseDouble(bufferedReader.readLine());

            book.setBookTitle(newTitle);
            book.setBookAuthor(newAuthor);
            book.setPrice((int) newPrice);

            BookService.update(book, searchParameter);

            System.out.println("Book has been successfully updated");
        } else {
            System.out.println("Book not found");
        }
    }


    private void findAll(BufferedReader bufferedReader) {
        Book[] books = BookService.findAll().toArray(new Book[0]);
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            if (book != null) {
                System.out.println("Id: " + book.getId() + ", BookTitle: " + book.getBookTitle() + ", BookAuthor: " + book.getBookAuthor() + ", Price: " + book.getPrice());
            }

        }
    }
}