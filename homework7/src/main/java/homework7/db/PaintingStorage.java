package homework7.db;

import homework7.entity.Painting;
import homework7.util.AppUtil;

public class PaintingStorage {
    private static PaintingStorage instance;
    private Painting[] paintings = new Painting[10];
    private int size = 0;

    private PaintingStorage() {
    }

    public static PaintingStorage getInstance() {
        if (instance == null) {
            instance = new PaintingStorage();
        }
        return instance;
    }

    public void create(Painting painting) {
        if (size == paintings.length) {
            Painting[] updatedArray = new Painting[size * 2];
            System.arraycopy(paintings, 0, updatedArray, 0, size);
            paintings = updatedArray;
        }
        String id = AppUtil.getUUID();
        painting.setId(id);
        paintings[size] = painting;
        size++;
    }

    public void update(Painting painting) {
        for (int i = 0; i < size; i++) {
            if (paintings[i].getId().equals(painting.getId())) {
                paintings[i] = painting;
                break;
            }
        }
    }

    public void delete(String id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (paintings[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                paintings[i] = paintings[i + 1];
            }
            paintings[size - 1] = null;
            size--;
        }
    }

    public Painting findOne(String id) {
        for (int i = 0; i < size; i++) {
            if (paintings[i].getId().equals(id)) {
                return paintings[i];
            }
        }
        return null;
    }

    public Painting[] findAll() {
        Painting[] allPaintings = new Painting[size];
        System.arraycopy(paintings, 0, allPaintings, 0, size);
        return allPaintings;
    }
}

