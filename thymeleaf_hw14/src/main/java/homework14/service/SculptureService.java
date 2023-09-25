package homework14.service;

import homework14.entity.Sculpture;

import java.util.List;

public interface SculptureService extends CrudService<Sculpture>{
    void checkExists(Long id);
    List<Sculpture> findAllSculpturesBySculptor(Long sculptorId);
}
