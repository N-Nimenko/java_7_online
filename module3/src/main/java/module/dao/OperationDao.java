package module.dao;

import module.entity.Operation;
import module.service.OperationService;

import java.util.List;

public interface OperationDao extends CrudDao<Operation>{
    void createOperation(int senderAccountNumber, int receiverAccountNumber, double amountToSend, String comment);
}
