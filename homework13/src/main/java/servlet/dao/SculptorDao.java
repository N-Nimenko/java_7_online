package servlet.dao;

import servlet.entity.Sculptor;

import java.util.List;

public interface SculptorDao extends CrudDao<Sculptor> {
    List<Long> getAllSculptorIds();
    boolean sculptorSculptureRelationExists(Long sculptorId, Long sculptureId);
    void deleteSculptorSculptureRelation(Long sculptorId, Long sculptureId);
}
