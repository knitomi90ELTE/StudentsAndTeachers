package hu.elte.progtech2.gyak06.sat.db.entity;

import java.util.Objects;

public class Teacher extends PersistentEntity {

    private String firstName;
    private String lastName;

    public Teacher() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
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
        final Teacher other = (Teacher) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public Object get(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return lastName;
            case 1:
                return firstName;
            default:
                return null;
        }
    }

    @Override
    public void set(int columnIndex, Object value) {
        switch (columnIndex) {
            case 0:
                setLastName((String) value);
                break;
            case 1:
                setFirstName((String) value);
        }
    }

    public static final String PROPERTY_NAMES[]= {"Vezetéknév", "Utónév"};

}
