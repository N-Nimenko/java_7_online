package hibernate.dao.impl;

import hibernate.config.HibernateConfig;
import hibernate.dao.OwnerDao;
import hibernate.entity.Owner;
import hibernate.entity.Property;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class OwnerDaoImpl implements OwnerDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Owner entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred while creating the owner: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Owner entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred while updating the owner: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Owner owner = session.get(Owner.class, id);
            if (owner != null) {
                session.delete(owner);
                transaction.commit();
                System.out.println("Owner with ID " + id + " has been deleted.");
            } else {
                System.out.println("Owner with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the owner: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Owner findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Owner owner = session.get(Owner.class, id);
            transaction.commit();
            return owner;
        } catch (Exception e) {
            System.out.println("An error occurred while finding the owner: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Owner> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Owner");
            List<Owner> owners = query.getResultList();
            transaction.commit();
            return owners;
        } catch (Exception e) {
            System.out.println("An error occurred while finding all owners: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    public List<Owner> findAllPropertiesByOwner(Long propertyId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT o FROM Owner o JOIN o.properties p WHERE p.id = :propertyId");
            query.setParameter("propertyId", propertyId);
            List<Owner> owners = query.getResultList();
            transaction.commit();
            return owners;
        } catch (Exception e) {
            System.out.println("An error occurred while finding owners for property: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    public List<Owner> findAllOwnersSorted(String sortBy, boolean ascending) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            String order = ascending ? "ASC" : "DESC";
            String queryStr = "FROM Owner o ORDER BY o." + sortBy + " " + order;

            Query query = session.createQuery(queryStr);
            List<Owner> owners = query.getResultList();
            transaction.commit();
            return owners;
        } catch (Exception e) {
            System.out.println("An error occurred while finding all owners: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    @Override
    public void addPropertyToOwner(Long ownerId, Long propertyId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Owner owner = session.get(Owner.class, ownerId);
            Property property = session.get(Property.class, propertyId);

            if (owner != null && property != null) {
                owner.getProperties().add(property);
                session.update(owner);
                transaction.commit();
            } else {
                System.out.println("Owner or property not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while adding property to owner: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}

