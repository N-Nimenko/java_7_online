package homework14.facade;


import homework14.dto.SculptureDto;

import java.util.Collection;
import java.util.List;

public interface SculptureFacade extends CrudFacade<SculptureDto>{
    Collection<SculptureDto> findBySculptor(Long id);
    List<SculptureDto> findAllSculpturesBySculptor(Long sculptorId);
}
