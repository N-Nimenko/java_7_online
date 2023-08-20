package crud.jdbc.dao;

import crud.jdbc.entity.BaseEntity;

import java.sql.SQLException;
import java.util.List;

public interface CrudDao<E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findById(Long id);
    List<E> findAll();
}
