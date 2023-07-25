package crud_file.dao;

import crud_file.entity.Restaurant;

import java.util.Collection;

public interface RestaurantDao {
    void create(Restaurant restaurant);

    void update(Restaurant restaurant);

    void delete(String id);

    Restaurant findOne(String id);

    Collection<Restaurant> findAll();
}
