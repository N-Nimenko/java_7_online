package module.service;

import module.entity.IncomeCategory;

import java.util.List;

public interface IncomeCategoryService extends CrudService<IncomeCategory>{
    List<IncomeCategory> findAllIncomeCategoriesByUserId(Long userId);
    void deleteAllIncomeCategoriesByUserId(Long userId);
    List<IncomeCategory> sortIncomesByAmount();
    List<IncomeCategory> sortIncomesByDate();
    List<IncomeCategory> sortIncomesByIncomeType();
    List<IncomeCategory> findSimilarIncomesByAmount(double targetAmount);
    List<IncomeCategory> findSimilarIncomesByIncomeType(String targetIncomeType);
}
