package homework7.dao;

import homework7.db.PaintingStorage;
import homework7.entity.Painting;

import java.util.List;

public class PaintingDao {
    private static final PaintingStorage paintingStorage = PaintingStorage.getInstance();

    public void create(Painting painting) {
        paintingStorage.create(painting);
    }

    public void update(Painting painting) {
        paintingStorage.update(painting);
    }

    public static void delete(String id) {
        paintingStorage.delete(id);
    }

    public Painting findOne(String id) {
        return paintingStorage.findOne(id);
    }

    public List<Painting> findAll() {
        return paintingStorage.findAll();
    }
}