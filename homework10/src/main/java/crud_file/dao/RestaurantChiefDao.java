package crud_file.dao;

import crud_file.entity.RestaurantChief;

import java.util.List;

public interface RestaurantChiefDao {
    void create(RestaurantChief restaurantChief);

    void deleteChiefById(String id);

    void deleteRestaurantById(String id);
    List<RestaurantChief> getAllChiefsFromRestaurant(String id);

    List<RestaurantChief> getAllRestaurantsFromChief(String id);

    void deleteChiefFromRestaurant(String chiefId, String restaurantId);
}
