package hu.elte.progtech2.gyak06.sat.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.*;
import hu.elte.progtech2.gyak06.sat.db.dao.*;
import hu.elte.progtech2.gyak06.sat.db.entity.*;
import hu.elte.progtech2.gyak06.sat.gui.model.*;
import hu.elte.progtech2.gyak06.sat.gui.action.*;
import static hu.elte.progtech2.gyak06.sat.gui.GuiConstants.*;

public class FrameWithTable extends JFrame {

    private final JTabbedPane tabbedPane = new JTabbedPane();
    private final JTable teacherTable = new JTable();
    private final JTable groupTable = new JTable();
    private final JTable subjectTable = new JTable();
    private final JTable subjectTeacherTable = new JTable();

    private ActionListener addAction;
    private MouseAdapter rightClickAction;
    private TableRemoveAction delAction;

    public FrameWithTable() {
        initFrame();
        linkActionListeners();

        setMenu();
        setPageStart();
        setCenter();

        pack();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
    }

    public JTable getTeacherTable() {
        return teacherTable;
    }

    public JTable getGroupTable() {
        return groupTable;
    }

    public JTable getSubjectTable() {
        return subjectTable;
    }

    public JTable getSubjectTeacherTable() {
        return subjectTeacherTable;
    }

    private void initFrame() {
        setTitle(FRAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);
    }

    private void setMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu create = new JMenu(ADD_MENU_TEXT);

        JMenuItem group = new JMenuItem(ADD_GROUP_MENU_TEXT);
        group.addActionListener(addAction);
        JMenuItem teacher = new JMenuItem(ADD_TEACHER_MENU_TEXT);
        teacher.addActionListener(addAction);
        JMenuItem subject = new JMenuItem(ADD_SUBJECT_MENU_TEXT);
        subject.addActionListener(addAction);
        JMenuItem subjectTeacher = new JMenuItem(ADD_SUBJECT_TEACHER_MENU_TEXT);
        subjectTeacher.addActionListener(addAction);

        create.add(group);
        create.add(teacher);
        create.add(subject);
        create.addSeparator();
        create.add(subjectTeacher);

        menuBar.add(create);
        setJMenuBar(menuBar);
    }

    private void setPageStart() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        JLabel label = new JLabel(FRAME_TITLE);

        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        label.setForeground(Color.LIGHT_GRAY);
        panel.setBackground(Color.DARK_GRAY);

        panel.add(label);
        add(panel, BorderLayout.NORTH);
    }

    private void setCenter() {
        JPanel panel = new JPanel(new GridLayout(1, 1));

        setTeachersTab();
        setGroupsTab();
        setSubjectsTab();
        setSubjectTeachersTab();

        panel.add(tabbedPane);
        add(panel, BorderLayout.CENTER);
    }

    private void setTeachersTab() {
        GenericTableModel<Teacher> model = new GenericTableModel(DefaultTeacherDao.getInstance(), Teacher.PROPERTY_NAMES);
        TableRowSorter<GenericTableModel<Teacher>> sorter = new TableRowSorter<>(model);
        
        teacherTable.setModel(model);
        teacherTable.setRowSorter(sorter);

        teacherTable.addMouseListener(rightClickAction);
        tabbedPane.add(TEACHER_TAB_TEXT, new JScrollPane(teacherTable));
    }

    private void setGroupsTab() {
        GenericTableModel<Group> model = new GenericTableModel(DefaultGroupDao.getInstance(), Group.PROPERTY_NAMES);
        TableRowSorter<GenericTableModel<Group>> sorter = new TableRowSorter<>(model);
        
        groupTable.setModel(model);
        groupTable.setRowSorter(sorter);

        groupTable.addMouseListener(rightClickAction);
        tabbedPane.add(GROUP_TAB_TEXT, new JScrollPane(groupTable));
    }
    
    private void setSubjectsTab() {
        GenericTableModel<Subject> model = new GenericTableModel(DefaultSubjectDao.getInstance(), Subject.PROPERTY_NAMES);
        TableRowSorter<GenericTableModel<Subject>> sorter = new TableRowSorter<>(model);
        
        subjectTable.setModel(model);
        subjectTable.setRowSorter(sorter);

        subjectTable.addMouseListener(rightClickAction);
        tabbedPane.add(SUBJECT_TAB_TEXT, new JScrollPane(subjectTable));        
    }

    private void setSubjectTeachersTab() {
        GenericTableModel<SubjectTeacher> model = new GenericTableModel(DefaultSubjectTeacherDao.getInstance(), SubjectTeacher.PROPERTY_NAMES);
        TableRowSorter<GenericTableModel<SubjectTeacher>> sorter = new TableRowSorter<>(model);
        
        subjectTeacherTable.setModel(model);
        subjectTeacherTable.setRowSorter(sorter);

        setDropdownColumn(subjectTeacherTable, 0, DefaultSubjectDao.getInstance().findAll().toArray());
        setDropdownColumn(subjectTeacherTable, 1, DefaultGroupDao.getInstance().findAll().toArray());
        setDropdownColumn(subjectTeacherTable, 2, DefaultTeacherDao.getInstance().findAll().toArray());
        
        subjectTeacherTable.addMouseListener(rightClickAction);
        tabbedPane.add(SUBJECT_TEACHER_TAB_TEXT, new JScrollPane(subjectTeacherTable)); 
    }
    
    private void setDropdownColumn(JTable table, int index, Object[] values) {
        JComboBox comboBox = new JComboBox(values);
        TableColumn column = table.getColumnModel().getColumn(index);
        column.setCellEditor(new DefaultCellEditor(comboBox));
        column.setCellRenderer(new DefaultTableCellRenderer());
    }
    
    private void linkActionListeners() {
        delAction = new TableRemoveAction(this);
        addAction = new TableInsertAction(this);
        rightClickAction = new TableRightClickAction(delAction);
    }

}
