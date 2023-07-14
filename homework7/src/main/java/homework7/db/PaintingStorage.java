package homework7.db;

import homework7.entity.Painting;
import homework7.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class PaintingStorage {
    private static PaintingStorage instance;
    private final List<Painting> paintings = new ArrayList<>();

    private PaintingStorage() {
    }

    public static PaintingStorage getInstance() {
        if (instance == null) {
            instance = new PaintingStorage();
        }
        return instance;
    }

    public void create(Painting painting) {
        String id = AppUtil.getUUID();
        painting.setId(id);
        paintings.add(painting);
    }

    public void update(Painting painting) {
        for (int i = 0; i < paintings.size(); i++) {
            if (paintings.get(i).getId().equals(painting.getId())) {
                paintings.set(i, painting);
                break;
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < paintings.size(); i++) {
            if (paintings.get(i).getId().equals(id)) {
                paintings.remove(i);
                break;
            }
        }
    }

    public Painting findOne(String id) {
        for (Painting painting : paintings) {
            if (painting.getId().equals(id)) {
                return painting;
            }
        }
        return null;
    }

    public List<Painting> findAll() {
        return new ArrayList<>(paintings);
    }
}

