package module.dao.impl;

import module.config.HibernateConfig;
import module.dao.AccountDao;
import module.entity.Account;
import module.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class AccountDaoImpl implements AccountDao{

    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Account entity) {
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
    public void update(Account entity) {
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
            Account accountToDelete = session.get(Account.class, id);
            if (accountToDelete != null) {
                session.delete(accountToDelete);
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
    public Account findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Account account = session.get(Account.class, id);
            return account;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return null;
        }
    }

    @Override
    public List<Account> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Query<Account> query = session.createQuery("FROM Account", Account.class);
            List<Account> accounts = query.getResultList();
            return accounts;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return Collections.emptyList();
        }
    }

    @Override
    public void addAccountToUser(User existingUser, Account existingAccount) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Set<Account> accounts = existingUser.getAccounts();
            accounts.add(existingAccount);
            session.update(existingUser);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Account> query = session.createQuery("SELECT a FROM Account a JOIN FETCH a.users u WHERE u.id = :userId", Account.class);
            query.setParameter("userId", userId);
            List<Account> accounts = query.list();
            transaction.commit();
            return accounts;
        } catch (Exception e) {
            System.out.println("There is a problem: " + e);
            return Collections.emptyList();
        }
    }

    @Override
    public int generateRandomAccountNumber() {
        Random random = new Random();
        int accountNumber;
        do {
            accountNumber = 100_000 + random.nextInt(900_000);
        } while (!isAccountNumberUnique(accountNumber));
        return accountNumber;
    }

    @Override
    public boolean isAccountNumberUnique(int accountNumber) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();

            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Account WHERE accountNumber = :accountNumber", Long.class);
            query.setParameter("accountNumber", accountNumber);

            Long count = query.uniqueResult();

            session.getTransaction().commit();

            return count == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Account findByAccountNumber(Session session, int accountNumber) {
        Query<Account> query = session.createQuery("FROM Account WHERE accountNumber = :accountNumber", Account.class);
        query.setParameter("accountNumber", accountNumber);

        return query.uniqueResult();
    }

    @Override
    public Account findByAccountNumberController(int accountNumber) {
        Transaction transaction = null;

        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Query<Account> query = session.createQuery("FROM Account WHERE accountNumber = :accountNumber", Account.class);
            query.setParameter("accountNumber", accountNumber);

            Account account = query.uniqueResult();

            transaction.commit();

            return account;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
    }
}
