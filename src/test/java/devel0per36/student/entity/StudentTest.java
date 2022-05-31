package devel0per36.student.entity;

import devel0per36.student.entity.component.Country;
import devel0per36.student.entity.component.FullName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentTest {
    private Student student;

    @BeforeEach
    public void init() {
        FullName name = new FullName("Иванов", "Пётр", "Сергеевич");
        student = new Student(23, name, 19, 6.8, Country.RUSSIA);
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        FullName copyName = new FullName("Иванов", "Пётр", "Сергеевич");
        Assertions.assertTrue(
                (student.getId() == 23) &&
                        (student.getName().equals(copyName)) &&
                        (student.getAge() == 19) &&
                        (student.getRating() == 6.8) &&
                        (student.getCountry() == Country.RUSSIA)
        );
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        FullName copyName = new FullName("Иванов", "Пётр", "Сергеевич");
        Student copyStudent = new Student(23, copyName, 19, 6.8, Country.RUSSIA);
        Assertions.assertTrue(student.equals(copyStudent));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями")
    public void notEqualsObjects() {
        FullName sergeyName = new FullName("Петров", "Сергей", "Александрович");
        Student sergeyStudent = new Student(89, sergeyName, 18, 8.2, Country.RUSSIA);
        Assertions.assertFalse(student.equals(sergeyStudent));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            Student cloneStudent = student.clone();
            Assertions.assertTrue(student.equals(cloneStudent));
            cloneStudent.setRating(9.2);
            Assertions.assertNotEquals(9.2, student.getRating());
            Assertions.assertEquals(9.2, cloneStudent.getRating());
            Assertions.assertFalse(student.equals(cloneStudent));
        } catch (CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа Student: " + ex.getMessage());
        }
    }
}
