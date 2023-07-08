package homework4.dao;

import homework4.List.MatList;

public class MatDao<E extends Number> {
    private final MatList<E> matList;

    public MatDao() {
        matList = new MatList<>();
    }

    public void add(E... elements) {
        matList.add(elements);
    }

    public void join(MatList<E>... matLists) {
        matList.join(matLists);
    }

    public void intersection(MatList<E>... matLists) {
        matList.intersection(matLists);
    }

    public void sortDesc() {
        matList.sortDesc();
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        matList.sortDesc(firstIndex, lastIndex);
    }

    public void sortDesc(E value) {
        matList.sortDesc(value);
    }

    public void sortAsc() {
        matList.sortAsc();
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        matList.sortAsc(firstIndex, lastIndex);
    }

    public void sortAsc(E value) {
        matList.sortAsc(value);
    }

    public E get(int index) {
        return matList.get(index);
    }

    public E getMax() {
        return matList.getMax();
    }

    public E getMin() {
        return matList.getMin();
    }

    public double getAverage() {
        return matList.getAverage();
    }

    public double getMedian() {
        return matList.getMedian();
    }

    public E[] toArray() {
        E[] arr = matList.createArray(matList.size);
        System.arraycopy(matList.elements, 0, arr, 0, matList.size);
        return arr;
    }

    public E[] toArray(int firstIndex, int lastIndex) {
        if (firstIndex >= 0 && firstIndex <= lastIndex && lastIndex < matList.size) {
            int length = lastIndex - firstIndex + 1;
            E[] arr = matList.createArray(length);
            System.arraycopy(matList.elements, firstIndex, arr, 0, length);
            return arr;
        }
        System.out.println("Invalid index range");
        return matList.createArray(0);
    }

    public MatList<E> cut(int firstIndex, int lastIndex) {
        return matList.cut(firstIndex, lastIndex);
    }

    public void clear() {
        matList.clear();
    }

    public void clearElements(Integer... indices) {
        matList.clearElements(indices);
    }

    public boolean contains(E element) {
        return matList.contains(element);
    }

    public int getIndex(E element) {
        return matList.getIndex(element);
    }
}