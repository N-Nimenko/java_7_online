package crud.jdbc.service;

import crud.jdbc.entity.Company;
import crud.jdbc.entity.Director;
import crud.jdbc.entity.DirectorOfCompany;

public interface DirectorOfCompanyService {
    void create(Long directorId, Long companyId);
    void update(Long directorId, Long companyId);
    void deleteDirectorFromCompany(Long directorId, Long companyId);
    void deleteDirectorAndCompany(Long directorId, Long companyId);
    Company[] findAllCompaniesByDirectorId(Long directorId);
    Director[] findAllDirectorsByCompanyId(Long companyId);
}
