package servlet.service;

import servlet.dto.SculptureRequestDto;
import servlet.entity.Sculptor;
import servlet.entity.Sculpture;

import java.util.List;

public interface SculptureService extends CrudService<Sculpture>{
    static void addSculptureToSculptor(Long sculptorId, Long sculptureId) {}
    List<Long> getAllSculptureIds();
    List<Sculpture> getSculpturesBySculptorId(Long sculptorId);
    public void addSculptureToSculptor(Sculptor existingSculptor, Sculpture existingSculpture);
}
