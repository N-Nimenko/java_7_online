package homework14.dto;

import homework14.entity.Sculpture;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SculptureDto extends BaseDto {

    private String name;
    private String materialOfSculpture;
    private String artisticStyle;
    private int yearOfPublishing;
    private int countOfSculptors;

    public SculptureDto(Sculpture sculpture) {
        super(sculpture);
        this.name = sculpture.getName();
        this.materialOfSculpture = sculpture.getMaterialOfSculpture();
        this.artisticStyle = sculpture.getArtisticStyle();
        this.yearOfPublishing = sculpture.getYearOfPublishing();
        if (CollectionUtils.isNotEmpty(sculpture.getSculptors())) {
            this.countOfSculptors = sculpture.getSculptors().size();
        }
    }
}
