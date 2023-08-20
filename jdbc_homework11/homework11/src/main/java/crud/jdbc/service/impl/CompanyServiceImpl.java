package crud.jdbc.service.impl;

import crud.jdbc.dao.CompanyDao;
import crud.jdbc.entity.Company;
import crud.jdbc.service.CompanyService;
import framework.annotations.BeanClass;
import framework.annotations.InjectBean;

import java.util.List;

@BeanClass
public class CompanyServiceImpl implements CompanyService {
    @InjectBean
    private CompanyDao companyDao;

    @Override
    public void create(Company entity) {
        companyDao.create(entity);
    }

    @Override
    public void update(Company entity) {
        companyDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        companyDao.delete(id);
    }

    @Override
    public Company findById(Long id) {
        return companyDao.findById(id);
    }

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }
}
