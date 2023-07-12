package homework7.service;

import homework7.entity.Artist;
import homework7.dao.*;
import homework7.entity.ArtistPainting;
import homework7.entity.Painting;

public class Service {

    private static final ArtistDao artistDao = new ArtistDao();
    private static final PaintingDao paintingDao = new PaintingDao();
    private static final ArtistPaintingDao artistPaintingDao = new ArtistPaintingDao();


    public static void createArtist(Artist artist) {
        artistDao.create(artist);
    }

    public static void createPainting(Painting painting) {
        paintingDao.create(painting);
    }

    public static void updateArtist(Artist artist) {
        artistDao.update(artist);
    }

    public static void updatePainting(Painting painting) {
        paintingDao.update(painting);
    }

    public static Artist findArtistById(String id) {
        return artistDao.findOne(id);
    }

    public static Painting findPaintingById(String id) {
        return paintingDao.findOne(id);
    }

    public static Painting[] findAllPaintings() {
        return paintingDao.findAll();
    }

    public static Artist[] findAllArtists() {
        return artistDao.findAll();
    }

    public static void deleteArtistById(String id) {
        artistDao.delete(id);
        artistPaintingDao.deleteArtistById(id);
    }

    public static void deletePaintingById(String id) {
        PaintingDao.delete(id);
        artistPaintingDao.deletePaintingById(id);
    }

    public static Artist[] findAllArtistsByPaintingsId(String id) {
        var artistPaintings = artistPaintingDao.getAllArtistsFromPaintings(id);
        Artist[] result = new Artist[artistPaintings.length];
        for (int i = 0; i < result.length; i++) {
            Artist found = artistDao.findOne(artistPaintings[i].getArtistId());
            result[i] = found;
        }
        return result;
    }

    public static Painting[] findAllPaintingsByArtistsId(String id) {
        var artistPaintings = artistPaintingDao.getAllPaintingsFromArtists(id);
        Painting[] result = new Painting[artistPaintings.length];
        for (int i = 0; i < result.length; i++) {
            Painting found = paintingDao.findOne(artistPaintings[i].getPaintingId());
            result[i] = found;
        }
        return result;
    }

    public static void addArtistToPainting(String artistId, String paintingId) {
        ArtistPainting artistPainting = new ArtistPainting();
        artistPainting.setArtistId(artistId);
        artistPainting.setPaintingId(paintingId);
        artistPaintingDao.create(artistPainting);
    }

    public static void deleteArtistFromPainting(String artistId, String paintingId) {
        artistPaintingDao.deleteArtistFromPainting(artistId, paintingId);
    }
}
