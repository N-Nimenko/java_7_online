package servlet.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "sculptor")
public class Sculptor extends BaseEntity {
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "age")
    private int age;

    @OneToMany
    @JoinTable(
            name = "sculptor_sculpture",
            joinColumns = @JoinColumn(name = "sculptor_id"),
            inverseJoinColumns = @JoinColumn(name = "sculpture_id")
    )
    private Set<Sculpture> sculptures;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Sculpture> getSculptures() {
        return sculptures;
    }

    public void setSculptures(Set<Sculpture> sculptures) {
        this.sculptures = sculptures;
    }

    @Override
    public String toString() {
        return "Sculptor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
