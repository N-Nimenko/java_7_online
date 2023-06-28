package homework4.Service;

import homework4.dao.MatDao;
import homework4.List.MatList;

public class MatService {
    private MatDao matDao;

    public MatService() {
        matDao = new MatDao();
    }

    public void addElement(Double n) {
        matDao.add(n);
    }

    public void addAll(Double... n) {
        matDao.addAll(n);
    }

    public void join(MatList<Double>... ml) {
        matDao.join(ml);
    }

    public void intersection(MatList<Double>... ml) {
        matDao.intersection(ml);
    }

    public void sortDesc() {
        matDao.sortDesc();
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        matDao.sortDesc(firstIndex, lastIndex);
    }

    public void sortDesc(Double value) {
        matDao.sortDesc(value);
    }

    public void sortAsc() {
        matDao.sortAsc();
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        matDao.sortAsc(firstIndex, lastIndex);
    }

    public void sortAsc(Double value) {
        matDao.sortAsc(value);
    }

    public Double get(int index) {
        return matDao.get(index);
    }

    public Double getMax() {
        return matDao.getMax();
    }

    public Double getMin() {
        return matDao.getMin();
    }

    public Double getAverage() {
        return matDao.getAverage();
    }

    public Double getMedian() {
        return matDao.getMedian();
    }

    public Double[] toArray() {
        return matDao.toArray();
    }

    public Double[] toArray(int firstIndex, int lastIndex) {
        return matDao.toArray(firstIndex, lastIndex);
    }

    public MatList<Double> cut(int firstIndex, int lastIndex) {
        return matDao.cut(firstIndex, lastIndex);
    }

    public void clearElements(Integer... indices) {
        matDao.clearElements(indices);
    }

    public void clear() {
        matDao.clear();
    }

}