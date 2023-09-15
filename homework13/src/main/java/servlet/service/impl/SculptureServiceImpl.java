package servlet.service.impl;

import servlet.dao.SculptureDao;
import servlet.dao.impl.SculptureDaoImpl;
import servlet.entity.Sculptor;
import servlet.entity.Sculpture;
import servlet.service.SculptureService;

import java.util.List;

public class SculptureServiceImpl implements SculptureService {

   SculptureDao sculptureDao = new SculptureDaoImpl();

    @Override
    public void create(Sculpture entity) {
        sculptureDao.create(entity);
    }

    @Override
    public void update(Sculpture entity) {
        sculptureDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        sculptureDao.delete(id);
    }

    @Override
    public Sculpture findById(Long id) {
        return sculptureDao.findById(id);
    }

    @Override
    public List<Sculpture> findAll() {
        return sculptureDao.findAll();
    }

    @Override
    public List<Long> getAllSculptureIds() {
        return sculptureDao.getAllSculptureIds();
    }

    @Override
    public List<Sculpture> getSculpturesBySculptorId(Long sculptorId) {
        return sculptureDao.getSculpturesBySculptorId(sculptorId);
    }
    public void addSculptureToSculptor(Sculptor existingSculptor, Sculpture existingSculpture){
        sculptureDao.addSculptureToSculptor(existingSculptor, existingSculpture);
    }
}
