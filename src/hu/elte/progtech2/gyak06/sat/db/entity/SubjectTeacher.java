package hu.elte.progtech2.gyak06.sat.db.entity;

import java.util.Objects;

public class SubjectTeacher extends PersistentEntity {

    private Subject subject;
    private Group group;
    private Teacher teacher;

    public SubjectTeacher() {
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SubjectTeacher other = (SubjectTeacher) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return subject + ", " + group + ": " + teacher;
    }
    
    @Override
    public Object get(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return subject;
            case 1:
                return group;
            case 2:
                return teacher;
            default:
                return null;
        }
    }

    @Override
    public void set(int columnIndex, Object value) {
        switch (columnIndex) {
            case 0:
                setSubject((Subject) value);
                break;
            case 1:
                setGroup((Group) value);
                break;
            case 2:
                setTeacher((Teacher) value);
                break;
        }
    }
    
    public static final String PROPERTY_NAMES[] = {"Tantárgy", "Csoport", "Tanár"};
    
}
