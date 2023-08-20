package crud.jdbc.dao;

import crud.jdbc.entity.DirectorOfCompany;

import java.util.List;

public interface DirectorOfCompanyDao {

    void create(Long directorId, Long companyId);

    void update(Long directorId, Long companyId);

    void deleteDirectorFromCompany(Long directorId, Long companyId);

    void deleteDirectorAndCompany(Long directorId, Long companyId);

    List<DirectorOfCompany> getAllDirectorsFromCompany(Long id);

    List<DirectorOfCompany> getAllCompaniesFromDirector(Long id);

}
