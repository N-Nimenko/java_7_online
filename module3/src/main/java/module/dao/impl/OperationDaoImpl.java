package module.dao.impl;

import module.config.HibernateConfig;
import module.dao.AccountDao;
import module.dao.OperationDao;
import module.entity.Account;
import module.entity.Operation;
import module.service.OperationService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OperationDaoImpl implements OperationDao {
    AccountDao accountDao = new AccountDaoImpl();
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(Operation entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.getCurrentSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("e = " + e);
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Operation entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Operation operation = session.get(Operation.class, id);
            if (operation != null) {
                session.delete(operation);
                transaction.commit();
            } else {
                System.out.println("Operation with ID " + id + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public Operation findById(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Operation operation = session.get(Operation.class, id);
            transaction.commit();
            return operation;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Operation> findAll() {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Query<Operation> query = session.createQuery("FROM Operation", Operation.class);
            List<Operation> operations = query.getResultList();
            transaction.commit();
            return operations;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
    @Override
    public void createOperation(int senderAccountNumber, int receiverAccountNumber, double amountToSend, String comment) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Account senderAccount = accountDao.findByAccountNumber(session, senderAccountNumber);
            Account receiverAccount = accountDao.findByAccountNumber(session, receiverAccountNumber);

            if (senderAccount == null) {
                throw new RuntimeException("Sender account not found");
            }

                if (receiverAccount == null) {
                throw new RuntimeException("Receiver account not found");
            }

            Operation operation = new Operation();
            operation.setComment(comment);
            operation.setAmount(amountToSend);
            operation.setSenderAccount(senderAccount);
            operation.setReceiverAccount(receiverAccount);

            operation.setDate(new Date());

            senderAccount.setBalance(senderAccount.getBalance() - amountToSend);
            receiverAccount.setBalance(receiverAccount.getBalance() + amountToSend);

            session.save(operation);
            session.update(senderAccount);
            session.update(receiverAccount);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


