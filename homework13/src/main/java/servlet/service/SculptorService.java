package servlet.service;

import servlet.entity.Sculptor;

import java.util.List;
import java.util.Map;

public interface SculptorService extends CrudService<Sculptor>{
    List<Long> getAllSculptorIds();
    boolean sculptorSculptureRelationExists(Long sculptorId, Long sculptureId);
    void deleteSculptorSculptureRelation(Long sculptorId, Long sculptureId);
}
