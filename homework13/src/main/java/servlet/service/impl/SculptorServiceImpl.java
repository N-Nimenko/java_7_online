package servlet.service.impl;

import servlet.dao.SculptorDao;
import servlet.dao.impl.SculptorDaoImpl;
import servlet.entity.Sculptor;
import servlet.service.SculptorService;

import java.util.List;
import java.util.Map;

public class SculptorServiceImpl implements SculptorService {

    SculptorDao sculptorDao = new SculptorDaoImpl();

    @Override
    public void create(Sculptor entity) {
        sculptorDao.create(entity);
    }

    @Override
    public void update(Sculptor entity) {
        sculptorDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        sculptorDao.delete(id);
    }

    @Override
    public Sculptor findById(Long id) {
        return sculptorDao.findById(id);
    }

    @Override
    public List<Sculptor> findAll() {
        return sculptorDao.findAll();
    }

    @Override
    public List<Long> getAllSculptorIds() {
        return sculptorDao.getAllSculptorIds();
    }

    @Override
    public boolean sculptorSculptureRelationExists(Long sculptorId, Long sculptureId) {
        return sculptorDao.sculptorSculptureRelationExists(sculptorId, sculptureId);
    }

    @Override
    public void deleteSculptorSculptureRelation(Long sculptorId, Long sculptureId) {
        sculptorDao.deleteSculptorSculptureRelation(sculptorId, sculptureId);
    }
}
