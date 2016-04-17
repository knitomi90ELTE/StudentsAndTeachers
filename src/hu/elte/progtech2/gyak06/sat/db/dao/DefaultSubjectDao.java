package hu.elte.progtech2.gyak06.sat.db.dao;

import hu.elte.progtech2.gyak06.sat.db.entity.Subject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultSubjectDao extends DefaultDao<Subject>{

    private static final String FIND_ALL_SUBJECT_SQL = "SELECT * FROM SUBJECT";
    private static final String FIND_SUBJECT_BY_ID_SQL = "SELECT * FROM SUBJECT WHERE ID = ?";
    private static final String INSERT_SUBJECT_SQL = "INSERT INTO SUBJECT(TITLE) VALUES(?)";
    private static final String UPDATE_SUBJECT_SQL = "UPDATE SUBJECT SET TITLE = ? WHERE ID = ?";
    private static final String DELETE_SUBJECT_SQL = "DELETE FROM SUBJECT WHERE ID = ?";

    private DefaultSubjectDao() {
    }
    
    @Override
    protected Subject fromResultSet(ResultSet resultSet) throws SQLException {
        Subject subject = new Subject();
        subject.setId(resultSet.getInt("ID"));
        subject.setTitle(resultSet.getString("TITLE"));
        return subject;
    }

    @Override
    protected Statement fromEntity(String query, Subject entity, boolean withId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, entity.getTitle());
        if (withId) {
            statement.setInt(2, entity.getId());
        }
        return statement;
    }

    @Override
    protected String getFindAllSql() {
        return FIND_ALL_SUBJECT_SQL;
    }

    @Override
    protected String getFindByIdSql() {
        return FIND_SUBJECT_BY_ID_SQL;
    }

    @Override
    protected String getInsertSql() {
        return INSERT_SUBJECT_SQL;
    }

    @Override
    protected String getUpdateSql() {
        return UPDATE_SUBJECT_SQL;
    }

    @Override
    protected String getDeleteSql() {
        return DELETE_SUBJECT_SQL;
    }
    
    public static DefaultSubjectDao getInstance() {
        return DefaultTeacherDaoHolder.INSTANCE;
    }
    
    private static class DefaultTeacherDaoHolder {

        private static final DefaultSubjectDao INSTANCE = new DefaultSubjectDao();
    }
}
