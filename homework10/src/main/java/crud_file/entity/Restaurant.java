package crud_file.entity;

public class Restaurant extends BaseEntity{
    private String name;
    private int michelinStars;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMichelinStars() {
        return michelinStars;
    }

    public void setMichelinStars(int michelinStars) {
        this.michelinStars = michelinStars;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", michelinStars=" + michelinStars +
                '}';
    }
}
