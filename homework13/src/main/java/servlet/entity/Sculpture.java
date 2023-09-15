package servlet.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "sculpture")
public class Sculpture extends BaseEntity {
    @Column(name = "sculpture_name")
    private String name;
    @Column(name = "sculpture_material")
    private String materialOfSculpture;
    @Column(name = "sculpture_artStyle")
    private String artisticStyle;
    @Column(name = "publishing_year")
    private int yearOfPublishing;

    @ManyToOne
    @JoinColumn(name = "sculptor_id")
    private Sculptor sculptor;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterialOfSculpture() {
        return materialOfSculpture;
    }

    public void setMaterialOfSculpture(String materialOfSculpture) {
        this.materialOfSculpture = materialOfSculpture;
    }

    public String getArtisticStyle() {
        return artisticStyle;
    }

    public void setArtisticStyle(String artisticStyle) {
        this.artisticStyle = artisticStyle;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public Sculptor getSculptor() {
        return sculptor;
    }

    public void setSculptor(Sculptor sculptor) {
        this.sculptor = sculptor;
    }

    @Override
    public String toString() {
        return "Sculpture{" +
                "name='" + name + '\'' +
                ", materialOfSculpture='" + materialOfSculpture + '\'' +
                ", artisticStyle='" + artisticStyle + '\'' +
                ", yearOfPublishing=" + yearOfPublishing +
                '}';
    }
}

