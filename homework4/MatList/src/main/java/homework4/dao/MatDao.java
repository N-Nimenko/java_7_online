package homework4.dao;

import homework4.List.MatList;

public class MatDao {
    private MatList<Double> matList;

    public MatDao() {
        matList = new MatList<>();
    }

    public void add(Double n) {
        matList.add(n);
    }

    public void addAll(Double... n) {
        matList.add(n);
    }

    public void join(MatList<Double>... ml) {
        matList.join(ml);
    }

    public void intersection(MatList<Double>... ml) {
        matList.intersection(ml);
    }

    public void sortDesc() {
        matList.sortDesc();
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        matList.sortDesc(firstIndex, lastIndex);
    }

    public void sortDesc(Double value) {
        matList.sortDesc(value);
    }

    public void sortAsc() {
        matList.sortAsc();
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        matList.sortAsc(firstIndex, lastIndex);
    }

    public void sortAsc(Double value) {
        matList.sortAsc(value);
    }

    public Double get(int index) {
        return matList.get(index);
    }

    public Double getMax() {
        return matList.getMax();
    }

    public Double getMin() {
        return matList.getMin();
    }

    public Double getAverage() {
        return matList.getAverage();
    }

    public Double getMedian() {
        return matList.getMedian();
    }

    public Double[] toArray() {
        return matList.toArray();
    }

    public Double[] toArray(int firstIndex, int lastIndex) {
        return matList.toArray(firstIndex, lastIndex);
    }

    public MatList<Double> cut(int firstIndex, int lastIndex) {
        return matList.cut(firstIndex, lastIndex);
    }

    public void clearElements(Integer... indices) {
        matList.clearElements(indices);
    }

    public void clear() {
        matList.clear();
    }
}