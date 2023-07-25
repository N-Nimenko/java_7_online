package crud_file.dao;

import crud_file.entity.Chief;

import java.util.Collection;

public interface ChiefDao {

    void create(Chief chief);

    void update(Chief chief);

    void delete(String id);

    Chief findOne(String id);

    Collection<Chief> findAll();
}
