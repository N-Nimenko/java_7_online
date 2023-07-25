package homework4.List;

import java.util.Arrays;
import java.util.Comparator;

public class MatList<E extends Number> {
    public E[] elements;
    private int[] indexList;
    public int size;

    public MatList() {
        this(10);
    }

    public MatList(int initialCapacity) {
        elements = createArray(initialCapacity);
        indexList = new int[initialCapacity];
        size = 0;
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
            add((E[]) list.toArray());
        }
    }

    public void add(E[] elements) {
        ensureCapacity(size + elements.length);
        for (E element : elements) {
            if (element != null) {
                this.elements[size] = element;
                indexList[size] = size;
                size++;
            }
        }
        System.out.println("Elements added: " + Arrays.toString(elements));
        System.out.println("Current List: " + Arrays.toString(Arrays.copyOf(this.elements, size)));
        System.out.println("Existing Index: " + Arrays.toString(Arrays.copyOf(indexList, size)));
    }

    public void join(MatList<E>... ml) {
        for (MatList<E> list : ml) {
            add((E[]) list.toArray());
        }
        System.out.println("Lists joined");
        System.out.println("Current List: " + Arrays.toString(elements));
    }

    public void intersection(MatList<E>... ml) {
        int[] tempList = new int[size];
        System.arraycopy(indexList, 0, tempList, 0, size);
        for (MatList<E> list : ml) {
            for (int i = 0; i < size; i++) {
                if (!list.contains(elements[tempList[i]])) {
                    tempList[i] = -1;
                }
            }
        }
        int newSize = 0;
        for (int i = 0; i < size; i++) {
            if (tempList[i] != -1) {
                tempList[newSize++] = tempList[i];
            }
        }
        size = newSize;
        indexList = Arrays.copyOf(tempList, newSize);
        System.out.println("Lists intersected");
        System.out.println("Current List: " + Arrays.toString(elements));
    }

    public void sortDesc() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (compareElements(elements[j], elements[j + 1]) < 0) {
                    swapElements(j, j + 1);
                }
            }
        }
        System.out.println("List sorted in descending order");
        System.out.println("Current List: " + Arrays.toString(elements));
    }

    private int compareElements(Number element1, Number element2) {
        return Integer.compare(element1.intValue(), element2.intValue());
    }

    private void swapElements(int index1, int index2) {
        Number tempElement = elements[index1];
        elements[index1] = elements[index2];
        elements[index2] = (E) tempElement;

        int tempIndex = indexList[index1];
        indexList[index1] = indexList[index2];
        indexList[index2] = tempIndex;
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && firstIndex <= lastIndex && lastIndex < size) {
            Arrays.sort(elements, firstIndex, lastIndex + 1, (a, b) -> Double.compare(b.doubleValue(), a.doubleValue()));
            System.out.println("List sorted in descending order from index " + firstIndex + " to " + lastIndex);
            System.out.println("Current List: " + Arrays.toString(elements));
        } else {
            System.out.println("Invalid index range");
        }
    }

    public void sortDesc(E value) {
        if (contains(value)) {
            int index = getIndex(value);
            Arrays.sort(elements, index, size, (a, b) -> Double.compare(b.doubleValue(), a.doubleValue()));
            System.out.println("List sorted in descending order starting from element: " + value);
            System.out.println("Current List: " + Arrays.toString(elements));
        } else {
            System.out.println("Element not found in the list");
        }
    }

    public void sortAsc() {
        Arrays.sort(elements, 0, size);
        System.out.println("List sorted in ascending order");
        System.out.println("Current List: " + Arrays.toString(elements));
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && firstIndex <= lastIndex && lastIndex < size) {
            Arrays.sort(elements, firstIndex, lastIndex + 1);
            System.out.println("List sorted in ascending order from index " + firstIndex + " to " + lastIndex);
            System.out.println("Current List: " + Arrays.toString(elements));
        } else {
            System.out.println("Invalid index range");
        }
    }

    public void sortAsc(E value) {
        if (contains(value)) {
            int index = getIndex(value);
            for (int i = index; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    if (elements[i].doubleValue() > elements[j].doubleValue()) {
                        E temp = elements[i];
                        elements[i] = elements[j];
                        elements[j] = temp;
                    }
                }
            }
            System.out.println("List sorted in ascending order starting from element: " + value);
            System.out.println("Current List: " + Arrays.toString(elements));
        } else {
            System.out.println("Element not found in the list");
        }
    }

    public E get(int index) {
        if (index >= 0 && index < size) {
            return elements[index];
        }
        System.out.println("Invalid index");
        return null;
    }

    public E getMax() {
        if (size > 0) {
            E max = elements[0];
            for (int i = 1; i < size; i++) {
                E current = elements[i];
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
        if (size > 0) {
            E min = elements[0];
            for (int i = 1; i < size; i++) {
                E current = elements[i];
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
        if (size > 0) {
            double sum = 0;
            for (int i = 0; i < size; i++) {
                sum += elements[i].doubleValue();
            }
            double average = sum / size;
            System.out.println("Average: " + average);
            return average;
        }
        System.out.println("List is empty");
        return 0;
    }

    public double getMedian() {
        if (size > 0) {
            E[] sortedList = Arrays.copyOf(elements, size);
            Arrays.sort(sortedList, 0, size, Comparator.comparingDouble(Number::doubleValue));

            int mid = size / 2;

            if (size % 2 == 0) {
                E mid1 = sortedList[mid - 1];
                E mid2 = sortedList[mid];
                double median = (mid1.doubleValue() + mid2.doubleValue()) / 2.0;
                System.out.println("Median: " + median);
                return median;
            } else {
                double median = sortedList[mid].doubleValue();
                System.out.println("Median: " + median);
                return median;
            }
        }
        System.out.println("List is empty");
        return 0;
    }

    public E[] toArray() {
        if (size == 0) {
            System.out.println("List is empty");
            return createArray(0);
        }

        E[] array = createArray(size);
        System.arraycopy(elements, 0, array, 0, size);
        return array;
    }

    public MatList<E> cut(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && firstIndex <= lastIndex && lastIndex < size) {
            E[] sublist = Arrays.copyOfRange(elements, firstIndex, lastIndex + 1);
            MatList<E> cutList = new MatList<>();
            cutList.add(sublist);
            System.arraycopy(elements, lastIndex + 1, elements, firstIndex, size - lastIndex - 1);
            System.arraycopy(indexList, lastIndex + 1, indexList, firstIndex, size - lastIndex - 1);
            size -= (lastIndex - firstIndex + 1);
            return cutList;
        }
        System.out.println("Invalid index range");
        return new MatList<>();
    }

    public void clear() {
        size = 0;
        elements = createArray(10);
        indexList = new int[10];
    }

    public void clearElements(Integer... indices) {
        for (Integer index : indices) {
            if (index >= 0 && index < size) {
                System.arraycopy(elements, index + 1, elements, index, size - index - 1);
                System.arraycopy(indexList, index + 1, indexList, index, size - index - 1);
                size--;
            }
        }
    }

    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(E element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            int newCapacity = Math.max(elements.length * 2, capacity);
            elements = Arrays.copyOf(elements, newCapacity);
            indexList = Arrays.copyOf(indexList, newCapacity);
        }
    }

    public E[] createArray(int capacity) {
        return (E[]) new Number[capacity];
    }
}