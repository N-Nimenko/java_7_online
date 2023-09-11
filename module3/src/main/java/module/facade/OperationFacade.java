package module.facade;

import module.dto.OperationRequestDto;
import module.dto.OperationResponseDto;
import module.entity.Operation;

import java.util.List;

public interface OperationFacade extends CrudFacade<OperationRequestDto, OperationResponseDto>{
}
