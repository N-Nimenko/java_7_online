package module.service;

import module.entity.Account;
import module.entity.User;
import org.hibernate.Session;

import java.util.List;

public interface AccountService extends CrudService<Account>{
    void addAccountToUser(User existingUser, Account existingAccount);
    List<Account> getAccountsByUserId(Long userId);
    public boolean isAccountNumberUnique(int accountNumber);
    public int generateRandomAccountNumber();
    public Account findByAccountNumberController(int accountNumber);
}
