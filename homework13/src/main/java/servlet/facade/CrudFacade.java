package servlet.facade;

import java.util.List;

public interface CrudFacade<REQUEST, RESPONSE>{
    void create(REQUEST request);
    void update(REQUEST request, Long id);
    void delete(Long id);
    RESPONSE findById(Long id);
    List<RESPONSE> findAll();
}
