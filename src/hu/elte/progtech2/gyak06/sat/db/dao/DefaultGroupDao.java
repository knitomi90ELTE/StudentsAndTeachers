package hu.elte.progtech2.gyak06.sat.db.dao;

import hu.elte.progtech2.gyak06.sat.db.entity.Group;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultGroupDao extends DefaultDao<Group> {

    private static final String FIND_ALL_GROUP_SQL = "SELECT * FROM \"GROUP\"";
    private static final String FIND_GROUP_BY_ID_SQL = "SELECT * FROM \"GROUP\" WHERE ID = ?";
    private static final String INSERT_GROUP_SQL = "INSERT INTO \"GROUP\"(\"NAME\") VALUES(?)";
    private static final String UPDATE_GROUP_SQL = "UPDATE \"GROUP\" SET \"NAME\" = ? WHERE ID = ?";
    private static final String DELETE_GROUP_SQL = "DELETE FROM \"GROUP\" WHERE ID = ?";

    private DefaultGroupDao() {
    }

    @Override
    protected Group fromResultSet(ResultSet resultSet) throws SQLException {
        Group group = new Group();
        group.setId(resultSet.getInt("ID"));
        group.setName(resultSet.getString("NAME"));
        return group;
    }

    @Override
    protected Statement fromEntity(String query, Group entity, boolean withId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getName());
        if (withId) {
            statement.setInt(2, entity.getId());
        }
        return statement;
    }

    @Override
    protected String getFindAllSql() {
        return FIND_ALL_GROUP_SQL;
    }

    @Override
    protected String getFindByIdSql() {
        return FIND_GROUP_BY_ID_SQL;
    }

    @Override
    protected String getInsertSql() {
        return INSERT_GROUP_SQL;
    }

    @Override
    protected String getUpdateSql() {
        return UPDATE_GROUP_SQL;
    }

    @Override
    protected String getDeleteSql() {
        return DELETE_GROUP_SQL;
    }

    public static DefaultGroupDao getInstance() {
        return DefaultTeacherDaoHolder.INSTANCE;
    }

    private static class DefaultTeacherDaoHolder {

        private static final DefaultGroupDao INSTANCE = new DefaultGroupDao();
    }
}
