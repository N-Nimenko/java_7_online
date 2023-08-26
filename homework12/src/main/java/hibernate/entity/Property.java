package hibernate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "properties")
public class Property extends BaseEntity {
    @Column(name = "kind_of_property")
    private String kindOfProperty;
    @Column(name = "rooms_quantity")
    private int roomsQuantity;
    @Column(name = "square_meters")
    private int squareMeters;

    @ManyToMany(mappedBy = "properties")
    private Set<Owner> owners;

    public Set<Owner> getOwners() {
        return owners;
    }

    public void setOwners(Set<Owner> owners) {
        this.owners = owners;
    }

    public String getKindOfProperty() {
        return kindOfProperty;
    }

    public void setKindOfProperty(String kindOfProperty) {
        this.kindOfProperty = kindOfProperty;
    }

    public int getRoomsQuantity() {
        return roomsQuantity;
    }

    public void setRoomsQuantity(int roomsQuantity) {
        this.roomsQuantity = roomsQuantity;
    }

    public int getSquareMeters() {
        return squareMeters;
    }

    public void setSquareMeters(int squareMeters) {
        this.squareMeters = squareMeters;
    }

    @Override
    public String toString() {
        return "Property{" +
                "kindOfProperty='" + kindOfProperty + '\'' +
                ", roomsQuantity=" + roomsQuantity +
                ", squareMeters=" + squareMeters +
                '}';
    }
}
