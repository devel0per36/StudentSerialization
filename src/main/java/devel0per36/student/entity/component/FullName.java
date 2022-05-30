package devel0per36.student.entity.component;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Класс для описания Имени
 * @version 1.0
 */
public class FullName implements Cloneable, Serializable {
    /* Фамилия */
    private String lastName;
    /* Имя */
    private String firstName;
    /* Отчество */
    private String middleName;

    /**
     * Конструктор для создания нового объекта типа FullName
     * @param lastName - фамилия
     * @param firstName - имя
     */
    public FullName(String lastName, String firstName) {
        this.lastName = lastName;
        this.firstName = firstName;
    }
    /**
     * Конструктор для создания нового объекта типа FullName
     * @param lastName - фамилия
     * @param firstName - имя
     * @param middleName - отчество
     * @see FullName#FullName(String, String) 
     */
    public FullName(String lastName, String firstName, String middleName) {
        this(lastName, firstName);
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public FullName clone() throws CloneNotSupportedException {
        return (FullName) super.clone();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if ((obj == null) || (getClass() != obj.getClass())) {
            return false;
        }
        FullName name = (FullName) obj;
        return (lastName == name.lastName || ((lastName != null) && lastName.equals(name.lastName))) &&
                (firstName == name.firstName || ((firstName != null) && firstName.equals(name.firstName))) &&
                (middleName == name.middleName || ((middleName != null) && middleName.equals(name.middleName)));
    }

    @Override
    public int hashCode() {
        final int CODE = 31 * 1 + (lastName == null ? 0 : lastName.hashCode()) +
                (firstName == null ? 0 : firstName.hashCode()) + (middleName == null ? 0 : middleName.hashCode());
        return CODE;
    }

    @Override
    public String toString() {
        return new StringJoiner(",", FullName.class.getSimpleName() + "{", "}")
                .add("lastName=" + lastName).add("firstName=" + firstName)
                .add("middleName=" + middleName).toString();
    }
}
