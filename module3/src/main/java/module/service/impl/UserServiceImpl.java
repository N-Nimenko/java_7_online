package module.service.impl;

import module.dao.UserDao;
import module.dao.impl.UserDaoImpl;
import module.entity.User;
import module.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoImpl();

    @Override
    public void create(User entity) {
        userDao.create(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Long getAccountIdByUserId(Long userId) {
        return userDao.getAccountIdByUserId(userId);
    }

    @Override
    public Long getUserIdByAccountNumber(int accountNumber) {
        return userDao.getUserIdByAccountNumber(accountNumber);
    }

    @Override
    public void deleteUserAccountRelation(Long userId, Long accountId) {
        userDao.deleteUserAccountRelation(userId, accountId);
    }
}
