package crud.jdbc.service.impl;

import crud.jdbc.dao.CompanyDao;
import crud.jdbc.dao.DirectorDao;
import crud.jdbc.dao.DirectorOfCompanyDao;
import crud.jdbc.entity.Company;
import crud.jdbc.entity.Director;
import crud.jdbc.entity.DirectorOfCompany;
import crud.jdbc.service.DirectorOfCompanyService;
import framework.annotations.BeanClass;
import framework.annotations.InjectBean;


@BeanClass
public class DirectorOfCompanyServiceImpl implements DirectorOfCompanyService {
    @InjectBean
    private DirectorDao directorDao;
    @InjectBean
    private CompanyDao companyDao;
    @InjectBean
    private DirectorOfCompanyDao directorOfCompanyDao;

    @Override
    public void create(Long directorId, Long companyId) {
        DirectorOfCompany directorOfCompany = new DirectorOfCompany();
        directorOfCompany.setDirectorId(directorId);
        directorOfCompany.setCompanyId(companyId);
        directorOfCompanyDao.create(directorId, companyId);
    }

    @Override
    public void update(Long directorId, Long companyId) {
        directorOfCompanyDao.update(directorId, companyId);
    }

    @Override
    public void deleteDirectorFromCompany(Long directorId, Long companyId) {
        directorOfCompanyDao.deleteDirectorFromCompany(directorId, companyId);
    }

    @Override
    public void deleteDirectorAndCompany(Long directorId, Long companyId) {
        directorOfCompanyDao.deleteDirectorAndCompany(directorId, companyId);
    }

    @Override
    public Director[] findAllDirectorsByCompanyId(Long id) {
        var companyDirectors = directorOfCompanyDao.getAllDirectorsFromCompany(id);
        Director[] result = new Director[companyDirectors.size()];
        for (int i = 0; i < result.length; i++) {
            Director found = directorDao.findById(companyDirectors.get(i).getDirectorId());
            result[i] = found;
        }
        return result;
    }

    @Override
    public Company[] findAllCompaniesByDirectorId(Long id) {
        var companyDirectors = directorOfCompanyDao.getAllCompaniesFromDirector(id);
        Company[] result = new Company[companyDirectors.size()];
        for (int i = 0; i < result.length; i++) {
            Company found = companyDao.findById(companyDirectors.get(i).getCompanyId());
            result[i] = found;
        }
        return result;
    }

}
