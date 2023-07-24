package crud_file.dao.impl;

import crud_file.dao.RestaurantDao;
import crud_file.db.RestaurantStorage;
import crud_file.entity.Restaurant;

import java.util.Collection;


public class JsonRestaurantDao implements RestaurantDao {

    private final RestaurantStorage restaurantStorage = RestaurantStorage.getInstance();

    @Override
    public void create(Restaurant restaurant) {
        restaurantStorage.create(restaurant);
    }

    @Override
    public void update(Restaurant restaurant) {
        restaurantStorage.update(restaurant);
    }

    @Override
    public void delete(String id) {
        restaurantStorage.delete(id);
    }

    @Override
    public Restaurant findOne(String id) {
        return restaurantStorage.findOne(id);
    }

    @Override
    public Collection<Restaurant> findAll() {
        return restaurantStorage.findAll();
    }
}

