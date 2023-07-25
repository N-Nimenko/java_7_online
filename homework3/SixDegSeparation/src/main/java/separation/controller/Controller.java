package separation.controller;

import separation.dao.DAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    private DAO dao;

    public Controller(DAO dao) {
        this.dao = dao;
    }

    public void start() {
        dao.shuffleFriendships();
        dao.shufflePeople();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            System.out.println("Choose the operation");
            System.out.println("1. Check the path between 2 people");
            System.out.println("0. Exit");

            try {
                String choiceString = bufferedReader.readLine();

                if (choiceString.equals("1")) {
                    checkConnection(bufferedReader);
                } else if (choiceString.equals("0")) {
                    return;
                } else {
                    System.out.println("Incorrect input data");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void checkConnection(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the names of 2 people for checking the path (in format name1 name2) All available names: Oleksandr, Vladimir, Oleg, Ivan, Ihor, Evgeniy, Egor, Pavel");
        String[] names = bufferedReader.readLine().split(" ");
        String person1 = names[0];
        String person2 = names[1];

        if (!dao.isValidName(person1) || !dao.isValidName(person2)) {
            System.out.println("You entered wrong names");
            return;
        }

        boolean pathExists = dao.findPath(person1, person2);
        if (pathExists) {
            String[] path = dao.getPath();
            System.out.println("Path between " + person1 + " and " + person2 + " exists!");
            System.out.println("Path of friendship:");
            for (int i = path.length - 1; i >= 0; i--) {
                System.out.println(path[i]);
            }
        } else {
            System.out.println("Path between " + person1 + " and " + person2 + " not exists.");
        }
    }
}
