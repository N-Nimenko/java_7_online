package crud.jdbc.dao.impl;

import crud.jdbc.config.JdbcConfig;
import crud.jdbc.dao.DirectorOfCompanyDao;
import crud.jdbc.entity.DirectorOfCompany;
import framework.annotations.BeanClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@BeanClass
public class DirectorOfCompanyDaoImpl implements DirectorOfCompanyDao {

    private final JdbcConfig config = JdbcConfig.getInstance();

    private static final String DIRECTOR_OF_COMPANY_CREATE_QUERY = "insert into DirectorOfCompany (director_id, company_id) values (?, ?)";
    private static final String DIRECTOR_OF_COMPANY_UPDATE_QUERY = "update DirectorOfCompany set director_id = ? where company_id = ?";
    private static final String DELETE_DIRECTOR_FROM_COMPANY_QUERY = "delete from DirectorOfCompany where director_id = ? and company_id = ?";
    private static final String DELETE_DIRECTOR_AND_COMPANY_QUERY = "delete from DirectorOfCompany where director_id = ? or company_id = ?";
    private static final String GET_ALL_DIRECTORS_FROM_COMPANY_QUERY = "select * from DirectorOfCompany where company_id = ?";
    private static final String GET_ALL_COMPANIES_FROM_DIRECTOR_QUERY = "select * from DirectorOfCompany where director_id = ?";

    @Override
    public void create(Long directorId, Long companyId) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(DIRECTOR_OF_COMPANY_CREATE_QUERY)) {
            ps.setLong(1, directorId);
            ps.setLong(2, companyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public void update(Long directorId, Long companyId) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(DIRECTOR_OF_COMPANY_UPDATE_QUERY)) {
            ps.setLong(1, directorId);
            ps.setLong(2, companyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public void deleteDirectorFromCompany(Long directorId, Long companyId) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(DELETE_DIRECTOR_FROM_COMPANY_QUERY)) {
            ps.setLong(1, directorId);
            ps.setLong(2, companyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public void deleteDirectorAndCompany(Long directorId, Long companyId) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(DELETE_DIRECTOR_AND_COMPANY_QUERY)) {
            ps.setLong(1, directorId);
            ps.setLong(2, companyId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public List<DirectorOfCompany> getAllDirectorsFromCompany(Long companyId) {
        List<DirectorOfCompany> directors = new ArrayList<>();
        try (PreparedStatement ps = config.getConnection().prepareStatement(GET_ALL_DIRECTORS_FROM_COMPANY_QUERY)) {
            ps.setLong(1, companyId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DirectorOfCompany directorOfCompany = buildDirectorOfCompanyByResultSet(rs);
                    directors.add(directorOfCompany);
                }
            }
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
        return directors;
    }

    @Override
    public List<DirectorOfCompany> getAllCompaniesFromDirector(Long directorId) {
        List<DirectorOfCompany> companies = new ArrayList<>();
        try (PreparedStatement ps = config.getConnection().prepareStatement(GET_ALL_COMPANIES_FROM_DIRECTOR_QUERY)) {
            ps.setLong(1, directorId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DirectorOfCompany directorOfCompany = buildDirectorOfCompanyByResultSet(rs);
                    companies.add(directorOfCompany);
                }
            }
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
        return companies;
    }

    private DirectorOfCompany buildDirectorOfCompanyByResultSet(ResultSet rs) throws SQLException {
        DirectorOfCompany directorOfCompany = new DirectorOfCompany();
        directorOfCompany.setDirectorId(rs.getLong("Director_id"));
        directorOfCompany.setCompanyId(rs.getLong("Company_id"));
        return directorOfCompany;
    }
}
