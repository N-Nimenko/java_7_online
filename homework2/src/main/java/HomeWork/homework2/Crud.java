package HomeWork.homework2;

import HomeWork.homework2.controller.BookController;

import java.io.IOException;

public class Crud{

    public static void main(String[] args) throws IOException {
        BookController bookController = new BookController();
        bookController.start();
    }
}
