package servlet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import servlet.config.HibernateConfig;
import servlet.dao.SculptureDao;
import servlet.entity.Sculptor;
import servlet.entity.Sculpture;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SculptureDaoImpl implements SculptureDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Sculpture entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Sculpture entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
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
            Sculpture sculptureToDelete = session.get(Sculpture.class, id);
            if (sculptureToDelete != null) {
                session.delete(sculptureToDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Sculpture findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Sculpture sculpture = session.get(Sculpture.class, id);
            return sculpture;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return null;
        }
    }

    @Override
    public List<Sculpture> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Sculpture> query = session.createQuery("FROM Sculpture", Sculpture.class);
            List<Sculpture> sculptures = query.getResultList();
            return sculptures;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Long> getAllSculptureIds() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Long> query = session.createQuery("SELECT id FROM Sculpture", Long.class);
            List<Long> sculptureIds = query.getResultList();
            return sculptureIds;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Sculpture> getSculpturesBySculptorId(Long sculptorId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Sculpture> query = session.createQuery("FROM Sculpture WHERE sculptor.id = :sculptorId", Sculpture.class);
            query.setParameter("sculptorId", sculptorId);
            List<Sculpture> sculptures = query.getResultList();
            return sculptures;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return Collections.emptyList();
        }
    }
    @Override
    public void addSculptureToSculptor(Sculptor existingSculptor, Sculpture existingSculpture) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Set<Sculpture> sculptures = existingSculptor.getSculptures();
            sculptures.add(existingSculpture);

            existingSculpture.setSculptor(existingSculptor);

            session.update(existingSculptor);
            session.update(existingSculpture);

            transaction.commit();
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
