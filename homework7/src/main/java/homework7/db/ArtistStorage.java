package homework7.db;

import homework7.entity.Artist;
import homework7.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

public class ArtistStorage {
    private static ArtistStorage instance;
    private final List<Artist> artists = new ArrayList<>();

    private ArtistStorage() {
    }

    public static ArtistStorage getInstance() {
        if (instance == null) {
            instance = new ArtistStorage();
        }
        return instance;
    }

    public void create(Artist artist) {
        String id = AppUtil.getUUID();
        artist.setId(id);
        artists.add(artist);
    }

    public void update(Artist artist) {
        for (int i = 0; i < artists.size(); i++) {
            if (artists.get(i).getId().equals(artist.getId())) {
                artists.set(i, artist);
                break;
            }
        }
    }

    public void delete(String id) {
        for (int i = 0; i < artists.size(); i++) {
            if (artists.get(i).getId().equals(id)) {
                artists.remove(i);
                break;
            }
        }
    }

    public Artist findOne(String id) {
        for (Artist artist : artists) {
            if (artist.getId().equals(id)) {
                return artist;
            }
        }
        return null;
    }

    public List<Artist> findAll() {
        return new ArrayList<>(artists);
    }
}
