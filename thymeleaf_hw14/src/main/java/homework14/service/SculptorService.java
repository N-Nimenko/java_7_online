package homework14.service;

import homework14.entity.Sculptor;

import java.util.List;

public interface SculptorService extends CrudService<Sculptor>{
    void checkExists(Long id);
    List<Sculptor> findAllSculptorsBySculpture(Long sculptureId);
    List<Sculptor> findAllSculptorsByExcludeSculpture(Long sculptureId);
}
