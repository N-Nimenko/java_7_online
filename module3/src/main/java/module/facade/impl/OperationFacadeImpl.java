package module.facade.impl;

import module.dto.OperationRequestDto;
import module.dto.OperationResponseDto;
import module.entity.Operation;
import module.facade.OperationFacade;
import module.service.OperationService;
import module.service.impl.OperationServiceImpl;

import java.util.List;
import java.util.ArrayList;

public class OperationFacadeImpl implements OperationFacade {

    private final OperationService operationService = new OperationServiceImpl();

    @Override
    public void create(OperationRequestDto operationRequestDto) {
        Operation operation = new Operation();
        operation.setComment(operationRequestDto.comment());
        operation.setAmount(operationRequestDto.amount());
        operationService.create(operation);
    }

    @Override
    public void update(OperationRequestDto operationRequestDto, Long id) {
        Operation operation = operationService.findById(id);
        if (operation != null) {
            operation.setComment(operationRequestDto.comment());
            operation.setAmount(operationRequestDto.amount());
            operationService.update(operation);
        }
    }

    @Override
    public void delete(Long id) {
        operationService.delete(id);
    }

    @Override
    public OperationResponseDto findById(Long id) {
        Operation operation = operationService.findById(id);
        if (operation != null) {
            return new OperationResponseDto(
                    operation.getId(),
                    operation.getComment(),
                    operation.getAmount(),
                    operation.getReceiverAccount(),
                    operation.getSenderAccount()
            );
        }
        return null;
    }

    @Override
    public List<OperationResponseDto> findAll() {
        List<Operation> operations = operationService.findAll();
        List<OperationResponseDto> responseDtos = new ArrayList<>();
        for (Operation operation : operations) {
            responseDtos.add(new OperationResponseDto(
                    operation.getId(),
                    operation.getComment(),
                    operation.getAmount(),
                    operation.getReceiverAccount(),
                    operation.getSenderAccount()
            ));
        }
        return responseDtos;
    }
}
