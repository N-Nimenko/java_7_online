package homework4.controller;

import homework4.Service.MatService;
import homework4.List.MatList;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;


public class MatController {
        private final MatService matService;

        public MatController() {
                matService = new MatService();
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
                System.out.println("Enter the element:");
                Double element = Double.parseDouble(bufferedReader.readLine());
                matService.addElement(element);
                System.out.println("Element added successfully.");
        }

        private void addMultipleElements(BufferedReader bufferedReader) throws IOException {
                System.out.println("Enter the elements separated by space:");
                String[] input = bufferedReader.readLine().split(" ");
                Double[] elements = new Double[input.length];
                for (int i = 0; i < input.length; i++) {
                        elements[i] = Double.parseDouble(input[i]);
                }
                matService.addAll(elements);
                System.out.println("Elements added successfully.");
        }

        private void joinWithMatList(BufferedReader bufferedReader) throws IOException {
                System.out.println("Enter the elements of the other MatList separated by space:");
                String[] input = bufferedReader.readLine().split(" ");
                Double[] elements = new Double[input.length];
                for (int i = 0; i < input.length; i++) {
                        elements[i] = Double.parseDouble(input[i]);
                }
                matService.join(new MatList<>(elements));
                System.out.println("MatList joined successfully.");
        }

        private void intersectWithMatList(BufferedReader bufferedReader) throws IOException {
                System.out.println("Enter the elements of the other MatList separated by space:");
                String[] input = bufferedReader.readLine().split(" ");
                Double[] elements = new Double[input.length];
                for (int i = 0; i < input.length; i++) {
                        elements[i] = Double.parseDouble(input[i]);
                }
                matService.intersection(new MatList<>(elements));
                System.out.println("Intersection with MatList performed successfully.");
        }

        private void sortDescending() {
                matService.sortDesc();
                System.out.println("List sorted in descending order.");
        }

        private void sortRangeDescending(BufferedReader bufferedReader) throws IOException {
                System.out.println("Enter the first and last index of the range separated by space:");
                String[] input = bufferedReader.readLine().split(" ");
                int firstIndex = Integer.parseInt(input[0]);
                int lastIndex = Integer.parseInt(input[1]);
                matService.sortDesc(firstIndex, lastIndex);
                System.out.println("Range sorted in descending order.");
        }

        private void sortFromElementDescending(BufferedReader bufferedReader) throws IOException {
                System.out.println("Enter the element:");
                Double element = Double.parseDouble(bufferedReader.readLine());
                matService.sortDesc(element);
                System.out.println("List sorted from the element in descending order.");
        }

        private void sortAscending() {
                matService.sortAsc();
                System.out.println("List sorted in ascending order.");
        }

        private void sortRangeAscending(BufferedReader bufferedReader) throws IOException {
                System.out.println("Enter the first and last index of the range separated by space:");
                String[] input = bufferedReader.readLine().split(" ");
                int firstIndex = Integer.parseInt(input[0]);
                int lastIndex = Integer.parseInt(input[1]);
                matService.sortAsc(firstIndex, lastIndex);
                System.out.println("Range sorted in ascending order.");
        }

        private void sortFromElementAscending(BufferedReader bufferedReader) throws IOException {
                System.out.println("Enter the element:");
                Double element = Double.parseDouble(bufferedReader.readLine());
                matService.sortAsc(element);
                System.out.println("List sorted from the element in ascending order.");
        }

        private void getElement(BufferedReader bufferedReader) throws IOException {
                System.out.println("Existing index List: " + MatList.indexList);
                int index = Integer.parseInt(bufferedReader.readLine());
                Double element = matService.get(index);
                System.out.println("Element at index " + index + ": " + element);
        }

        private void getMax() {
                Double max = matService.getMax();
                System.out.println("Maximum element: " + max);
        }

        private void getMin() {
                Double min = matService.getMin();
                System.out.println("Minimum element: " + min);
        }

        private void getAverage() {
                Double average = matService.getAverage();
                System.out.println("Average value: " + average);
        }

        private void getMedian() {
                Double median = matService.getMedian();
                System.out.println("Median value: " + median);
        }

        private void convertToArray() {
                Double[] array = matService.toArray();
                System.out.println("Array: " + Arrays.toString(array));
        }

        private void convertRangeToArray(BufferedReader bufferedReader) throws IOException {
                System.out.println("Existing index List: " + MatList.indexList);
                System.out.println("Enter the first and last index of the range separated by space:");
                String[] input = bufferedReader.readLine().split(" ");

                if (input.length >= 2) {
                        int firstIndex = Integer.parseInt(input[0]);
                        int lastIndex = Integer.parseInt(input[1]);
                        Double[] array = matService.toArray(firstIndex, lastIndex);
                        System.out.println("Array: " + Arrays.toString(array));
                } else {
                        System.out.println("Invalid input. Please provide both first and last index.");
                }
        }

        private void cutRange(BufferedReader bufferedReader) throws IOException {
                System.out.println("Existing index List: " + MatList.indexList);
                System.out.println("Enter the first and last index of the range separated by space:");
                String[] input = bufferedReader.readLine().split(" ");
                int firstIndex = Integer.parseInt(input[0]);
                int lastIndex = Integer.parseInt(input[1]);
                MatList<Double> cutList = matService.cut(firstIndex, lastIndex);
                System.out.println("Cut range: " + cutList);
        }

        private void clearList() {
                matService.clear();
                System.out.println("List cleared.");
        }

        private void clearElements(BufferedReader bufferedReader) throws IOException {
                System.out.println("Existing index List: " + MatList.indexList);
                System.out.println("Enter the indices of the elements to clear separated by space:");
                String[] input = bufferedReader.readLine().split(" ");
                Integer[] indices = new Integer[input.length];
                for (int i = 0; i < input.length; i++) {
                        indices[i] = Integer.parseInt(input[i]);
                }
                matService.clearElements(indices);
                System.out.println("Elements cleared from the list.");
        }
}