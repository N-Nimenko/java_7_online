package homework14.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
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

    @ManyToMany(mappedBy = "sculptures")
    private Set<Sculptor> sculptors;
}

