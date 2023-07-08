package homework6.controller;

import homework6.dao.Dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class Controller {
    private Dao dao;

    public Controller() {
        this.dao = new Dao();
    }

    public void start() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to TableMakerApp");
        System.out.println("Select an option:");
        String answer;
        menu();
        try{
        while ((answer = bufferedReader.readLine()) != null) {
            crud(bufferedReader, answer);
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void menu() {
        System.out.println("********************************************");
        System.out.println("If you want to generate a table from a sentence, enter 1");
        System.out.println("If you want to exit the application, enter 0");
        System.out.println("********************************************");
    }

    private void crud(BufferedReader bufferedReader, String answer) {
        switch (answer) {
            case "1" -> generate(bufferedReader);
            case "0" -> System.exit(0);
        }
        menu();
    }

    private void generate(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the sentence:");
            String sentence = bufferedReader.readLine();
            List<Map.Entry<String, Long>> sortedEntries = Dao.processInput(sentence);
            dao.displayStatistics(sortedEntries, sentence);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





