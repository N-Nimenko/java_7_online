package module.dao;

import module.entity.Account;
import module.entity.PiggyBank;

public interface PiggyBankDao extends CrudDao<PiggyBank>{
    void sendMoneyToAccount(PiggyBank piggyBank, Account account, double amount);
    void addPiggyBankToAccount(Account existingAccount, PiggyBank existingPiggyBank);
    void sendMoneyToPiggyBank(Account account, PiggyBank piggyBank, double amount);
}
