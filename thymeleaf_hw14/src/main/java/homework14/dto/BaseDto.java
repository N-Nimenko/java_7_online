package homework14.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import homework14.entity.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDto {

    private Long id;
    public <E extends BaseEntity> BaseDto(E dto) {
        this.id = dto.getId();
    }
}
