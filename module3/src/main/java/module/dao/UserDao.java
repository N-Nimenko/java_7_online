package module.dao;

import module.entity.User;

public interface UserDao extends CrudDao<User>{
    Long getAccountIdByUserId(Long userId);
    Long getUserIdByAccountNumber(int accountNumber);
    void deleteUserAccountRelation(Long userId, Long accountId);
}
