package module.service;

import module.entity.User;

public interface UserService extends CrudService<User>{
    Long getAccountIdByUserId(Long userId);
    Long getUserIdByAccountNumber(int accountNumber);
    void deleteUserAccountRelation(Long userId, Long accountId);
}
