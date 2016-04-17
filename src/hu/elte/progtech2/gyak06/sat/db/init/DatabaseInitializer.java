package hu.elte.progtech2.gyak06.sat.db.init;

import hu.elte.progtech2.gyak06.sat.db.dao.*;
import hu.elte.progtech2.gyak06.sat.db.entity.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseInitializer {

    private Connection connection;
    private Properties properties;

    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/StudentsAndTeachers2";
    private static final String DELETE_TEACHER_TABLE_SQL = "DROP TABLE TEACHER";
    private static final String CREATE_TEACHER_TABLE_SQL = "CREATE TABLE TEACHER ("
            + " ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + " FIRST_NAME VARCHAR(20),"
            + " LAST_NAME VARCHAR(20)"
            + ")";
    private static final String DELETE_GROUP_TABLE_SQL = "DROP TABLE \"GROUP\"";
    private static final String CREATE_GROUP_TABLE_SQL = "CREATE TABLE \"GROUP\" ("
            + " ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + " \"NAME\" VARCHAR(20)"
            + ")";
    private static final String DELETE_SUBJECT_TABLE_SQL = "DROP TABLE SUBJECT";
    private static final String CREATE_SUBJECT_TABLE_SQL = "CREATE TABLE SUBJECT ("
            + " ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + " TITLE VARCHAR(20)"
            + ")";
    private static final String DELETE_SUBJECT_TEACHER_TABLE_SQL = "DROP TABLE SUBJECT_TEACHER";
    private static final String CREATE_SUBJECT_TEACHER_TABLE_SQL = "CREATE TABLE SUBJECT_TEACHER ("
            + " ID INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
            + " SUBJECT_ID INT CONSTRAINT FK_SUBJ_TEACH_SUBJ REFERENCES SUBJECT ON DELETE CASCADE ON UPDATE RESTRICT,"
            + " TEACHER_ID INT CONSTRAINT FK_SUBJ_TEACH_TEACH REFERENCES TEACHER ON DELETE CASCADE ON UPDATE RESTRICT,"
            + " GROUP_ID INT CONSTRAINT FK_SUBJ_TEACH_GROUP REFERENCES \"GROUP\" ON DELETE CASCADE ON UPDATE RESTRICT"
            + ")";

    private DatabaseInitializer() {
        properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "password");
    }

    public void init() {
        deleteTables();
        createTables();
        uploadTeacherTable();
        uploadGroupTable();
        uploadSubjectTable();
    }
    
    private void deleteTables() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            Statement statement = connection.createStatement();
            statement.executeUpdate(DELETE_SUBJECT_TEACHER_TABLE_SQL);
            statement.executeUpdate(DELETE_SUBJECT_TABLE_SQL);
            statement.executeUpdate(DELETE_GROUP_TABLE_SQL);
            statement.executeUpdate(DELETE_TEACHER_TABLE_SQL);
            close(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void createTables() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, properties);
            Statement statement = connection.createStatement();
            statement.addBatch(CREATE_GROUP_TABLE_SQL);
            statement.addBatch(CREATE_SUBJECT_TABLE_SQL);
            statement.addBatch(CREATE_TEACHER_TABLE_SQL);
            statement.addBatch(CREATE_SUBJECT_TEACHER_TABLE_SQL);
            statement.executeBatch();
            close(statement);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void uploadTeacherTable() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(createTeacher("Árpád", "Németh"));
        teachers.add(createTeacher("Kata", "Horváth"));
        teachers.add(createTeacher("Eszter", "Kiss"));
        teachers.add(createTeacher("Zoltán", "Szabó"));
        
        for (Teacher teacher : teachers) {
            DefaultTeacherDao.getInstance().create(teacher);
        }
    }
    
    private void uploadGroupTable() {
        List<Group> groups = new ArrayList<>();
        groups.add(createGroup("Katica csoport"));
        groups.add(createGroup("Méhecske csoport"));
        
        for (Group group : groups) {
            DefaultGroupDao.getInstance().create(group);
        }
    }
    
    private void uploadSubjectTable() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(createSubject("Szinezés"));
        subjects.add(createSubject("Hajtogatás"));
        
        for (Subject subject : subjects) {
            DefaultSubjectDao.getInstance().create(subject);
        }
    }
    
    private void close(Statement statement) throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }
    
    private Teacher createTeacher(String firstName, String lastName) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        return teacher;
    }
    
    private Group createGroup(String name) {
        Group group = new Group();
        group.setName(name);
        return group;
    }
    
    private Subject createSubject(String title) {
        Subject subject = new Subject();
        subject.setTitle(title);
        return subject;
    }

    public static DatabaseInitializer getInstance() {
        return DatabaseInitializerHolder.INSTANCE;
    }

    private static class DatabaseInitializerHolder {
        private static final DatabaseInitializer INSTANCE = new DatabaseInitializer();
    }
}
