package crud_file.entity;

public class RestaurantChief extends BaseEntity{
    private String chiefId;
    private String restaurantId;

    public String getChiefId() {
        return chiefId;
    }

    public void setChiefId(String chiefId) {
        this.chiefId = chiefId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "RestaurantChief{" +
                "chiefId='" + chiefId + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                '}';
    }
}
