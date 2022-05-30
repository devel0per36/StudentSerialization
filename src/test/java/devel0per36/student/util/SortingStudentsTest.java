package devel0per36.student.util;

import devel0per36.student.entity.Student;
import devel0per36.student.entity.component.Country;
import devel0per36.student.entity.component.FullName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SortingStudentsTest {
    private List<Student> students;

    @BeforeEach
    public void init() {
        Student anton = new Student(10, new FullName("Антонов", "Антон"), 18, 5.9, Country.FRANCE);
        Student alice = new Student(5, new FullName("Петрова", "Алиса", "Сергеевна"), 19, 9.0, Country.RUSSIA);
        Student roma = new Student(53, new FullName("Микрофонов", "Роман", "Алексеевич"), 26, 8.5, Country.RUSSIA);
        Student alexandr = new Student(1, new FullName("Корнеплодов", "Александр", "Александрович"), 21, 7.5, Country.TURKEY);
        Student maxim = new Student(78, new FullName("Иванов", "Максим", "Александрович"), 20, 7.5, Country.BELARUS);
        Student dasha = new Student(66, new FullName("Краснова", "Дарья", "Васильевна"), 18, 9.0, Country.BELARUS);
        Student stepan = new Student(97, new FullName("Конфетов", "Степан", "Иванович"), 18, 6.9, Country.RUSSIA);
        Student ivan = new Student(99, new FullName("Борисов", "Иван", "Васильевич"), 32, 7.5, Country.KAZAKHSTAN);
        students = List.of(anton, alice, roma, alexandr, maxim, dasha, stepan, ivan);
    }

    @Test
    @DisplayName("Проверка количества возвращаемых элементов в списке")
    public void checkQuantityInList() {
        Assertions.assertEquals(8, students.size());

        List<Student> sortResult = SortingStudents.moreThanRequestedRatingAndName(students, 7.0);
        Assertions.assertEquals(6, sortResult.size());
    }

    @Test
    @DisplayName("Проверка отсортированных элементов")
    public void checkSortedList() {
        List<Student> sortResult = SortingStudents.moreThanRequestedRatingAndName(students, 7.0);

        Assertions.assertEquals(99, sortResult.get(0).getId());
        Assertions.assertEquals("Борисов", sortResult.get(0).getName().getLastName());
        Assertions.assertEquals(7.5, sortResult.get(0).getRating());

        Assertions.assertEquals(78, sortResult.get(1).getId());
        Assertions.assertEquals("Иванов", sortResult.get(1).getName().getLastName());
        Assertions.assertEquals(7.5, sortResult.get(1).getRating());

        Assertions.assertEquals(1, sortResult.get(2).getId());
        Assertions.assertEquals("Корнеплодов", sortResult.get(2).getName().getLastName());
        Assertions.assertEquals(7.5, sortResult.get(2).getRating());

        Assertions.assertEquals(53, sortResult.get(3).getId());
        Assertions.assertEquals("Микрофонов", sortResult.get(3).getName().getLastName());
        Assertions.assertEquals(8.5, sortResult.get(3).getRating());

        Assertions.assertEquals(66, sortResult.get(4).getId());
        Assertions.assertEquals("Краснова", sortResult.get(4).getName().getLastName());
        Assertions.assertEquals(9.0, sortResult.get(4).getRating());

        Assertions.assertEquals(5, sortResult.get(5).getId());
        Assertions.assertEquals("Петрова", sortResult.get(5).getName().getLastName());
        Assertions.assertEquals(9.0, sortResult.get(5).getRating());
    }
}
