package servlet.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import servlet.config.HibernateConfig;
import servlet.dao.SculptorDao;
import servlet.entity.Sculptor;
import servlet.entity.Sculpture;

import java.util.Collections;
import java.util.List;

public class SculptorDaoImpl implements SculptorDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Sculptor entity) {
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
    public void update(Sculptor entity) {
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
            Sculptor sculptorToDelete = session.get(Sculptor.class, id);
            if (sculptorToDelete != null) {
                session.delete(sculptorToDelete);
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
    public Sculptor findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Sculptor sculptor = session.get(Sculptor.class, id);
            return sculptor;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return null;
        }
    }

    @Override
    public List<Sculptor> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Sculptor> query = session.createQuery("FROM Sculptor", Sculptor.class);
            List<Sculptor> sculptors = query.getResultList();
            return sculptors;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Long> getAllSculptorIds() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Long> query = session.createQuery("SELECT id FROM Sculptor", Long.class);
            List<Long> sculptorIds = query.getResultList();
            return sculptorIds;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return Collections.emptyList();
        }
    }

    @Override
    public boolean sculptorSculptureRelationExists(Long sculptorId, Long sculptureId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Query<Long> query = session.createQuery(
                    "SELECT COUNT(*) FROM Sculptor sculptor JOIN sculptor.sculptures sculpture " +
                            "WHERE sculptor.id = :sculptorId AND sculpture.id = :sculptureId", Long.class
            );
            query.setParameter("sculptorId", sculptorId);
            query.setParameter("sculptureId", sculptureId);

            Long count = query.getSingleResult();

            session.getTransaction().commit();

            return count > 0;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return false;
        }
    }


    @Override
    public void deleteSculptorSculptureRelation(Long sculptorId, Long sculptureId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Sculptor sculptor = session.get(Sculptor.class, sculptorId);

            Sculpture sculpture = session.get(Sculpture.class, sculptureId);

            if (sculptor != null && sculpture != null) {
                sculptor.getSculptures().remove(sculpture);
                session.update(sculptor);
            }

            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
        }
    }
}


