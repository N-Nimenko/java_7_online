package module.service.impl;

import module.dao.AccountDao;
import module.dao.OperationDao;
import module.dao.impl.AccountDaoImpl;
import module.dao.impl.OperationDaoImpl;
import module.entity.Account;
import module.entity.Operation;
import module.service.OperationService;

import java.util.List;

public class OperationServiceImpl implements OperationService {

    OperationDao operationDao = new OperationDaoImpl();
    AccountDao accountDao = new AccountDaoImpl();

    @Override
    public void create(Operation entity) {
        operationDao.create(entity);
    }

    @Override
    public void update(Operation entity) {
        operationDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        operationDao.delete(id);
    }

    @Override
    public Operation findById(Long id) {
        return operationDao.findById(id);
    }

    @Override
    public List<Operation> findAll() {
        return operationDao.findAll();
    }

    @Override
    public void createOperation(int senderAccountNumber, int receiverAccountNumber, double amountToSend, String comment) {
        operationDao.createOperation(senderAccountNumber, receiverAccountNumber, amountToSend, comment);
    }

    @Override
    public void update(Account senderAccount) {
        accountDao.update(senderAccount);
    }
}
