package crud_file.service;

import crud_file.dao.impl.JsonChiefDao;
import crud_file.dao.impl.JsonRestaurantChiefDao;
import crud_file.dao.impl.JsonRestaurantDao;
import crud_file.entity.Chief;
import crud_file.entity.Restaurant;
import crud_file.dao.*;
import crud_file.entity.RestaurantChief;

import java.util.List;

public class Service {
    private static final ChiefDao chiefDao = new JsonChiefDao();
    private static final RestaurantDao restaurantDao = new JsonRestaurantDao();
    private static final RestaurantChiefDao restaurantChiefDao = new JsonRestaurantChiefDao();

    public static void createChief(Chief chief) {
        chiefDao.create(chief);
    }

    public static void createRestaurant(Restaurant restaurant) {
        restaurantDao.create(restaurant);
    }

    public static void updateChief(Chief chief) {
        chiefDao.update(chief);
    }

    public static void updateRestaurant(Restaurant restaurant) {
        restaurantDao.update(restaurant);
    }

    public static Chief findChiefById(String id) {
        return chiefDao.findOne(id);
    }

    public static Restaurant findRestaurantById(String id) {
        return restaurantDao.findOne(id);
    }

    public static List<Restaurant> findAllRestaurants() {
        return (List<Restaurant>) restaurantDao.findAll();
    }

    public static List<Chief> findAllChiefs() {
        return (List<Chief>) chiefDao.findAll();
    }

    public static void deleteChiefById(String id) {
        chiefDao.delete(id);
        restaurantChiefDao.deleteChiefById(id);
    }

    public static void deleteRestaurantById(String id) {
        restaurantDao.delete(id);
        restaurantChiefDao.deleteRestaurantById(id);
    }

    public static Chief[] findAllChiefsByRestaurantId(String id) {
        var restaurantChiefs = restaurantChiefDao.getAllChiefsFromRestaurant(id);
        Chief[] result = new Chief[restaurantChiefs.size()];
        for (int i = 0; i < result.length; i++) {
            Chief found = chiefDao.findOne(restaurantChiefs.get(i).getChiefId());
            result[i] = found;
        }
        return result;
    }

    public static Restaurant[] findAllRestaurantsByChiefId(String id) {
        var restaurantChiefs = restaurantChiefDao.getAllRestaurantsFromChief(id);
        Restaurant[] result = new Restaurant[restaurantChiefs.size()];
        for (int i = 0; i < result.length; i++) {
            Restaurant found = restaurantDao.findOne(restaurantChiefs.get(i).getRestaurantId());
            result[i] = found;
        }
        return result;
    }

    public static void addChiefToRestaurant(String chiefId, String restaurantId) {
        RestaurantChief restaurantChief = new RestaurantChief();
        restaurantChief.setChiefId(chiefId);
        restaurantChief.setRestaurantId(restaurantId);
        restaurantChiefDao.create(restaurantChief);
    }

    public static void deleteChiefFromRestaurant(String chiefId, String restaurantId) {
        restaurantChiefDao.deleteChiefFromRestaurant(chiefId, restaurantId);
    }
}

