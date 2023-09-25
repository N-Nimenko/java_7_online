package homework14.facade;

import homework14.dto.SculptorDto;
import homework14.dto.SculptureDto;

import java.util.Collection;
import java.util.List;

public interface SculptorFacade extends CrudFacade<SculptorDto>{
    Collection<SculptorDto> findBySculpture(Long id);
    List<SculptorDto> findAllSculptorsByExcludeSculpture(Long sculptureId);
    List<SculptorDto> findAllSculptorsBySculpture(Long sculptureId);
}
