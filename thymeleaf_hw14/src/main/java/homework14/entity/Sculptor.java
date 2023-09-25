package homework14.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "sculptors")
public class Sculptor extends BaseEntity {

    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;

    @ManyToMany
    @JoinTable(
            name = "sculptor_sculpture",
            joinColumns = @JoinColumn(name = "sculptor_id"),
            inverseJoinColumns = @JoinColumn(name = "sculpture_id")
    )
    private Set<Sculpture> sculptures;
}
