package homework7.dao;

import homework7.db.ArtistStorage;
import homework7.entity.Artist;

import java.util.List;

public class ArtistDao {
    private final ArtistStorage artistStorage = ArtistStorage.getInstance();

    public void create(Artist artist) {
        artistStorage.create(artist);
    }

    public void update(Artist artist) {
        artistStorage.update(artist);
    }

    public void delete(String id) {
        artistStorage.delete(id);
    }

    public Artist findOne(String id) {
        return artistStorage.findOne(id);
    }

    public List<Artist> findAll() {
        return artistStorage.findAll();
    }
}
