package module.dao;

import module.entity.Account;
import module.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface AccountDao extends CrudDao<Account>{
    void addAccountToUser(User existingUser, Account existingAccount);
    List<Account> getAccountsByUserId(Long userId);
    boolean isAccountNumberUnique(int accountNumber);
    int generateRandomAccountNumber();
    Account findByAccountNumber(Session session, int accountNumber);
    public Account findByAccountNumberController(int accountNumber);
}
