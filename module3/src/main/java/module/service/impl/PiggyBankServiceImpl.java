package module.service.impl;

import module.dao.PiggyBankDao;
import module.dao.impl.PiggyBankDaoImpl;
import module.entity.Account;
import module.entity.PiggyBank;
import module.service.PiggyBankService;

import java.util.List;

public class PiggyBankServiceImpl implements PiggyBankService {

    PiggyBankDao piggyBankDao = new PiggyBankDaoImpl();

    @Override
    public void create(PiggyBank entity) {
        piggyBankDao.create(entity);
    }

    @Override
    public void update(PiggyBank entity) {
        piggyBankDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        piggyBankDao.delete(id);
    }

    @Override
    public PiggyBank findById(Long id) {
        return piggyBankDao.findById(id);
    }

    @Override
    public List<PiggyBank> findAll() {
        return piggyBankDao.findAll();
    }

    @Override
    public void sendMoneyToAccount(PiggyBank piggyBank, Account account, double amount) {
        piggyBankDao.sendMoneyToAccount(piggyBank, account, amount);
    }

    @Override
    public void addPiggyBankToAccount(Account existingAccount, PiggyBank existingPiggyBank) {
        piggyBankDao.addPiggyBankToAccount(existingAccount, existingPiggyBank);
    }

    @Override
    public void sendMoneyToPiggyBank(Account account, PiggyBank piggyBank, double amount) {
        piggyBankDao.sendMoneyToPiggyBank(account, piggyBank, amount);
    }
}
