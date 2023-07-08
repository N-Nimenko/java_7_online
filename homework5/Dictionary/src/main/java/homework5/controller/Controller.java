package homework5.controller;

import homework5.dictionary.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Set;

public class Controller {
    private final Dictionary<String, String> dictionary;

    public Controller() {
        dictionary = new Dictionary<>();
    }

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the Dictionary Application");
        System.out.println("Select your options");
        String answer;
        menu();
        while ((answer = bufferedReader.readLine()) != null) {
            crud(bufferedReader, answer);
        }
    }

    private void menu() {
        System.out.println("********************************************");
        System.out.println("If you want to check the size of the dictionary, enter 1");
        System.out.println("If you want to check if the dictionary is empty, enter 2");
        System.out.println("If you want to check if the dictionary contains a key, enter 3");
        System.out.println("If you want to check if the dictionary contains a value, enter 4");
        System.out.println("If you want to get a value by key, enter 5");
        System.out.println("If you want to put a new key-value pair, enter 6");
        System.out.println("If you want to remove an object by key, enter 7");
        System.out.println("If you want to put all key-value pairs from another dictionary, enter 8");
        System.out.println("If you want to clear the dictionary, enter 9");
        System.out.println("If you want to get all keys, enter 10");
        System.out.println("If you want to get a collection of values, enter 11");
        System.out.println("If you want to exit the application, enter 0");
        System.out.println("********************************************");
    }

    private void crud(BufferedReader bufferedReader, String answer) throws IOException {
        switch (answer) {
            case "1" -> getSize();
            case "2" -> checkEmpty();
            case "3" -> containsKey(bufferedReader);
            case "4" -> containsValue(bufferedReader);
            case "5" -> getValue(bufferedReader);
            case "6" -> putKeyValue(bufferedReader);
            case "7" -> removeKeyValue(bufferedReader);
            case "8" -> putAllDictionary(bufferedReader);
            case "9" -> clearDictionary();
            case "10" -> getAllKeys();
            case "11" -> getAllValues();
            case "0" -> {
                System.out.println("Closing the application");
                System.exit(0);
            }
            default -> System.out.println("Invalid operation, try again.");
        }
        menu();
    }

    private void getSize() {
        int size = dictionary.size();
        System.out.println("Size of the dictionary: " + size);
    }

    private void checkEmpty() {
        boolean isEmpty = dictionary.isEmpty();
        System.out.println("Dictionary is empty: " + isEmpty);
    }

    private void containsKey(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the key:");
        String key = bufferedReader.readLine();
        boolean contains = dictionary.containsKey(key);
        System.out.println("Key exists in the dictionary: " + contains);
    }

    private void containsValue(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the value:");
        String value = bufferedReader.readLine();
        boolean contains = dictionary.containsValue(value);
        System.out.println("Value exists in the dictionary: " + contains);
    }

    private void getValue(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the key:");
        String key = bufferedReader.readLine();
        String value = dictionary.get(key);
        System.out.println("Value for key " + key + ": " + value);
    }

    private void putKeyValue(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the key:");
        String key = bufferedReader.readLine();
        System.out.println("Enter the value:");
        String value = bufferedReader.readLine();
        boolean putSuccess = dictionary.put(key, value);
        if (putSuccess) {
            System.out.println("Key-value pair added successfully.");
        } else {
            System.out.println("Failed to add key-value pair. Key already exists.");
        }
    }

    private void removeKeyValue(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the key:");
        String key = bufferedReader.readLine();
        boolean removeSuccess = dictionary.remove(key);
        if (removeSuccess) {
            System.out.println("Key-value pair removed successfully.");
        } else {
            System.out.println("Failed to remove key-value pair. Key does not exist.");
        }
    }

    private void putAllDictionary(BufferedReader bufferedReader) throws IOException {
        Dictionary<String, String> newDictionary = new Dictionary<>();
        System.out.println("Enter the number of key-value pairs to put:");
        int count = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < count; i++) {
            System.out.println("Enter the key:");
            String key = bufferedReader.readLine();
            System.out.println("Enter the value:");
            String value = bufferedReader.readLine();
            newDictionary.put(key, value);
        }
        boolean putAllSuccess = dictionary.putAll(newDictionary);
        if (putAllSuccess) {
            System.out.println("All key-value pairs added successfully.");
        } else {
            System.out.println("Failed to add key-value pairs.");
        }
    }

    private void clearDictionary() {
        boolean clearSuccess = dictionary.clear();
        if (clearSuccess) {
            System.out.println("Dictionary cleared successfully.");
        } else {
            System.out.println("Failed to clear the dictionary.");
        }
    }

    private void getAllKeys() {
        Set<String> keys = dictionary.keySet();
        System.out.println("Keys in the dictionary: " + keys);
    }

    private void getAllValues() {
        Collection<String> values = dictionary.values();
        System.out.println("Values in the dictionary: " + values);
    }
}
