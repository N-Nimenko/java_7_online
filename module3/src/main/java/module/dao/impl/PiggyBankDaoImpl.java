package module.dao.impl;

import module.config.HibernateConfig;
import module.dao.PiggyBankDao;
import module.entity.Account;
import module.entity.PiggyBank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class PiggyBankDaoImpl implements PiggyBankDao {
    private final SessionFactory sessionFactory = HibernateConfig.getInstance().getSessionFactory();

    @Override
    public void create(PiggyBank entity) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(PiggyBank entity) {
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
            PiggyBank piggyBank = session.get(PiggyBank.class, id);
            if (piggyBank != null) {
                session.delete(piggyBank);
            }
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PiggyBank findById(Long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            PiggyBank piggyBank = session.get(PiggyBank.class, id);
            transaction.commit();
            return piggyBank;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<PiggyBank> findAll() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            Query<PiggyBank> query = session.createQuery("FROM PiggyBank", PiggyBank.class);
            List<PiggyBank> piggyBanks = query.list();
            transaction.commit();
            return piggyBanks;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void sendMoneyToAccount(PiggyBank piggyBank, Account account, double amount) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            PiggyBank persistentPiggyBank = session.get(PiggyBank.class, piggyBank.getId());
            Account persistentAccount = session.get(Account.class, account.getId());

            if (persistentPiggyBank != null && persistentAccount != null) {
                double currentPiggyBankBalance = persistentPiggyBank.getBalance();
                if (currentPiggyBankBalance >= amount) {
                    persistentPiggyBank.setBalance(currentPiggyBankBalance - amount);
                    double currentAccountBalance = persistentAccount.getBalance();
                    persistentAccount.setBalance(currentAccountBalance + amount);
                    session.update(persistentPiggyBank);
                    session.update(persistentAccount);
                } else {
                    throw new RuntimeException("Not enough funds in piggy bank for the transfer.");
                }
            }

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPiggyBankToAccount(Account existingAccount, PiggyBank existingPiggyBank) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Account persistentAccount = session.get(Account.class, existingAccount.getId());

            if (persistentAccount != null) {
                Set<PiggyBank> piggyBanks = persistentAccount.getPiggyBanks();
                piggyBanks.add(existingPiggyBank);
                session.update(persistentAccount);
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
    public void sendMoneyToPiggyBank(Account account, PiggyBank piggyBank, double amount) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Account persistentAccount = session.get(Account.class, account.getId());

            if (persistentAccount != null) {
                if (persistentAccount.getBalance() >= amount) {
                    double currentBalance = persistentAccount.getBalance();
                    persistentAccount.setBalance(currentBalance - amount);

                    PiggyBank persistentPiggyBank = session.get(PiggyBank.class, piggyBank.getId());
                    if (persistentPiggyBank != null) {
                        double piggyBankBalance = persistentPiggyBank.getBalance();
                        persistentPiggyBank.setBalance(piggyBankBalance + amount);

                        session.update(persistentAccount);
                        session.update(persistentPiggyBank);
                    }
                } else {
                    throw new RuntimeException("Insufficient funds in the account.");
                }
            } else {
                throw new RuntimeException("Account with ID " + account.getId() + " not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
