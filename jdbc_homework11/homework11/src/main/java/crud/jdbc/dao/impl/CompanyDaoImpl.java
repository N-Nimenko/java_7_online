package crud.jdbc.dao.impl;

import crud.jdbc.config.JdbcConfig;
import crud.jdbc.dao.CompanyDao;
import crud.jdbc.entity.Company;
import framework.annotations.BeanClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@BeanClass
public class CompanyDaoImpl implements CompanyDao {
    private final JdbcConfig config = JdbcConfig.getInstance();

    private static final String COMPANY_CREATE_QUERY = "insert into company values (default, ?, ?, ?)";
    private static final String COMPANY_UPDATE_QUERY = "update company set name = ?, employeeQuantity = ?, companyPrice = ? where id = ?";
    private static final String COMPANY_DELETE_QUERY = "delete from company where id = ?";
    private static final String COMPANY_FIND_BY_ID_QUERY = "select * from company where id = ";
    private static final String COMPANY_FIND_ALL_QUERY = "select * from company";

    @Override
    public void create(Company entity) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(COMPANY_CREATE_QUERY)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCompanyPrice());
            ps.setInt(3, entity.getEmployeeQuantity());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
    }
    @Override
    public void update(Company entity) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(COMPANY_UPDATE_QUERY)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCompanyPrice());
            ps.setInt(3, entity.getEmployeeQuantity());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(COMPANY_DELETE_QUERY)){
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public Company findById(Long id) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(COMPANY_FIND_BY_ID_QUERY + "?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return buildCompanyByResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
        return null;
    }

        @Override
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();
        try(ResultSet rs = config.getStatement().executeQuery(COMPANY_FIND_ALL_QUERY)) {
            while (rs.next()){
                companies.add(buildCompanyByResultSet(rs));
            }
            return companies;
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
        return companies;
    }

    private Company buildCompanyByResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("Id");
        String name = rs.getString("name");
        Integer employeeQuantity = rs.getInt("employeeQuantity");
        Integer companyPrice = rs.getInt("companyPrice");

        Company company = new Company();
        company.setId(id != 0 ? Long.valueOf(String.valueOf(id)) : null);
        company.setName(name);
        company.setEmployeeQuantity(employeeQuantity);
        company.setCompanyPrice(companyPrice);

        return company;
    }
}
