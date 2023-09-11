package module.service;

import module.entity.Account;
import module.entity.Operation;

import java.util.List;

public interface OperationService extends CrudService<Operation>{
    void createOperation(int senderAccountNumber, int receiverAccountNumber, double amountToSend, String comment);
    void update(Account senderAccount);
}
