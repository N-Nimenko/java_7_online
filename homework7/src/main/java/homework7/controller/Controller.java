package homework7.controller;

import homework7.entity.Artist;
import homework7.entity.Painting;
import homework7.service.Service;
import homework7.util.AppUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    public void start() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to art application");
        System.out.println("Select your option");
        String answer;
        menu();
        try {
            while ((answer = bufferedReader.readLine()) != null) {
                crud(bufferedReader, answer);
            }
        } catch(IOException e){
            System.err.println("An error happened: " + e.getMessage());
        }
    }

    public void menu(){
        System.out.println("********************************************");
        System.out.println("If you want to create an artist, enter 1");
        System.out.println("If you want to create a painting, enter 2");
        System.out.println("If you want to update an artist, enter 3");
        System.out.println("If you want to update a painting, enter 4");
        System.out.println("If you want to delete an artist, enter 5");
        System.out.println("If you want to delete a painting, enter 6");
        System.out.println("If you want to delete an artist from painting, enter 7");
        System.out.println("If you want to browse a certain painting, enter 8");
        System.out.println("If you want to browse a certain artist, enter 9");
        System.out.println("If you want to browse all the artists with their id's, enter 10");
        System.out.println("If you want to browse all the paintings with their id's, enter 11");
        System.out.println("If you want to browse all paintings by artists, enter 12");
        System.out.println("If you want to browse all artists by paintings, enter 13");
        System.out.println("If you want to add an artist to painting, enter 14");
        System.out.println("If you want to add painting to an artist, enter 15");
        System.out.println("If you want to close the application, enter 0");
        System.out.println("********************************************");
    }

    public void crud(BufferedReader bufferedReader, String answer) {
        try {
            switch (answer) {
                case "1" -> createArtist(bufferedReader);
                case "2" -> createPainting(bufferedReader);
                case "3" -> addPaintingToArtist(bufferedReader);
                case "4" -> updatePainting(bufferedReader);
                case "5" -> updateArtist(bufferedReader);
                case "6" -> deletePainting(bufferedReader);
                case "7" -> deleteArtist(bufferedReader);
                case "8" -> deleteArtistFromPainting(bufferedReader);
                case "9" -> findPainting(bufferedReader);
                case "10" -> findArtist(bufferedReader);
                case "11" -> displayAllPaintingIds();
                case "12" -> displayAllArtistIds();
                case "13" -> addArtistToPainting(bufferedReader);
                case "14" -> displayAllPaintingsOfArtist(bufferedReader);
                case "15" -> displayAllArtistsOfPainting(bufferedReader);
                case "0" -> {
                    System.out.println("Closing the application");
                    System.exit(0);
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
        menu();
    }

    private void createArtist(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the first name of the artist:");
            String firstName = bufferedReader.readLine();
            System.out.println("Enter the last name of the artist:");
            String lastName = bufferedReader.readLine();

            Artist artist = new Artist();
            artist.setId(AppUtil.getUUID());
            artist.setFirstName(firstName);
            artist.setLastName(lastName);

            Service.createArtist(artist);
            System.out.println("Artist created with ID: " + artist.getId());
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void createPainting(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the name of the painting:");
            String paintingName = bufferedReader.readLine();
            System.out.println("Enter the year of publishing:");
            String paintingYearOfPublish = bufferedReader.readLine();

            Painting painting = new Painting();
            painting.setId(AppUtil.getUUID());
            painting.setName(paintingName);
            painting.setYearOfPublishing(Integer.parseInt(paintingYearOfPublish));

            Service.createPainting(painting);
            System.out.println("Painting created with ID: " + painting.getId());
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("A Number format error occurred: " + e.getMessage());
        }
    }

    private void addPaintingToArtist(BufferedReader bufferedReader) {
        try {
            System.out.println("Enter the artist ID:");
            String artistId = bufferedReader.readLine();
            System.out.println("Enter the painting ID:");
            String paintingId = bufferedReader.readLine();
            Service.addArtistToPainting(artistId, paintingId);
            System.out.println("Artist added to the painting successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void updatePainting(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_PAINTING);
            String paintingId = bufferedReader.readLine();
            Painting painting = Service.findPaintingById(paintingId);
            if (painting != null) {
                System.out.println("Enter the new name of the painting:");
                String paintingName = bufferedReader.readLine();
                System.out.println("Enter the new year of publishing:");
                String paintingYearOfPublish = bufferedReader.readLine();
                painting.setName(paintingName);
                painting.setYearOfPublishing(Integer.parseInt(paintingYearOfPublish));
                Service.updatePainting(painting);
                System.out.println("Painting has been updated successfully.");
            } else {
                System.out.println("Painting not found.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        } catch (NumberFormatException e){
            System.err.println("A Number format error occurred: " + e.getMessage());
        }
    }

    private void updateArtist(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_ARTIST);
            String artistId = bufferedReader.readLine();
            Artist artist = Service.findArtistById(artistId);
            if (artist != null) {
                System.out.println("Enter the new first name of the artist:");
                String firstName = bufferedReader.readLine();
                System.out.println("Enter the new last name of the artist:");
                String lastName = bufferedReader.readLine();
                artist.setFirstName(firstName);
                artist.setLastName(lastName);
                Service.updateArtist(artist);
                System.out.println("Artist has been updated successfully.");
            } else {
                System.out.println("Artist not found.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void deletePainting(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_PAINTING);
            String paintingId = bufferedReader.readLine();
            Painting painting = Service.findPaintingById(paintingId);
            if (painting != null) {
                Service.deletePaintingById(paintingId);
                System.out.println("Painting has been deleted successfully.");
            } else {
                System.out.println("Painting not found.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void deleteArtist(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_ARTIST);
            String artistId = bufferedReader.readLine();
            Artist artist = Service.findArtistById(artistId);
            if (artist != null) {
                Service.deleteArtistById(artistId);
                System.out.println("Artist has been deleted successfully.");
            } else {
                System.out.println("Artist not found.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void deleteArtistFromPainting(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_ARTIST);
            String artistId = bufferedReader.readLine();
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_PAINTING);
            String paintingId = bufferedReader.readLine();
            Service.deleteArtistFromPainting(artistId, paintingId);
            System.out.println("Artist has been removed from the painting successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void findPainting(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_PAINTING);
            String paintingId = bufferedReader.readLine();
            Painting painting = Service.findPaintingById(paintingId);
            if (painting != null) {
                System.out.println("Painting found:");
                System.out.println("ID: " + painting.getId());
                System.out.println("Name: " + painting.getName());
                System.out.println("Year of Publishing: " + painting.getYearOfPublishing());
            } else {
                System.out.println("Painting not found.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void findArtist(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_ARTIST);
            String artistId = bufferedReader.readLine();
            Artist artist = Service.findArtistById(artistId);
            if (artist != null) {
                System.out.println("Artist found:");
                System.out.println("ID: " + artist.getId());
                System.out.println("First Name: " + artist.getFirstName());
                System.out.println("Last Name: " + artist.getLastName());
            } else {
                System.out.println("Artist not found.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void displayAllPaintingIds() {
        Painting[] paintings = Service.findAllPaintings();
        System.out.println("All Painting IDs:");
        for (Painting painting : paintings) {
            System.out.println(painting.getId());
        }
    }

    private void displayAllArtistIds() {
        Artist[] artists = Service.findAllArtists();
        System.out.println("All Artist IDs:");
        for (Artist artist : artists) {
            System.out.println(artist.getId());
        }
    }

    private void addArtistToPainting(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_ARTIST);
            String artistId = bufferedReader.readLine();
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_PAINTING);
            String paintingId = bufferedReader.readLine();
            Service.addArtistToPainting(artistId, paintingId);
            System.out.println("Artist has been added to the painting successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void displayAllPaintingsOfArtist(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_ARTIST);
            String artistId = bufferedReader.readLine();
            Painting[] paintings = Service.findAllPaintingsByArtistsId(artistId);
            System.out.println("All Paintings of Artist:");
            for (Painting painting : paintings) {
                System.out.println("ID: " + painting.getId());
                System.out.println("Name: " + painting.getName());
                System.out.println("Year of Publishing: " + painting.getYearOfPublishing());
                System.out.println("------------------------");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    private void displayAllArtistsOfPainting(BufferedReader bufferedReader) {
        try {
            System.out.println(AppUtil.FIND_BY_ID_MESSAGE_PAINTING);
            String paintingId = bufferedReader.readLine();
            Artist[] artists = Service.findAllArtistsByPaintingsId(paintingId);
            System.out.println("All Artists of Painting:");
            for (Artist artist : artists) {
                System.out.println("ID: " + artist.getId());
                System.out.println("First Name: " + artist.getFirstName());
                System.out.println("Last Name: " + artist.getLastName());
                System.out.println("------------------------");
            }
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}




