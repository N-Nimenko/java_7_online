package module.service.impl;

import module.dao.ExpenseCategoryDao;
import module.dao.impl.ExpenseCategoryDaoImpl;
import module.entity.ExpenseCategory;
import module.service.ExpenseCategoryService;

import java.util.List;

public class ExpenseCategoryServiceImpl implements ExpenseCategoryService {

    ExpenseCategoryDao expenseCategoryDao = new ExpenseCategoryDaoImpl();

    @Override
    public void create(ExpenseCategory entity) {
        expenseCategoryDao.create(entity);
    }

    @Override
    public void update(ExpenseCategory entity) {
        expenseCategoryDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        expenseCategoryDao.delete(id);
    }

    @Override
    public ExpenseCategory findById(Long id) {
        return expenseCategoryDao.findById(id);
    }

    @Override
    public List<ExpenseCategory> findAll() {
        return expenseCategoryDao.findAll();
    }

    @Override
    public void deleteAllExpensesOfUser(Long userId) {
        expenseCategoryDao.deleteAllExpensesOfUser(userId);
    }

    @Override
    public List<ExpenseCategory> findAllExpenseCategoriesByUserId(Long userId) {
        return expenseCategoryDao.findAllExpenseCategoriesByUserId(userId);
    }

    @Override
    public List<ExpenseCategory> findAllExpensesByCategoryId(Long categoryId) {
        return expenseCategoryDao.findAllExpensesByCategoryId(categoryId);
    }

    @Override
    public List<ExpenseCategory> findSimilarExpensesByAmount(double amount) {
        return expenseCategoryDao.findSimilarExpensesByAmount(amount);
    }

    @Override
    public List<ExpenseCategory> sortExpensesByAmount() {
        return expenseCategoryDao.sortExpensesByAmount();
    }

    @Override
    public List<ExpenseCategory> sortExpensesByExpenseType() {
        return expenseCategoryDao.sortExpensesByExpenseType();
    }

    @Override
    public List<ExpenseCategory> findSimilarExpensesByExpenseType(String expenseType) {
        return expenseCategoryDao.findSimilarExpensesByExpenseType(expenseType);
    }
}
