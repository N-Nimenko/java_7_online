package hibernate.service.impl;

import hibernate.dao.PropertyDao;
import hibernate.dao.impl.PropertyDaoImpl;
import hibernate.entity.Property;
import hibernate.service.PropertyService;

import java.util.List;

public class PropertyServiceImpl implements PropertyService {

    private final PropertyDao propertyDao = new PropertyDaoImpl();

    @Override
    public void create(Property entity) {
        propertyDao.create(entity);
    }

    @Override
    public void update(Property entity) {
        propertyDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        propertyDao.delete(id);
    }

    @Override
    public Property findById(Long id) {
        return propertyDao.findById(id);
    }

    @Override
    public List<Property> findAll() {
        return propertyDao.findAll();
    }

    @Override
    public List<Property> findAllOwnersByProperty(Long propertyId) {
        return propertyDao.findAllOwnersByProperty(propertyId);
    }

    @Override
    public List<Property> findAllPropertiesSorted(String sortBy, boolean ascending) {
        return propertyDao.findAllPropertiesSorted(sortBy, ascending);
    }
}
