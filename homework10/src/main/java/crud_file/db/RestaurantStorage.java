package crud_file.db;

import com.google.gson.Gson;
import crud_file.entity.Restaurant;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class RestaurantStorage {
    private static RestaurantStorage restaurantStorage;
    private final List<Restaurant> restaurants = new ArrayList<>();

    private RestaurantStorage() {
    }

    public static RestaurantStorage getInstance() {
        if (restaurantStorage == null) {
            restaurantStorage = new RestaurantStorage();
        }
        return restaurantStorage;
    }

    public void create(Restaurant restaurant) {
        readRestaurantsFromJson();
        restaurant.setId(generateId());
        restaurants.add(restaurant);
        writeRestaurantsToJson();
    }

    public void update(Restaurant restaurant) {
        readRestaurantsFromJson();
        int index = findRestaurantIndex(restaurant.getId());
        if (index != -1) {
            restaurants.set(index, restaurant);
            writeRestaurantsToJson();
        } else {
            System.out.println("Restaurant not found");
        }
    }

    public void delete(String id) {
        readRestaurantsFromJson();
        int index = findRestaurantIndex(id);
        if (index != -1) {
            restaurants.remove(index);
            writeRestaurantsToJson();
        } else {
            System.out.println("Restaurant not found");
        }
    }

    public Restaurant findOne(String id) {
        readRestaurantsFromJson();
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getId().equals(id)) {
                return restaurant;
            }
        }
        return null;
    }

    public Collection<Restaurant> findAll() {
        readRestaurantsFromJson();
        return restaurants;
    }

    public void readRestaurantsFromJson() {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("json/restaurants.json")) {
            Restaurant[] currentRestaurants = gson.fromJson(fileReader, Restaurant[].class);
            if (currentRestaurants != null) {
                restaurants.clear();
                restaurants.addAll(Arrays.asList(currentRestaurants));
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void writeRestaurantsToJson() {
        Gson gson = new Gson();
        try (FileWriter fileWriter = new FileWriter("json/restaurants.json")) {
            String json = gson.toJson(restaurants);
            fileWriter.write(json);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        if (restaurants.stream().anyMatch(restaurant -> restaurant.getId().equals(id))) {
            return generateId();
        }
        return id;
    }

    private int findRestaurantIndex(String id) {
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    public static String getRestaurantUUID() {
        return UUID.randomUUID().toString();
    }
}