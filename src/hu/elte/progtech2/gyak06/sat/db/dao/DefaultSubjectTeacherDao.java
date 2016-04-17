package hu.elte.progtech2.gyak06.sat.db.dao;

import hu.elte.progtech2.gyak06.sat.db.entity.SubjectTeacher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DefaultSubjectTeacherDao extends DefaultDao<SubjectTeacher> {

    private static final String FIND_ALL_SUBJECT_TEACHER_SQL = "SELECT * FROM SUBJECT_TEACHER";
    private static final String FIND_SUBJECT_TEACHER_BY_ID_SQL = "SELECT * FROM SUBJECT_TEACHER WHERE ID = ?";
    private static final String INSERT_SUBJECT_TEACHER_SQL = "INSERT INTO SUBJECT_TEACHER(SUBJECT_ID, GROUP_ID, TEACHER_ID) VALUES(?,?,?)";
    private static final String UPDATE_SUBJECT_TEACHER_SQL = "UPDATE SUBJECT_TEACHER SET SUBJECT_ID = ?, GROUP_ID = ?, TEACHER_ID = ? WHERE ID = ?";
    private static final String DELETE_SUBJECT_TEACHER_SQL = "DELETE FROM SUBJECT_TEACHER WHERE ID = ?";

    private DefaultSubjectTeacherDao() {
    }

    public static DefaultSubjectTeacherDao getInstance() {
        return DefaultSubjectTeacherDaoHolder.INSTANCE;
    }

    @Override
    protected String getFindAllSql() {
        return FIND_ALL_SUBJECT_TEACHER_SQL;
    }

    @Override
    protected String getFindByIdSql() {
        return FIND_SUBJECT_TEACHER_BY_ID_SQL;
    }

    @Override
    protected String getInsertSql() {
        return INSERT_SUBJECT_TEACHER_SQL;
    }

    @Override
    protected String getUpdateSql() {
        return UPDATE_SUBJECT_TEACHER_SQL;
    }

    @Override
    protected String getDeleteSql() {
        return DELETE_SUBJECT_TEACHER_SQL;
    }

    @Override
    protected SubjectTeacher fromResultSet(ResultSet resultSet) throws SQLException {
        SubjectTeacher entity = new SubjectTeacher();
        entity.setId(resultSet.getInt("ID"));
        entity.setSubject(
                DefaultSubjectDao.getInstance().findById(
                        resultSet.getInt("SUBJECT_ID")
                )
        );
        entity.setGroup(DefaultGroupDao.getInstance().findById(resultSet.getInt("GROUP_ID")));
        entity.setTeacher(DefaultTeacherDao.getInstance().findById(resultSet.getInt("TEACHER_ID")));
        return entity;
    }

    @Override
    protected Statement fromEntity(String query, SubjectTeacher entity, boolean withId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, entity.getSubject().getId());
        statement.setInt(2, entity.getGroup().getId());
        statement.setInt(3, entity.getTeacher().getId());
        if (withId) {
            statement.setInt(4, entity.getId());
        }
        return statement;
    }

    private static class DefaultSubjectTeacherDaoHolder {

        private static final DefaultSubjectTeacherDao INSTANCE = new DefaultSubjectTeacherDao();
    }
}
