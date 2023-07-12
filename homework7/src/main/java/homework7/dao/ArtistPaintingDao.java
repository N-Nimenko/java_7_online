package homework7.dao;

import homework7.db.ArtistPaintingStorage;
import homework7.entity.ArtistPainting;

public class ArtistPaintingDao {
    private final ArtistPaintingStorage artistPaintingStorage = ArtistPaintingStorage.getInstance();

    public void create(ArtistPainting artist) {
        artistPaintingStorage.create(artist);
    }

    public void deleteArtistById(String artistId) {
        artistPaintingStorage.deleteArtistById(artistId);
    }

    public void deletePaintingById(String paintingId) {
        artistPaintingStorage.deletePaintingById(paintingId);
    }

    public void deleteArtistFromPainting(String artistId, String paintingId) {
        artistPaintingStorage.deleteArtistFromPaintingId(artistId, paintingId);
    }

    public ArtistPainting[] getAllArtistsFromPaintings(String paintingId) {
        return artistPaintingStorage.getAllArtistsFromPaintings(paintingId);
    }

    public ArtistPainting[] getAllPaintingsFromArtists(String artistId) {
        return artistPaintingStorage.getAllPaintingsFromArtists(artistId);
    }
}

