package module.service.impl;

import module.dao.IncomeCategoryDao;
import module.dao.impl.IncomeCategoryDaoImpl;
import module.entity.IncomeCategory;
import module.service.IncomeCategoryService;

import java.util.List;

public class IncomeCategoryServiceImpl implements IncomeCategoryService {

    IncomeCategoryDao incomeCategoryDao = new IncomeCategoryDaoImpl();

    @Override
    public void create(IncomeCategory entity) {
        incomeCategoryDao.create(entity);
    }

    @Override
    public void update(IncomeCategory entity) {
        incomeCategoryDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        incomeCategoryDao.delete(id);
    }

    @Override
    public IncomeCategory findById(Long id) {
        return incomeCategoryDao.findById(id);
    }

    @Override
    public List<IncomeCategory> findAll() {
        return incomeCategoryDao.findAll();
    }

    @Override
    public List<IncomeCategory> findAllIncomeCategoriesByUserId(Long userId) {
        return incomeCategoryDao.findAllIncomeCategoriesByUserId(userId);
    }

    @Override
    public void deleteAllIncomeCategoriesByUserId(Long userId) {
        incomeCategoryDao.deleteAllIncomeCategoriesByUserId(userId);
    }

    @Override
    public List<IncomeCategory> sortIncomesByAmount() {
        return incomeCategoryDao.sortIncomesByAmount();
    }

    @Override
    public List<IncomeCategory> sortIncomesByDate() {
        return incomeCategoryDao.sortIncomesByDate();
    }

    @Override
    public List<IncomeCategory> sortIncomesByIncomeType() {
        return incomeCategoryDao.sortIncomesByIncomeType();
    }

    @Override
    public List<IncomeCategory> findSimilarIncomesByAmount(double targetAmount) {
        return incomeCategoryDao.findSimilarIncomesByAmount(targetAmount);
    }

    @Override
    public List<IncomeCategory> findSimilarIncomesByIncomeType(String targetIncomeType) {
        return incomeCategoryDao.findSimilarIncomesByIncomeType(targetIncomeType);
    }
}
