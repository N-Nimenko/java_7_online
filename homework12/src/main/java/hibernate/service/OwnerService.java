package hibernate.service;

import hibernate.entity.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner>{
    List<Owner> findAllPropertiesByOwner(Long propertyId);
    List<Owner> findAllOwnersSorted(String sortBy, boolean ascending);
    public void addPropertyToOwner (Long ownerId, Long propertyId);
}
