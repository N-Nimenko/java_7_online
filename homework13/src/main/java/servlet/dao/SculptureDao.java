package servlet.dao;

import servlet.entity.Sculptor;
import servlet.entity.Sculpture;

import java.util.List;

public interface SculptureDao extends CrudDao<Sculpture>{
    List<Long> getAllSculptureIds();
    List<Sculpture> getSculpturesBySculptorId(Long sculptorId);
    public void addSculptureToSculptor(Sculptor existingSculptor, Sculpture existingSculpture);
}
