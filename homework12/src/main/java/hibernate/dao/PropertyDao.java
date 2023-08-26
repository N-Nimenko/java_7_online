package hibernate.dao;

import hibernate.entity.Property;

import java.util.List;

public interface PropertyDao extends CrudDao<Property>{
    List<Property> findAllOwnersByProperty(Long propertyId);
    List<Property> findAllPropertiesSorted(String sortBy, boolean ascending);
}
