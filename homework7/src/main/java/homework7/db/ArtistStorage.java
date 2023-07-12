package homework7.db;

import homework7.entity.Artist;
import homework7.util.AppUtil;

public class ArtistStorage {
    private static ArtistStorage instance;
    private Artist[] artists = new Artist[10];
    private int size = 0;

    private ArtistStorage() {
    }

    public static ArtistStorage getInstance() {
        if (instance == null) {
            instance = new ArtistStorage();
        }
        return instance;
    }

    public void create(Artist artist) {
        if (size == artists.length) {
            Artist[] updatedArray = new Artist[size * 2];
            System.arraycopy(artists, 0, updatedArray, 0, size);
            artists = updatedArray;
        }
        String id = AppUtil.getUUID();
        artist.setId(id);
        artists[size] = artist;
        size++;
    }

    public void update(Artist artist) {
        for (int i = 0; i < size; i++) {
            if (artists[i].getId().equals(artist.getId())) {
                artists[i] = artist;
                break;
            }
        }
    }

    public void delete(String id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (artists[i].getId().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            for (int i = index; i < size - 1; i++) {
                artists[i] = artists[i + 1];
            }
            artists[size - 1] = null;
            size--;
        }
    }

    public Artist findOne(String id) {
        for (int i = 0; i < size; i++) {
            if (artists[i].getId().equals(id)) {
                return artists[i];
            }
        }
        return null;
    }

    public Artist[] findAll() {
        Artist[] allArtists = new Artist[size];
        System.arraycopy(artists, 0, allArtists, 0, size);
        return allArtists;
    }
}
