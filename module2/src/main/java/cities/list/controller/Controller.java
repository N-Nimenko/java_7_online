package cities.list.controller;

import cities.list.db.Storage;
import cities.list.cities.Cities;

import java.io.*;
import java.util.*;

public class Controller {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public void start(){
        try {
            System.out.println("+---------------------------------------+");
            System.out.println("|      Welcome to the Japanese cities   |");
            System.out.println("|          cheap path finder            |");
            System.out.println("+---------------------------------------+");
            System.out.println("All the available cities: ");
            for (String city : Cities.availableCities) {
                System.out.println(city);
            }
            System.out.println("Enter the names of first and second cities:");

            String[] cityNames = bufferedReader.readLine().split(" ");
            if (cityNames.length != 2) {
                System.err.println("Invalid input: Please enter two city names separated by a space.");
                return;
            }

            String startCityName = cityNames[0];
            String endCityName = cityNames[1];

            int startCityIndex = findCityIndex(startCityName);
            int endCityIndex = findCityIndex(endCityName);

            if (startCityIndex == -1 || endCityIndex == -1) {
                System.err.println("Invalid input: One or both of the entered city names do not exist.");
                return;
            }

            List<Map<Integer, Integer>> cities = readInputFromFile();
            int cost = Storage.findCheapestPath(cities, startCityIndex, endCityIndex);
            System.out.println("+---------------------------------------+");
            System.out.println("|  Cost of the cheapest path has been   |");
            System.out.println("|     written to the output.txt         |");
            System.out.println("+---------------------------------------+");

            writeOutputToFile(startCityName, endCityName, cost);
            askForAnotherPath(bufferedReader);
        } catch (IOException e) {
            System.err.println("+-----------------------------------------------------------+");
            System.err.println("| An error happened: " + e.getMessage() + "|");
            System.err.println("+-----------------------------------------------------------+");
        }
    }

    private List<Map<Integer, Integer>> readInputFromFile() throws IOException {
        List<Map<Integer, Integer>> cities = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader("info/input.txt"));
        int n = Integer.parseInt(reader.readLine());

        List<String> cityList = new ArrayList<>(Cities.availableCities);

        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> city = new HashMap<>();

            String cityName = reader.readLine();
            int p = Integer.parseInt(reader.readLine());

            for (int j = 0; j < p; j++) {
                String[] neighborInfo = reader.readLine().split(" ");
                String neighborCityName = cityList.get(Integer.parseInt(neighborInfo[0]) - 1);
                int cost = Integer.parseInt(neighborInfo[1]);
                city.put(findCityIndex(neighborCityName), cost);
            }

            cities.add(city);
        }

        reader.close();
        return cities;
    }


    private void writeOutputToFile(String startCityName, String endCityName, int cost) {
        try {
            StringBuilder currentContent = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader("info/output.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    currentContent.append(line).append("\n");
                }
            }

            FileWriter writer = new FileWriter("info/output.txt");
            writer.write(currentContent.toString());

            writer.write(String.format("| %-11s | %-9s | %12d |\n", startCityName, endCityName, cost));
            writer.write("+---------------------------------------+\n");
            writer.close();
        } catch (IOException e) {
            System.err.println("+-----------------------------------------------------------+");
            System.err.println("| An error happened: " + e.getMessage() + "|");
            System.err.println("+-----------------------------------------------------------+");
        }
    }

    private int findCityIndex(String cityName) {
        List<String> cityList = new ArrayList<>(Cities.availableCities);
        for (int i = 0; i < cityList.size(); i++) {
            String city = cityList.get(i);
            if (city.equals(cityName)) {
                return i;
            }
        }
        return -1;
    }
    private void askForAnotherPath(BufferedReader bufferedReader) throws IOException {
        System.out.println("Do you want to find another path? (yes/no):");
        String answer = bufferedReader.readLine();
        if ("yes".equalsIgnoreCase(answer)) {
            start();
        } else {
            System.out.println("Goodbye!");
        }
    }
}




