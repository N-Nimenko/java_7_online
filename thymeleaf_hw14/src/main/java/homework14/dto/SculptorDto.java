package homework14.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import homework14.entity.Sculptor;

@Getter
@Setter
@NoArgsConstructor
public class SculptorDto extends BaseDto {

    private String firstName;
    private String lastName;
    private int age;
    private int countOfSculptures;

    public SculptorDto(Sculptor sculptor) {
        super(sculptor);
        this.firstName = sculptor.getFirstName();
        this.lastName = sculptor.getLastName();
        this.age = sculptor.getAge();
        if (CollectionUtils.isNotEmpty(sculptor.getSculptures())) {
            this.countOfSculptures = sculptor.getSculptures().size();
        }
    }
}
