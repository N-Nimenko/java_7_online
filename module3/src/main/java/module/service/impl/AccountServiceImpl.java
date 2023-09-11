package module.service.impl;

import module.dao.AccountDao;
import module.dao.impl.AccountDaoImpl;
import module.entity.Account;
import module.entity.User;
import module.service.AccountService;

import java.util.List;

public class AccountServiceImpl implements AccountService {

    AccountDao accountDao = new AccountDaoImpl();

    @Override
    public void create(Account entity) {
        accountDao.create(entity);
    }

    @Override
    public void update(Account entity) {
        accountDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        accountDao.delete(id);
    }

    @Override
    public Account findById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public void addAccountToUser(User existingUser, Account existingAccount) {
        accountDao.addAccountToUser(existingUser, existingAccount);
    }

    @Override
    public int generateRandomAccountNumber() {
        return accountDao.generateRandomAccountNumber();
    }

    @Override
    public Account findByAccountNumberController(int accountNumber) {
        return accountDao.findByAccountNumberController(accountNumber);
    }

    @Override
    public boolean isAccountNumberUnique(int accountNumber) {
        return accountDao.isAccountNumberUnique(accountNumber);
    }

    @Override
    public List<Account> getAccountsByUserId(Long userId) {
        return accountDao.getAccountsByUserId(userId);
    }
}
