package module.service;

import module.entity.ExpenseCategory;

import java.util.List;

public interface ExpenseCategoryService extends CrudService<ExpenseCategory> {
    void deleteAllExpensesOfUser(Long userId);
    List<ExpenseCategory> findAllExpenseCategoriesByUserId(Long userId);
    List<ExpenseCategory> findAllExpensesByCategoryId(Long categoryId);
    List<ExpenseCategory> findSimilarExpensesByAmount(double amount);
    List<ExpenseCategory> sortExpensesByAmount();
    List<ExpenseCategory> sortExpensesByExpenseType();
    List<ExpenseCategory> findSimilarExpensesByExpenseType(String expenseType);
}
