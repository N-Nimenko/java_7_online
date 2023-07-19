package crud_file.dao.impl;

import crud_file.dao.RestaurantChiefDao;
import crud_file.db.RestaurantChiefStorage;
import crud_file.entity.RestaurantChief;

import java.util.List;

public class JsonRestaurantChiefDao implements RestaurantChiefDao {

    private final RestaurantChiefStorage restaurantChiefStorage = RestaurantChiefStorage.getInstance();
    @Override
    public void create(RestaurantChief restaurantChief) {
        restaurantChiefStorage.create(restaurantChief);
    }

    @Override
    public void deleteChiefById(String id) {
        restaurantChiefStorage.deleteChiefById(id);
    }

    @Override
    public void deleteRestaurantById(String id) {
        restaurantChiefStorage.deleteRestaurantById(id);
    }

    @Override
    public List<RestaurantChief> getAllChiefsFromRestaurant(String id) {
        return restaurantChiefStorage.getAllChiefsFromRestaurant(id);
    }

    @Override
    public List<RestaurantChief> getAllRestaurantsFromChief(String id) {
        return restaurantChiefStorage.getAllRestaurantsFromChief(id);
    }

    @Override
    public void deleteChiefFromRestaurant(String chiefId, String restaurantId) {
        restaurantChiefStorage.deleteChiefFromRestaurant(chiefId, restaurantId);
    }
}
