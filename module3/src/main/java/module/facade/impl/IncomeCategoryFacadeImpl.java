package module.facade.impl;

import module.dto.IncomeCategoryRequestDto;
import module.dto.IncomeCategoryResponseDto;
import module.entity.IncomeCategory;
import module.entity.User;
import module.facade.IncomeCategoryFacade;
import module.service.IncomeCategoryService;
import module.service.UserService;
import module.service.impl.IncomeCategoryServiceImpl;
import module.service.impl.UserServiceImpl;

import java.util.List;
import java.util.ArrayList;

public class IncomeCategoryFacadeImpl implements IncomeCategoryFacade {
    private final IncomeCategoryService incomeCategoryService = new IncomeCategoryServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    public void create(IncomeCategoryRequestDto incomeCategoryRequestDto) {
        User user = userService.findById(incomeCategoryRequestDto.getUserId());
        if (user == null) {
            throw new RuntimeException("User with ID " + incomeCategoryRequestDto.getUserId() + " not found.");
        }


        IncomeCategory incomeCategory = new IncomeCategory();
        incomeCategory.setIncomeType(incomeCategoryRequestDto.incomeType());
        incomeCategory.setIncomeAmount(incomeCategoryRequestDto.incomeAmount());
        incomeCategory.setUser(user);

        incomeCategoryService.create(incomeCategory);
    }

    @Override
    public void update(IncomeCategoryRequestDto incomeCategoryRequestDto, Long id) {
        IncomeCategory incomeCategory = incomeCategoryService.findById(id);
        if (incomeCategory != null) {
            incomeCategory.setIncomeType(incomeCategoryRequestDto.incomeType());
            incomeCategory.setIncomeAmount(incomeCategoryRequestDto.incomeAmount());
            incomeCategoryService.update(incomeCategory);
        }
    }

    @Override
    public void delete(Long id) {
        incomeCategoryService.delete(id);
    }

    @Override
    public IncomeCategoryResponseDto findById(Long id) {
        IncomeCategory incomeCategory = incomeCategoryService.findById(id);
        if (incomeCategory != null) {
            return new IncomeCategoryResponseDto(
                    incomeCategory.getId(),
                    incomeCategory.getIncomeType(),
                    incomeCategory.getIncomeAmount()
            );
        }
        return null;
    }

    @Override
    public List<IncomeCategoryResponseDto> findAll() {
        List<IncomeCategory> incomeCategories = incomeCategoryService.findAll();
        List<IncomeCategoryResponseDto> responseDtos = new ArrayList<>();
        for (IncomeCategory incomeCategory : incomeCategories) {
            responseDtos.add(new IncomeCategoryResponseDto(
                    incomeCategory.getId(),
                    incomeCategory.getIncomeType(),
                    incomeCategory.getIncomeAmount()
            ));
        }
        return responseDtos;
    }
}
