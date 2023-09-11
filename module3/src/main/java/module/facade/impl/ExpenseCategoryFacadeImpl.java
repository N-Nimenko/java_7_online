package module.facade.impl;

import module.dto.ExpenseCategoryRequestDto;
import module.dto.ExpenseCategoryResponseDto;
import module.entity.ExpenseCategory;
import module.entity.User;
import module.facade.ExpenseCategoryFacade;
import module.service.ExpenseCategoryService;
import module.service.UserService;
import module.service.impl.ExpenseCategoryServiceImpl;
import module.service.impl.UserServiceImpl;

import java.util.List;
import java.util.ArrayList;

public class ExpenseCategoryFacadeImpl implements ExpenseCategoryFacade {

    private final ExpenseCategoryService expenseCategoryService = new ExpenseCategoryServiceImpl();
    private final UserService userService = new UserServiceImpl();

    @Override
    public void create(ExpenseCategoryRequestDto expenseCategoryRequestDto) {
        User user = userService.findById(expenseCategoryRequestDto.getUserId());
        if (user == null) {
            throw new RuntimeException("User with ID " + expenseCategoryRequestDto.getUserId() + " not found.");
        }

        ExpenseCategory expenseCategory = new ExpenseCategory();
        expenseCategory.setExpenseType(expenseCategoryRequestDto.expenseType());
        expenseCategory.setExpenseAmount(expenseCategoryRequestDto.expenseAmount());
        expenseCategory.setUser(user);

        expenseCategoryService.create(expenseCategory);
    }


    @Override
    public void update(ExpenseCategoryRequestDto expenseCategoryRequestDto, Long id) {
        ExpenseCategory expenseCategory = expenseCategoryService.findById(id);
        if (expenseCategory != null) {
            expenseCategory.setExpenseType(expenseCategoryRequestDto.expenseType());
            expenseCategory.setExpenseAmount(expenseCategoryRequestDto.expenseAmount());
            expenseCategoryService.update(expenseCategory);
        }
    }

    @Override
    public void delete(Long id) {
        expenseCategoryService.delete(id);
    }

    @Override
    public ExpenseCategoryResponseDto findById(Long id) {
        ExpenseCategory expenseCategory = expenseCategoryService.findById(id);
        if (expenseCategory != null) {
            return new ExpenseCategoryResponseDto(
                    expenseCategory.getId(),
                    expenseCategory.getExpenseType(),
                    expenseCategory.getExpenseAmount()
            );
        }
        return null;
    }

    @Override
    public List<ExpenseCategoryResponseDto> findAll() {
        List<ExpenseCategory> expenseCategories = expenseCategoryService.findAll();
        List<ExpenseCategoryResponseDto> responseDtos = new ArrayList<>();
        for (ExpenseCategory expenseCategory : expenseCategories) {
            responseDtos.add(new ExpenseCategoryResponseDto(
                    expenseCategory.getId(),
                    expenseCategory.getExpenseType(),
                    expenseCategory.getExpenseAmount()
            ));
        }
        return responseDtos;
    }
}
