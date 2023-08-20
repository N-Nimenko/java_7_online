package crud.jdbc.dao.impl;

import crud.jdbc.config.JdbcConfig;
import crud.jdbc.dao.DirectorDao;
import crud.jdbc.entity.Director;
import framework.annotations.BeanClass;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@BeanClass
public class DirectorDaoImpl implements DirectorDao {
    private final JdbcConfig config = JdbcConfig.getInstance();

    private static final String DIRECTOR_CREATE_QUERY = "insert into director values (default, ?, ?, ?)";
    private static final String DIRECTOR_UPDATE_QUERY = "update director set first_name = ?, last_name = ?, age = ? where id = ?";
    private static final String DIRECTOR_DELETE_QUERY = "delete from director where id = ?";
    private static final String DIRECTOR_FIND_BY_ID_QUERY = "select * from director where id = ";
    private static final String DIRECTOR_FIND_ALL_QUERY = "select * from director";

    @Override
    public void create(Director entity) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(DIRECTOR_CREATE_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getAge());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public void update(Director entity) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(DIRECTOR_UPDATE_QUERY)) {
            ps.setString(1, entity.getFirstName());
            ps.setString(2, entity.getLastName());
            ps.setInt(3, entity.getAge());
            ps.setLong(4, entity.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement ps = config.getConnection().prepareStatement(DIRECTOR_DELETE_QUERY)){
            ps.setLong(1, id);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("An exception happened " + e);
        }
    }

    @Override
    public Director findById(Long id) {
        try (PreparedStatement ps = config.getConnection().prepareStatement(DIRECTOR_FIND_BY_ID_QUERY + "?")) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return buildDirectorByResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
        return null;
    }

    @Override
    public List<Director> findAll() {
        List<Director> directors = new ArrayList<>();
        try(ResultSet rs = config.getStatement().executeQuery(DIRECTOR_FIND_ALL_QUERY)) {
            while (rs.next()){
                directors.add(buildDirectorByResultSet(rs));
            }
            return directors;
        } catch (SQLException e) {
            System.out.println("An exception happened " + e);
        }
        return directors;
    }

    private Director buildDirectorByResultSet(ResultSet rs) throws SQLException {
        Long id = rs.getLong("Id");
        String firstName = rs.getString("first_Name");
        String lastName = rs.getString("last_Name");
        Integer age = rs.getInt("age");

        Director director = new Director();
        director.setId(id != 0 ? Long.valueOf(String.valueOf(id)) : null);
        director.setFirstName(firstName);
        director.setLastName(lastName);
        director.setAge(age);

        return director;
    }
}
