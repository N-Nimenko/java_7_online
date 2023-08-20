package crud.jdbc.service.impl;

import crud.jdbc.dao.DirectorDao;
import crud.jdbc.entity.Director;
import crud.jdbc.service.DirectorService;
import framework.annotations.BeanClass;
import framework.annotations.InjectBean;

import java.util.List;
@BeanClass
public class DirectorServiceImpl implements DirectorService {
    @InjectBean
    private DirectorDao directorDao;

    @Override
    public void create(Director entity) {
        directorDao.create(entity);
    }

    @Override
    public void update(Director entity) {
        directorDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        directorDao.delete(id);
    }

    @Override
    public Director findById(Long id) {
        return directorDao.findById(id);
    }

    @Override
    public List<Director> findAll() {
        return directorDao.findAll();
    }
}