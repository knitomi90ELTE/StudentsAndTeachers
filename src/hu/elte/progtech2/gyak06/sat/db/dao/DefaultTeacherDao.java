package hu.elte.progtech2.gyak06.sat.db.dao;

import hu.elte.progtech2.gyak06.sat.db.entity.Teacher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultTeacherDao extends DefaultDao<Teacher> {

    private static final String FIND_ALL_TEACHER_SQL = "SELECT * FROM TEACHER";
    private static final String FIND_TEACHER_BY_ID_SQL = "SELECT * FROM TEACHER WHERE ID = ?";
    private static final String INSERT_TEACHER_SQL = "INSERT INTO TEACHER(FIRST_NAME, LAST_NAME) VALUES(?,?)";
    private static final String UPDATE_TEACHER_SQL = "UPDATE TEACHER SET FIRST_NAME = ?, LAST_NAME = ? WHERE ID = ?";
    private static final String DELETE_TEACHER_SQL = "DELETE FROM TEACHER WHERE ID = ?";

    private DefaultTeacherDao() {
    }

    public static DefaultTeacherDao getInstance() {
        return DefaultTeacherDaoHolder.INSTANCE;
    }

    @Override
    protected Teacher fromResultSet(ResultSet resultSet) throws SQLException {
        Teacher teacher = new Teacher();
        teacher.setId(resultSet.getInt("ID"));
        teacher.setFirstName(resultSet.getString("FIRST_NAME"));
        teacher.setLastName(resultSet.getString("LAST_NAME"));
        return teacher;
    }

    @Override
    protected Statement fromEntity(String query, Teacher entity, boolean withId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getFirstName());
        statement.setString(2, entity.getLastName());
        if (withId) {
            statement.setInt(3, entity.getId());
        }
        return statement;
    }

    @Override
    protected String getFindAllSql() {
        return FIND_ALL_TEACHER_SQL;
    }

    @Override
    protected String getFindByIdSql() {
        return FIND_TEACHER_BY_ID_SQL;
    }

    @Override
    protected String getInsertSql() {
        return INSERT_TEACHER_SQL;
    }

    @Override
    protected String getUpdateSql() {
        return UPDATE_TEACHER_SQL;
    }

    @Override
    protected String getDeleteSql() {
        return DELETE_TEACHER_SQL;
    }
    
    private static class DefaultTeacherDaoHolder {
        private static final DefaultTeacherDao INSTANCE = new DefaultTeacherDao();
    }
}
