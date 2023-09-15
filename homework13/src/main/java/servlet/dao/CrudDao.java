package servlet.dao;

import servlet.entity.BaseEntity;

import java.util.List;

public interface CrudDao <E extends BaseEntity> {
    void create(E entity);
    void update(E entity);
    void delete(Long id);
    E findById(Long id);
    List<E> findAll();
}
