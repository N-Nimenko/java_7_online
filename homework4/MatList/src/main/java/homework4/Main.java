package homework4;

import homework4.controller.MatController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        MatController matcontroller = new MatController();
        matcontroller.start();
    }
}