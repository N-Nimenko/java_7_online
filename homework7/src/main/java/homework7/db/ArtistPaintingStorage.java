package homework7.db;

import homework7.entity.ArtistPainting;

import java.util.ArrayList;
import java.util.List;

public class ArtistPaintingStorage {
    private static ArtistPaintingStorage instance;
    private final List<ArtistPainting> artistPaintings = new ArrayList<>();

    private ArtistPaintingStorage() {
    }

    public static ArtistPaintingStorage getInstance() {
        if (instance == null) {
            instance = new ArtistPaintingStorage();
        }
        return instance;
    }

    public void create(ArtistPainting artist) {
        artistPaintings.add(artist);
    }

    public void deleteArtistById(String artistId) {
        artistPaintings.removeIf(artistPainting -> artistPainting.getArtistId().equals(artistId));
    }

    public void deletePaintingById(String paintingId) {
        artistPaintings.removeIf(artistPainting -> artistPainting.getPaintingId().equals(paintingId));
    }

    public List<ArtistPainting> getAllArtistsFromPaintings(String paintingId) {
        List<ArtistPainting> result = new ArrayList<>();
        for (ArtistPainting artistPainting : artistPaintings) {
            if (artistPainting.getPaintingId().equals(paintingId)) {
                result.add(artistPainting);
            }
        }
        return result;
    }

    public List<ArtistPainting> getAllPaintingsFromArtists(String artistId) {
        List<ArtistPainting> result = new ArrayList<>();
        for (ArtistPainting artistPainting : artistPaintings) {
            if (artistPainting.getArtistId().equals(artistId)) {
                result.add(artistPainting);
            }
        }
        return result;
    }

    public void deleteArtistFromPaintingId(String artistId, String paintingId) {
        artistPaintings.removeIf(artistPainting -> artistPainting.getPaintingId().equals(paintingId) && artistPainting.getArtistId().equals(artistId));
    }
}

