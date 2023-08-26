package hibernate.service;

import hibernate.entity.Property;

import java.util.List;

public interface PropertyService extends CrudService<Property>{
    List<Property> findAllOwnersByProperty(Long propertyId);
    List<Property> findAllPropertiesSorted(String sortBy, boolean ascending);
}
