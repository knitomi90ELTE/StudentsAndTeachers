package hu.elte.progtech2.gyak06.sat.gui.action;

import javax.swing.*;
import java.awt.event.*;
import hu.elte.progtech2.gyak06.sat.gui.*;
import hu.elte.progtech2.gyak06.sat.db.dao.*;
import hu.elte.progtech2.gyak06.sat.db.entity.*;
import hu.elte.progtech2.gyak06.sat.gui.model.*;
import static hu.elte.progtech2.gyak06.sat.gui.GuiConstants.*;

public class TableInsertAction implements ActionListener {

    private FrameWithTable parent;

    public TableInsertAction(FrameWithTable parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case ADD_TEACHER_MENU_TEXT:
                // hozzunk létre egy új tanárt, adatbekérésre használjuk a readString() metódust
                Teacher teacher = new Teacher();
                teacher.setLastName(readString(LAST_NAME_QUESTION));
                teacher.setFirstName(readString(FIRST_NAME_QUESTION));

                GenericTableModel teacherModel = (GenericTableModel) parent.getTeacherTable().getModel();
                teacherModel.addEntity(teacher);
                break;
            case ADD_GROUP_MENU_TEXT:
                Group group = new Group();
                group.setName(readString(GROUP_QUESTION));

                GenericTableModel groupModel = (GenericTableModel) parent.getGroupTable().getModel();
                groupModel.addEntity(group);
                break;
            case ADD_SUBJECT_MENU_TEXT:
                Subject subject = new Subject();
                subject.setTitle(readString(SUBJECT_QUESTION));

                GenericTableModel subjectModel = (GenericTableModel) parent.getSubjectTable().getModel();
                subjectModel.addEntity(subject);
                break;
            case ADD_SUBJECT_TEACHER_MENU_TEXT:
                Subject selectedSubject = readSubject();
                if (selectedSubject != null) {
                    Group selectedGroup = readGroup();
                    if (selectedGroup != null) {
                        Teacher selectedTeacher = readTeacher();
                        if (selectedTeacher != null) {
                            SubjectTeacher subjectTeacher = new SubjectTeacher();
                            subjectTeacher.setSubject(selectedSubject);
                            subjectTeacher.setGroup(selectedGroup);
                            subjectTeacher.setTeacher(selectedTeacher);
                            
                            GenericTableModel subjectTeacherModel = (GenericTableModel) parent.getSubjectTeacherTable().getModel();
                            subjectTeacherModel.addEntity(subjectTeacher);
                        }
                    }
                }
                break;
        }
    }

    private String readString(String label) {
        String name = null;
        while (name == null) {
            name = JOptionPane.showInputDialog(parent, label, DIALOG_TITLE, JOptionPane.INFORMATION_MESSAGE);
            if (name != null && name.trim().equals("")) {
                name = null;
            }
        }
        return name;
    }
    
    private Subject readSubject() {
        Object[] subjects = DefaultSubjectDao.getInstance().findAll().toArray();
        Subject subject = (Subject) JOptionPane.showInputDialog(parent, SELECT_SUBJECT, DIALOG_TITLE, JOptionPane.QUESTION_MESSAGE, null, subjects, subjects[0]);
        return subject;
    }
    
    private Group readGroup() {
        Object[] groups = DefaultGroupDao.getInstance().findAll().toArray();
        Group group = (Group) JOptionPane.showInputDialog(parent, SELECT_SUBJECT, DIALOG_TITLE, JOptionPane.QUESTION_MESSAGE, null, groups, groups[0]);
        return group;
    }

    private Teacher readTeacher() {
        Object[] teachers = DefaultTeacherDao.getInstance().findAll().toArray();
        Teacher teacher = (Teacher) JOptionPane.showInputDialog(parent, SELECT_SUBJECT, DIALOG_TITLE, JOptionPane.QUESTION_MESSAGE, null, teachers, teachers[0]);
        return teacher;
    }
}
