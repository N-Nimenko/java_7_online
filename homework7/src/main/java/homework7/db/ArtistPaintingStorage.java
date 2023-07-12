package homework7.db;

import homework7.entity.ArtistPainting;

public class ArtistPaintingStorage {
    private static ArtistPaintingStorage instance;
    private ArtistPainting[] artistPaintings = new ArtistPainting[10];
    private int size = 0;

    private ArtistPaintingStorage() {
    }

    public static ArtistPaintingStorage getInstance() {
        if (instance == null) {
            instance = new ArtistPaintingStorage();
        }
        return instance;
    }

    public void create(ArtistPainting artist) {
        if (size == artistPaintings.length) {
            ArtistPainting[] updatedArray = new ArtistPainting[size * 2];
            System.arraycopy(artistPaintings, 0, updatedArray, 0, artistPaintings.length);
            artistPaintings = updatedArray;
        }
        artistPaintings[size] = artist;
        size++;
    }

    public void deleteArtistById(String artistId) {
        boolean beginShifting = false;
        for (int i = 0; i < size; i++) {
            if (artistPaintings[i].getArtistId().equals(artistId)) {
                artistPaintings[i] = null;
                beginShifting = true;
            }
            if (beginShifting) {
                artistPaintings[i] = artistPaintings[i + 1];
            }
        }
        size--;
    }

    public void deletePaintingById(String paintingId) {
        boolean beginShifting = false;
        for (int i = 0; i < size; i++) {
            if (artistPaintings[i].getPaintingId().equals(paintingId)) {
                artistPaintings[i] = null;
                beginShifting = true;
            }
            if (beginShifting) {
                artistPaintings[i] = artistPaintings[i + 1];
            }
        }
        size--;
    }

    public ArtistPainting[] getAllArtistsFromPaintings(String paintingId) {
        int count = 0;
        for (ArtistPainting artistPainting : artistPaintings) {
            if (artistPainting != null && artistPainting.getPaintingId().equals(paintingId)) {
                count++;
            }
        }
        if (count == 0) {
            return new ArtistPainting[0];
        }
        int index = 0;
        ArtistPainting[] result = new ArtistPainting[count];
        for (ArtistPainting artistPainting : artistPaintings) {
            if (artistPainting != null && artistPainting.getPaintingId().equals(paintingId)) {
                result[index] = artistPainting;
                index++;
            }
        }
        return result;
    }

    public ArtistPainting[] getAllPaintingsFromArtists(String artistId) {
        int count = 0;
        for (ArtistPainting artistPainting : artistPaintings) {
            if (artistPainting != null && artistPainting.getArtistId().equals(artistId)) {
                count++;
            }
        }
        if (count == 0) {
            return new ArtistPainting[0];
        }
        int index = 0;
        ArtistPainting[] result = new ArtistPainting[count];
        for (ArtistPainting artistPainting : artistPaintings) {
            if (artistPainting != null && artistPainting.getArtistId().equals(artistId)) {
                result[index] = artistPainting;
                index++;
            }
        }
        return result;
    }

    public void deleteArtistFromPaintingId(String artistId, String paintingId) {
        boolean beginShifting = false;
        for (int i = 0; i < size; i++) {
            if (artistPaintings[i] != null && artistPaintings[i].getPaintingId().equals(paintingId) && artistPaintings[i].getArtistId().equals(artistId)) {
                artistPaintings[i] = null;
                beginShifting = true;
            }
            if (beginShifting) {
                artistPaintings[i] = artistPaintings[i + 1];
            }
        }
        size--;
    }
}

