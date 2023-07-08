package homework4.Service;

import homework4.List.MatList;
import homework4.dao.MatDao;

public class MatService<E extends Number> {
    private MatDao<E> matDao;

    public MatService() {
        matDao = new MatDao<>();
    }

    public void addElements(E... elements) {
        matDao.add(elements);
    }

    public void joinMatLists(MatList<E>... matLists) {
        matDao.join(matLists);
    }

    public void intersectMatLists(MatList<E>... matLists) {
        matDao.intersection(matLists);
    }

    public void sortDescending() {
        matDao.sortDesc();
    }

    public void sortDescending(int firstIndex, int lastIndex) {
        matDao.sortDesc(firstIndex, lastIndex);
    }

    public void sortDescending(E value) {
        matDao.sortDesc(value);
    }

    public void sortAscending() {
        matDao.sortAsc();
    }

    public void sortAscending(int firstIndex, int lastIndex) {
        matDao.sortAsc(firstIndex, lastIndex);
    }

    public void sortAscending(E value) {
        matDao.sortAsc(value);
    }

    public E getElement(int index) {
        return matDao.get(index);
    }

    public E getMaxElement() {
        return matDao.getMax();
    }

    public E getMinElement() {
        return matDao.getMin();
    }

    public double getAverageValue() {
        return matDao.getAverage();
    }

    public double getMedianValue() {
        return matDao.getMedian();
    }

    public E[] toArray() {
        return matDao.toArray();
    }

    public E[] toArray(int firstIndex, int lastIndex) {
        return matDao.toArray(firstIndex, lastIndex);
    }

    public MatList<E> cutElements(int firstIndex, int lastIndex) {
        return matDao.cut(firstIndex, lastIndex);
    }

    public void clearElements() {
        matDao.clear();
    }

    public void clearElements(Integer... indices) {
        matDao.clearElements(indices);
    }

    public boolean containsElement(E element) {
        return matDao.contains(element);
    }

    public int getElementIndex(E element) {
        return matDao.getIndex(element);
    }
}