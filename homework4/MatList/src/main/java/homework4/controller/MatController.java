package homework4.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import homework4.List.MatList;
import homework4.Service.MatService;

public class MatController {
    private final MatService<Integer> matService;

    public MatController() {
        matService = new MatService<>();
    }

    public void start() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the MatList Application");
        System.out.println("Select your options");
        String answer;
        menu();
        while ((answer = bufferedReader.readLine()) != null) {
            crud(bufferedReader, answer);
        }
    }

    private void menu() {
        System.out.println("********************************************");
        System.out.println("If you want to add an element, enter 1");
        System.out.println("If you want to add multiple elements, enter 2");
        System.out.println("If you want to join with other MatList, enter 3");
        System.out.println("If you want to find intersection with other MatList, enter 4");
        System.out.println("If you want to sort in descending order, enter 5");
        System.out.println("If you want to sort a range in descending order, enter 6");
        System.out.println("If you want to sort from a specific element in descending order, enter 7");
        System.out.println("If you want to sort in ascending order, enter 8");
        System.out.println("If you want to sort a range in ascending order, enter 9");
        System.out.println("If you want to sort from a specific element in ascending order, enter 10");
        System.out.println("If you want to get an element by index, enter 11");
        System.out.println("If you want to get the maximum element, enter 12");
        System.out.println("If you want to get the minimum element, enter 13");
        System.out.println("If you want to get the average value, enter 14");
        System.out.println("If you want to get the median value, enter 15");
        System.out.println("If you want to convert to an array, enter 16");
        System.out.println("If you want to convert a range to an array, enter 17");
        System.out.println("If you want to cut a range from the list, enter 18");
        System.out.println("If you want to clear the list, enter 19");
        System.out.println("If you want to clear specific elements from the list, enter 20");
        System.out.println("If you want to close the application, enter 0");
        System.out.println("********************************************");
    }

    private void crud(BufferedReader bufferedReader, String answer) throws IOException {
        switch (answer) {
            case "1" -> addElement(bufferedReader);
            case "2" -> addMultipleElements(bufferedReader);
            case "3" -> joinWithMatList(bufferedReader);
            case "4" -> intersectWithMatList(bufferedReader);
            case "5" -> sortDescending();
            case "6" -> sortRangeDescending(bufferedReader);
            case "7" -> sortFromElementDescending(bufferedReader);
            case "8" -> sortAscending();
            case "9" -> sortRangeAscending(bufferedReader);
            case "10" -> sortFromElementAscending(bufferedReader);
            case "11" -> getElement(bufferedReader);
            case "12" -> getMax();
            case "13" -> getMin();
            case "14" -> getAverage();
            case "15" -> getMedian();
            case "16" -> convertToArray();
            case "17" -> convertRangeToArray(bufferedReader);
            case "18" -> cutRange(bufferedReader);
            case "19" -> clearList();
            case "20" -> clearElements(bufferedReader);
            case "0" -> {
                System.out.println("Closing the application");
                System.exit(0);
            }
        }
        menu();
    }

    private void addElement(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter an element to add:");
        try {
            int element = Integer.parseInt(bufferedReader.readLine());
            matService.addElements(element);
            System.out.println("Element added successfully");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }
    }

    private void addMultipleElements(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter multiple elements separated by spaces:");
        String input = bufferedReader.readLine();
        String[] elements = input.split(" ");
        try {
            Integer[] intElements = new Integer[elements.length];
            for (int i = 0; i < elements.length; i++) {
                intElements[i] = Integer.parseInt(elements[i]);
            }
            matService.addElements(intElements);
            System.out.println("Elements added successfully");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter integers separated by spaces.");
        }
    }

    private void joinWithMatList(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the elements of the second MatList separated by spaces:");
        String input = bufferedReader.readLine();
        String[] elements = input.split(" ");
        try {
            Integer[] intElements = new Integer[elements.length];
            for (int i = 0; i < elements.length; i++) {
                intElements[i] = Integer.parseInt(elements[i]);
            }
            MatList<Integer> matList = new MatList<>(intElements);
            matService.joinMatLists(matList);
            System.out.println("MatList joined successfully");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter integers separated by spaces.");
        }
    }

    private void intersectWithMatList(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the elements of the second MatList separated by spaces:");
        String input = bufferedReader.readLine();
        String[] elements = input.split(" ");
        try {
            Integer[] intElements = new Integer[elements.length];
            for (int i = 0; i < elements.length; i++) {
                intElements[i] = Integer.parseInt(elements[i]);
            }
            MatList<Integer> matList = new MatList<>(intElements);
            matService.intersectMatLists(matList);
            System.out.println("Intersection with MatList performed successfully");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter integers separated by spaces.");
        }
    }

    private void sortDescending() {
        matService.sortDescending();
    }

    private void sortRangeDescending(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the first and last index of the range to sort (separated by spaces):");
        String input = bufferedReader.readLine();
        String[] indices = input.split(" ");
        if (indices.length == 2) {
            try {
                int firstIndex = Integer.parseInt(indices[0]);
                int lastIndex = Integer.parseInt(indices[1]);
                matService.sortDescending(firstIndex, lastIndex);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid indices.");
            }
        } else {
            System.out.println("Invalid input. Please enter two indices separated by spaces.");
        }
    }

    private void sortFromElementDescending(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the element from which to sort in descending order:");
        try {
            int element = Integer.parseInt(bufferedReader.readLine());
            matService.sortDescending(element);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }
    }

    private void sortAscending() {
        matService.sortAscending();
    }

    private void sortRangeAscending(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the first and last index of the range to sort (separated by spaces):");
        String input = bufferedReader.readLine();
        String[] indices = input.split(" ");
        if (indices.length == 2) {
            try {
                int firstIndex = Integer.parseInt(indices[0]);
                int lastIndex = Integer.parseInt(indices[1]);
                matService.sortAscending(firstIndex, lastIndex);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid indices.");
            }
        } else {
            System.out.println("Invalid input. Please enter two indices separated by spaces.");
        }
    }

    private void sortFromElementAscending(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the element from which to sort in ascending order:");
        try {
            int element = Integer.parseInt(bufferedReader.readLine());
            matService.sortAscending(element);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter an integer.");
        }
    }

    private void getElement(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the index of the element:");
        try {
            int index = Integer.parseInt(bufferedReader.readLine());
            Integer element = matService.getElement(index);
            if (element != null) {
                System.out.println("Element at index " + index + ": " + element);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid index.");
        }
    }

    private void getMax() {
        Integer maxElement = matService.getMaxElement();
        if (maxElement != null) {
            System.out.println("Maximum element: " + maxElement);
        }
    }

    private void getMin() {
        Integer minElement = matService.getMinElement();
        if (minElement != null) {
            System.out.println("Minimum element: " + minElement);
        }
    }

    private void getAverage() {
        double average = matService.getAverageValue();
        System.out.println("Average value: " + average);
    }

    private void getMedian() {
        double median = matService.getMedianValue();
        System.out.println("Median value: " + median);
    }

    private void convertToArray() {
        Integer[] array = matService.toArray();
        System.out.println("Array: " + Arrays.toString(array));
    }

    private void convertRangeToArray(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the first and last index of the range to convert (separated by spaces):");
        String input = bufferedReader.readLine();
        String[] indices = input.split(" ");
        if (indices.length == 2) {
            try {
                int firstIndex = Integer.parseInt(indices[0]);
                int lastIndex = Integer.parseInt(indices[1]);
                Integer[] array = matService.toArray(firstIndex, lastIndex);
                System.out.println("Array: " + Arrays.toString(array));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid indices.");
            }
        } else {
            System.out.println("Invalid input. Please enter two indices separated by spaces.");
        }
    }

    private void cutRange(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the first and last index of the range to cut (separated by spaces):");
        String input = bufferedReader.readLine();
        String[] indices = input.split(" ");
        if (indices.length == 2) {
            try {
                int firstIndex = Integer.parseInt(indices[0]);
                int lastIndex = Integer.parseInt(indices[1]);
                MatList<Integer> cutList = matService.cutElements(firstIndex, lastIndex);
                System.out.println("Range cut successfully. Cut elements: " + Arrays.toString(cutList.toArray()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter valid indices.");
            }
        } else {
            System.out.println("Invalid input. Please enter two indices separated by spaces.");
        }
    }

    private void clearList() {
        matService.clearElements();
        System.out.println("List cleared.");
    }

    private void clearElements(BufferedReader bufferedReader) throws IOException {
        System.out.println("Enter the indices of the elements to clear (separated by spaces):");
        String input = bufferedReader.readLine();
        String[] indices = input.split(" ");
        try {
            Integer[] intIndices = new Integer[indices.length];
            for (int i = 0; i < indices.length; i++) {
                intIndices[i] = Integer.parseInt(indices[i]);
            }
            matService.clearElements(intIndices);
            System.out.println("Elements cleared successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter valid indices.");
        }
    }
}