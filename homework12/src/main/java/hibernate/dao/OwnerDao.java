package hibernate.dao;

import hibernate.entity.Owner;
import hibernate.entity.Property;

import java.util.List;

public interface OwnerDao extends CrudDao<Owner>{
    List<Owner> findAllPropertiesByOwner(Long propertyId);
    List<Owner> findAllOwnersSorted(String sortBy, boolean ascending);
    public void addPropertyToOwner (Long ownerId, Long propertyId);
}
