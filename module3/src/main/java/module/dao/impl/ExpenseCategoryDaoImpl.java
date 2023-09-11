package module.dao.impl;

import module.config.HibernateConfig;
import module.dao.ExpenseCategoryDao;
import module.entity.ExpenseCategory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;

public class ExpenseCategoryDaoImpl implements ExpenseCategoryDao {

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(ExpenseCategory entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ExpenseCategory entity) {
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
            ExpenseCategory categoryToDelete = session.get(ExpenseCategory.class, id);
            if (categoryToDelete != null) {
                session.delete(categoryToDelete);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ExpenseCategory findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            ExpenseCategory category = session.get(ExpenseCategory.class, id);
            transaction.commit();
            return category;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ExpenseCategory> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<ExpenseCategory> query = session.createQuery("FROM ExpenseCategory", ExpenseCategory.class);
            List<ExpenseCategory> categories = query.list();
            transaction.commit();
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void deleteAllExpensesOfUser(Long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<?> query = session.createQuery("DELETE FROM ExpenseCategory WHERE user_id = :userId");
            query.setParameter("userId", userId);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<ExpenseCategory> findAllExpenseCategoriesByUserId(Long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<ExpenseCategory> query = session.createQuery(
                    "SELECT ec FROM ExpenseCategory ec WHERE ec.user.id = :userId",
                    ExpenseCategory.class
            );
            query.setParameter("userId", userId);
            List<ExpenseCategory> categories = query.list();
            transaction.commit();
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<ExpenseCategory> findAllExpensesByCategoryId(Long categoryId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<ExpenseCategory> query = session.createQuery(
                    "SELECT ec FROM ExpenseCategory ec WHERE ec.id = :categoryId",
                    ExpenseCategory.class
            );
            query.setParameter("categoryId", categoryId);
            List<ExpenseCategory> categories = query.list();
            transaction.commit();
            return categories;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<ExpenseCategory> findSimilarExpensesByAmount(double amount) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM ExpenseCategory e WHERE e.expenseAmount = :amount";
            Query<ExpenseCategory> query = session.createQuery(hql, ExpenseCategory.class);
            query.setParameter("amount", amount);
            List<ExpenseCategory> expenses = query.getResultList();
            transaction.commit();
            return expenses;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<ExpenseCategory> sortExpensesByAmount() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<ExpenseCategory> query = session.createQuery(
                    "SELECT ec FROM ExpenseCategory ec ORDER BY ec.expenseAmount",
                    ExpenseCategory.class
            );
            List<ExpenseCategory> sortedExpenses = query.list();
            transaction.commit();
            return sortedExpenses;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<ExpenseCategory> sortExpensesByExpenseType() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<ExpenseCategory> query = session.createQuery(
                    "SELECT ec FROM ExpenseCategory ec ORDER BY ec.expenseType",
                    ExpenseCategory.class
            );
            List<ExpenseCategory> sortedExpenses = query.list();
            transaction.commit();
            return sortedExpenses;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public List<ExpenseCategory> findSimilarExpensesByExpenseType(String expenseType) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "FROM ExpenseCategory e WHERE e.expenseType = :expenseType";
            Query<ExpenseCategory> query = session.createQuery(hql, ExpenseCategory.class);
            query.setParameter("expenseType", expenseType);
            List<ExpenseCategory> expenses = query.getResultList();
            transaction.commit();
            return expenses;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
