package crud_file.dao.impl;

import crud_file.dao.ChiefDao;
import crud_file.db.ChiefStorage;
import crud_file.entity.Chief;

import java.util.Collection;

public class JsonChiefDao implements ChiefDao{
    private final ChiefStorage chiefStorage = ChiefStorage.getInstance();

    @Override
    public void create(Chief chief) {
        chiefStorage.create(chief);
    }

    @Override
    public void update(Chief chief) {
        chiefStorage.update(chief);
    }

    @Override
    public void delete(String id) {
        chiefStorage.delete(id);
    }

    @Override
    public Chief findOne(String id) {
        return chiefStorage.findOne(id);
    }

    @Override
    public Collection<Chief> findAll() {
        return chiefStorage.findAll();
    }
}
