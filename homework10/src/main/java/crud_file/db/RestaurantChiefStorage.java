package crud_file.db;

import crud_file.entity.RestaurantChief;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class RestaurantChiefStorage {
    private static RestaurantChiefStorage restaurantChiefStorage;
    private final List<RestaurantChief> restaurantChiefs = new ArrayList<>();

    private RestaurantChiefStorage() {
    }

    public static RestaurantChiefStorage getInstance() {
        if (restaurantChiefStorage == null) {
            restaurantChiefStorage = new RestaurantChiefStorage();
        }
        return restaurantChiefStorage;
    }

    public void create(RestaurantChief restaurantChief) {
        readRestaurantChiefsFromJson();
        restaurantChief.setId(generateId());
        restaurantChiefs.add(restaurantChief);
        writeRestaurantChiefsToJson();
    }

    public void readRestaurantChiefsFromJson() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("json/restaurantchief.json")) {
            RestaurantChief[] currentRestaurantChiefs = gson.fromJson(fileReader, RestaurantChief[].class);
            if (currentRestaurantChiefs != null) {
                restaurantChiefs.clear();
                restaurantChiefs.addAll(Arrays.asList(currentRestaurantChiefs));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void writeRestaurantChiefsToJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("json/restaurantchief.json")) {
            String json = gson.toJson(restaurantChiefs);
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (restaurantChiefs.stream().anyMatch(restaurantChief -> restaurantChief.getId().equals(id))) {
            return generateId();
        }
        return id;
    }

    private int findRestaurantChiefIndex(String id) {
        for (int i = 0; i < restaurantChiefs.size(); i++) {
            if (restaurantChiefs.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteChiefById(String id) {
        readRestaurantChiefsFromJson();
        int index = findRestaurantChiefIndex(id);
        if (index != -1) {
            restaurantChiefs.remove(index);
            writeRestaurantChiefsToJson();
        } else {
            System.out.println("Restaurant with this chief not found");
        }
    }

    public void deleteRestaurantById(String id) {
        readRestaurantChiefsFromJson();
        int index = findRestaurantChiefIndex(id);
        if (index != -1) {
            restaurantChiefs.remove(index);
            writeRestaurantChiefsToJson();
        } else {
            System.out.println("Restaurant with this chief not found");
        }
    }

    public List<RestaurantChief> getAllChiefsFromRestaurant(String id) {
        readRestaurantChiefsFromJson();
        List<RestaurantChief> chiefs = new ArrayList<>();
        for (RestaurantChief restaurantChief : restaurantChiefs) {
            if (restaurantChief.getRestaurantId().equals(id)) {
                chiefs.add(restaurantChief);
            }
        }
        return chiefs;
    }

    public List<RestaurantChief> getAllRestaurantsFromChief(String id) {
        readRestaurantChiefsFromJson();
        List<RestaurantChief> restaurants = new ArrayList<>();
        for (RestaurantChief restaurantChief : restaurantChiefs) {
            if (restaurantChief.getChiefId().equals(id)) {
                restaurants.add(restaurantChief);
            }
        }
        return restaurants;
    }

    public void deleteChiefFromRestaurant(String chiefId, String restaurantId) {
        readRestaurantChiefsFromJson();
        for (Iterator<RestaurantChief> iterator = restaurantChiefs.iterator(); iterator.hasNext(); ) {
            RestaurantChief restaurantChief = iterator.next();
            if (restaurantChief.getChiefId().equals(chiefId) && restaurantChief.getRestaurantId().equals(restaurantId)) {
                iterator.remove();
                break;
            }
        }
        writeRestaurantChiefsToJson();
    }
}

