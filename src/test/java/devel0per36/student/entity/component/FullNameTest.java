package devel0per36.student.entity.component;

import devel0per36.student.entity.component.FullName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FullNameTest {
    private FullName name;

    @BeforeEach
    public void init() {
        name = new FullName("Иванов", "Иван", "Иванович");
    }

    @Test
    @DisplayName("Равны ли значения объекта таким же константным значениям")
    public void equalsValues() {
        Assertions.assertTrue(
                name.getLastName().equals("Иванов") &&
                        name.getFirstName().equals("Иван") &&
                        name.getMiddleName().equals("Иванович")
        );
    }

    @Test
    @DisplayName("Равны ли 2 объекта с одинаковыми значениями")
    public void equalsCopyObject() {
        FullName copyName = new FullName("Иванов", "Иван", "Иванович");
        Assertions.assertTrue(name.equals(copyName));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями (ФИО с отчеством)")
    public void notEqualsObjectsWithMiddleName() {
        FullName petrName = new FullName("Петров", "Пётр", "Иванович");
        Assertions.assertFalse(name.equals(petrName));
    }

    @Test
    @DisplayName("Не равны ли два объекта с разными значениями (ФИО без отчества)")
    public void notEqualsObjectsWithoutMiddleName() {
        FullName aliceName = new FullName("Краснова", "Алиса");
        Assertions.assertFalse(name.equals(aliceName));
    }

    @Test
    @DisplayName("Равны ли исходный и клонируемый объекты")
    public void equalsCloneObject() {
        try {
            FullName cloneName = name.clone();
            Assertions.assertTrue(name.equals(cloneName));
            cloneName.setLastName("Борисов");
            Assertions.assertNotEquals("Борисов", name.getLastName());
            Assertions.assertEquals("Борисов", cloneName.getLastName());
            Assertions.assertFalse(name.equals(cloneName));
        } catch(CloneNotSupportedException ex) {
            System.err.println("Ошибка клонирования объекта типа FullName: " + ex.getMessage());
        }
    }
}
