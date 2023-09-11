package module.dao.impl;

import module.config.HibernateConfig;
import module.dao.IncomeCategoryDao;
import module.entity.IncomeCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class IncomeCategoryDaoImpl implements IncomeCategoryDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(IncomeCategory entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(IncomeCategory entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            IncomeCategory incomeCategory = session.get(IncomeCategory.class, id);
            if (incomeCategory != null) {
                session.delete(incomeCategory);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public IncomeCategory findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            IncomeCategory incomeCategory = session.get(IncomeCategory.class, id);
            transaction.commit();
            return incomeCategory;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<IncomeCategory> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<IncomeCategory> query = session.createQuery("FROM IncomeCategory", IncomeCategory.class);
            List<IncomeCategory> incomeCategories = query.list();
            transaction.commit();
            return incomeCategories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<IncomeCategory> findAllIncomeCategoriesByUserId(Long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "FROM IncomeCategory WHERE user.id = :userId";
            Query<IncomeCategory> query = session.createQuery(hql, IncomeCategory.class);
            query.setParameter("userId", userId);
            List<IncomeCategory> incomeCategories = query.getResultList();
            session.getTransaction().commit();
            return incomeCategories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteAllIncomeCategoriesByUserId(Long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<IncomeCategory> query = session.createQuery("DELETE FROM IncomeCategory WHERE user.id = :userId");
            query.setParameter("userId", userId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<IncomeCategory> sortIncomesByAmount() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "FROM IncomeCategory ORDER BY incomeAmount";
            Query<IncomeCategory> query = session.createQuery(hql, IncomeCategory.class);
            List<IncomeCategory> incomeCategories = query.list();
            session.getTransaction().commit();
            return incomeCategories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<IncomeCategory> sortIncomesByDate() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "FROM IncomeCategory ORDER BY date";
            Query<IncomeCategory> query = session.createQuery(hql, IncomeCategory.class);
            List<IncomeCategory> incomeCategories = query.list();
            session.getTransaction().commit();
            return incomeCategories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<IncomeCategory> sortIncomesByIncomeType() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "FROM IncomeCategory ORDER BY incomeType";
            Query<IncomeCategory> query = session.createQuery(hql, IncomeCategory.class);
            List<IncomeCategory> incomeCategories = query.list();
            session.getTransaction().commit();
            return incomeCategories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<IncomeCategory> findSimilarIncomesByAmount(double targetAmount) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "FROM IncomeCategory WHERE incomeAmount = :amount";
            Query<IncomeCategory> query = session.createQuery(hql, IncomeCategory.class);
            query.setParameter("amount", targetAmount);
            List<IncomeCategory> incomeCategories = query.list();
            session.getTransaction().commit();
            return incomeCategories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<IncomeCategory> findSimilarIncomesByIncomeType(String targetIncomeType) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            String hql = "FROM IncomeCategory WHERE incomeType = :incomeType";
            Query<IncomeCategory> query = session.createQuery(hql, IncomeCategory.class);
            query.setParameter("incomeType", targetIncomeType);
            List<IncomeCategory> incomeCategories = query.list();
            session.getTransaction().commit();
            return incomeCategories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
