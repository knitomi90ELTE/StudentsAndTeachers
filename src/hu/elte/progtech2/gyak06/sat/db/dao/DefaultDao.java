package hu.elte.progtech2.gyak06.sat.db.dao;

import hu.elte.progtech2.gyak06.sat.db.entity.PersistentEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class DefaultDao<T extends PersistentEntity> implements GenericDao<T> {

    protected static final String DATABASE_URL = "jdbc:derby://localhost:1527/StudentsAndTeachers2";
    protected Connection connection;
    protected Properties properties;

    public DefaultDao() {
        properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "password");
    }

    @Override
    public List<T> findAll() {
        List<T> items = new ArrayList<>();
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            PreparedStatement statement = connection.prepareStatement(getFindAllSql());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                items.add(fromResultSet(resultSet));
            }
            close(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return items;
        }
    }

    @Override
    public T findById(Integer id) {
        T item = null;
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            PreparedStatement statement = connection.prepareStatement(getFindByIdSql());
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                item = fromResultSet(resultSet);
            }
            close(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            return item;
        }
    }

    @Override
    public void create(T entity) {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            PreparedStatement statement = (PreparedStatement) fromEntity(getInsertSql(), entity, false);
            statement.executeUpdate();
            close(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(T entity) {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            PreparedStatement statement = (PreparedStatement) fromEntity(getUpdateSql(), entity, true);
            statement.executeUpdate();
            close(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(T entity) {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            PreparedStatement statement = connection.prepareStatement(getDeleteSql());
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
            close(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    protected abstract String getFindAllSql();

    protected abstract String getFindByIdSql();

    protected abstract String getInsertSql();

    protected abstract String getUpdateSql();

    protected abstract String getDeleteSql();

    protected abstract T fromResultSet(ResultSet resultSet) throws SQLException;

    protected abstract Statement fromEntity(String query, T entity, boolean withId) throws SQLException;

    protected void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

}
