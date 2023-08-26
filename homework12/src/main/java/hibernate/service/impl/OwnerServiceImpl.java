package hibernate.service.impl;

import hibernate.dao.OwnerDao;
import hibernate.dao.impl.OwnerDaoImpl;
import hibernate.entity.Owner;
import hibernate.service.OwnerService;

import java.util.List;
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao = new OwnerDaoImpl();

    @Override
    public void create(Owner entity) {
        ownerDao.create(entity);
    }

    @Override
    public void update(Owner entity) {
        ownerDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        ownerDao.delete(id);
    }

    @Override
    public Owner findById(Long id) {
        return ownerDao.findById(id);
    }

    @Override
    public List<Owner> findAll() {
        return ownerDao.findAll();
    }

    @Override
    public List<Owner> findAllPropertiesByOwner(Long propertyId) {
        return ownerDao.findAllPropertiesByOwner(propertyId);
    }

    @Override
    public List<Owner> findAllOwnersSorted(String sortBy, boolean ascending) {
        return ownerDao.findAllOwnersSorted(sortBy, ascending);
    }

    @Override
    public void addPropertyToOwner(Long ownerId, Long propertyId) {
        ownerDao.addPropertyToOwner(ownerId, propertyId);
    }
}