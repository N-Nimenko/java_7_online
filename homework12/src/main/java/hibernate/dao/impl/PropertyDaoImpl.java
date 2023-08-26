package hibernate.dao.impl;

import hibernate.config.HibernateConfig;
import hibernate.dao.PropertyDao;
import hibernate.entity.Property;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Collections;
import java.util.List;

public class PropertyDaoImpl implements PropertyDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Property entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred while creating the property: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Property entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("An error occurred while updating the property: " + e.getMessage());
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
            Property property = session.get(Property.class, id);
            if (property != null) {
                session.delete(property);
                transaction.commit();
                System.out.println("Property with ID " + id + " has been deleted.");
            } else {
                System.out.println("Property with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while deleting the property: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Property findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Property property = session.get(Property.class, id);
            transaction.commit();
            return property;
        } catch (Exception e) {
            System.out.println("An error occurred while finding the property: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return null;
    }

    @Override
    public List<Property> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("FROM Property");
            List<Property> properties = query.getResultList();
            transaction.commit();
            return properties;
        } catch (Exception e) {
            System.out.println("An error occurred while finding all properties: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    public List<Property> findAllOwnersByProperty(Long ownerId) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query query = session.createQuery("SELECT p FROM Property p JOIN p.owners o WHERE o.id = :ownerId");
            query.setParameter("ownerId", ownerId);
            List<Property> properties = query.getResultList();
            transaction.commit();
            return properties;
        } catch (Exception e) {
            System.out.println("An error occurred while finding properties for owner: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }

    public List<Property> findAllPropertiesSorted(String sortBy, boolean ascending) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            String order = ascending ? "ASC" : "DESC";
            String queryStr = "FROM Property p ORDER BY p." + sortBy + " " + order;

            Query query = session.createQuery(queryStr);
            List<Property> properties = query.getResultList();
            transaction.commit();
            return properties;
        } catch (Exception e) {
            System.out.println("An error occurred while finding all properties: " + e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList();
    }
}

