package homework4.List;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.lang.reflect.Array;

public class MatList<E extends Number> {
    private List<E> elements;
    public static List<Integer> indexList;

    public MatList() {
        elements = new ArrayList<>();
        indexList = new ArrayList<>();
    }

    public MatList(E[]... numbers) {
        this();
        for (E[] arr : numbers) {
            add(arr);
        }
    }

    public MatList(MatList<E>... numbers) {
        this();
        for (MatList<E> list : numbers) {
            add(list.toArray());
        }
    }

    public void add(E... n) {
        for (int i = 0; i < n.length; i++) {
            E element = n[i];
            elements.add(element);
            int id = elements.size() - 1;
            indexList.add(id);
        }
        System.out.println("Elements added: " + Arrays.toString(n));
        System.out.println("Current List: " + elements);
        System.out.println("Existing Index: " + indexList);
    }

    public void join(MatList<E>... ml) {
        for (MatList<E> list : ml) {
            elements.addAll(list.toList());
        }
        System.out.println("Lists joined");
        System.out.println("Current List: " + elements);
    }

    public void intersection(MatList<E>... ml) {
        List<E> tempList = new ArrayList<>(elements);
        for (MatList<E> list : ml) {
            tempList.retainAll(list.toList());
        }
        elements = tempList;
        System.out.println("Lists intersected");
        System.out.println("Current List: " + elements);
    }

    public void sortDesc() {
        elements.sort(Collections.reverseOrder());
        System.out.println("List sorted in descending order");
        System.out.println("Current List: " + elements);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && firstIndex <= lastIndex && lastIndex < elements.size()) {
            List<E> sublist = elements.subList(firstIndex, lastIndex + 1);
            sublist.sort(Collections.reverseOrder());
            System.out.println("List sorted in descending order from index " + firstIndex + " to " + lastIndex);
            System.out.println("Current List: " + elements);
        } else {
            System.out.println("Invalid index range");
        }
    }

    public void sortDesc(E value) {
        if (elements.contains(value)) {
            int index = elements.indexOf(value);
            List<E> sublist = elements.subList(index, elements.size());
            sublist.sort(Collections.reverseOrder());
            System.out.println("List sorted in descending order starting from element: " + value);
            System.out.println("Current List: " + elements);
        } else {
            System.out.println("Element not found in the list");
        }
    }

    public void sortAsc() {
        elements.sort(null);
        System.out.println("List sorted in ascending order");
        System.out.println("Current List: " + elements);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && firstIndex <= lastIndex && lastIndex < elements.size()) {
            List<E> sublist = elements.subList(firstIndex, lastIndex + 1);
            sublist.sort(null);
            System.out.println("List sorted in ascending order from index " + firstIndex + " to " + lastIndex);
            System.out.println("Current List: " + elements);
        } else {
            System.out.println("Invalid index range");
        }
    }

    public void sortAsc(E value) {
        if (elements.contains(value)) {
            int index = elements.indexOf(value);
            List<E> sublist = elements.subList(index, elements.size());
            sublist.sort(null);
            System.out.println("List sorted in ascending order starting from element: " + value);
            System.out.println("Current List: " + elements);
        } else {
            System.out.println("Element not found in the list");
        }
    }

    public E get(int index) {
        if (index >= 0 && index < elements.size()) {
            E element = elements.get(index);
            return element;
        }
        System.out.println("Invalid index");
        return null;
    }
    public E getMax() {
        if (!elements.isEmpty()) {
            E max = elements.get(0);
            for (int i = 1; i < elements.size(); i++) {
                E current = elements.get(i);
                if (current.doubleValue() > max.doubleValue()) {
                    max = current;
                }
            }
            System.out.println("Maximum value: " + max);
            return max;
        }
        System.out.println("List is empty");
        return null;
    }

    public E getMin() {
        if (!elements.isEmpty()) {
            E min = elements.get(0);
            for (int i = 1; i < elements.size(); i++) {
                E current = elements.get(i);
                if (current.doubleValue() < min.doubleValue()) {
                    min = current;
                }
            }
            System.out.println("Minimum value: " + min);
            return min;
        }
        System.out.println("List is empty");
        return null;
    }

    public double getAverage() {
        if (!elements.isEmpty()) {
            double sum = 0;
            for (E element : elements) {
                sum += element.doubleValue();
            }
            double average = sum / elements.size();
            System.out.println("Average: " + average);
            return average;
        }
        System.out.println("List is empty");
        return 0;
    }

    public double getMedian() {
        if (!elements.isEmpty()) {
            List<E> sortedList = new ArrayList<>(elements);
            sortedList.sort(null);

            int size = sortedList.size();
            int mid = size / 2;

            if (size % 2 == 0) {
                E mid1 = sortedList.get(mid - 1);
                E mid2 = sortedList.get(mid);
                double median = (mid1.doubleValue() + mid2.doubleValue()) / 2.0;
                System.out.println("Median: " + median);
                return median;
            } else {
                double median = sortedList.get(mid).doubleValue();
                System.out.println("Median: " + median);
                return median;
            }
        }
        System.out.println("List is empty");
        return 0;
    }

    public E[] toArray() {
        if (elements.isEmpty()) {
            System.out.println("List is empty");
            return (E[]) new Object[0];
        }

        E[] array = (E[]) Array.newInstance(elements.get(0).getClass(), elements.size());
        return elements.toArray(array);
    }

    public E[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && firstIndex <= lastIndex && lastIndex < elements.size()) {
            List<E> sublist = elements.subList(firstIndex, lastIndex + 1);
            E[] array = (E[]) Array.newInstance(sublist.get(0).getClass(), sublist.size());
            return sublist.toArray(array);
        }
        System.out.println("Invalid index range");
        return (E[]) new Object[0];
    }

    public MatList<E> cut(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && firstIndex <= lastIndex && lastIndex < elements.size()) {
            List<E> sublist = elements.subList(firstIndex, lastIndex + 1);
            MatList<E> cutList = new MatList<>();
            cutList.add(sublist.toArray((E[]) new Number[0]));
            elements.subList(firstIndex, lastIndex + 1).clear();
            return cutList;
        }
        System.out.println("Invalid index range");
        return new MatList<>();
    }

    public void clear() {
        elements.clear();
        indexList.clear();
    }

    public void clearElements(Integer... indices) {
        List<Integer> sortedIndices = new ArrayList<>();
        Collections.addAll(sortedIndices, indices);
        Collections.sort(sortedIndices, Collections.reverseOrder());
        for (Integer index : sortedIndices) {
            if (index >= 0 && index < elements.size()) {
                elements.remove((int) index);
                this.indexList.remove((int) index);
            }
        }
    }

    public List<E> toList() {
        return elements;
    }
}